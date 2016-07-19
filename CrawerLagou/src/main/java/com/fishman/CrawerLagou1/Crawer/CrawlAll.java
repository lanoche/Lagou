package com.fishman.CrawerLagou1.Crawer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fishman.CrawerLagou1.Dao.MongoDao;
import com.mongodb.BasicDBObject;
/*
 * 1. 爬取网页得到json字符串
 * 2. 解析json提取有用信息
 * 3. 对有用信息封装并写入数据库
 */
public class CrawlAll {
	 private static CrawlAll ourInstance = new CrawlAll();

	    public static CrawlAll getInstance() {
	        return ourInstance;
	    }

	    private CrawlAll() {
	    }
	    static MongoDao dao = new MongoDao();
    	
	    public static void main(String[] args) {
	    	CrawlAll crawlClient = new CrawlAll();
	        BasicCookieStore cookieStore = new BasicCookieStore();
	        
	        CloseableHttpClient httpClient = HttpClients.custom()
	                .setDefaultCookieStore(cookieStore).build();
	        dao.remove("lagouJob", "city");
	        for (int i = 1; i <= 100; i++) {
	            HttpUriRequest getOneData = RequestBuilder.post()
	                    .setUri("http://www.lagou.com/jobs/positionAjax.json?px=default&city=")
	                    .addParameter("first", "true")
	                    .addParameter("pn", i + "")
	                    .addParameter("kd", "java").build();
	            
	            String responseContent = null;
	            try {
	                responseContent = crawlClient.getStringFromRequest(httpClient, getOneData);
	                //System.out.println("responseContent"+responseContent);
	            } catch (Exception e) {
	                break;
	            }
	            JSONArray results = crawlClient.getResult(responseContent);
	            List<BasicDBObject> coms = parseArray(results);

	            crawlClient.insertResponseToDb(coms);
	        }
	    }

		public String getStringFromRequest(CloseableHttpClient httpClient, HttpUriRequest httpUriRequest) {
	        StringBuilder sb = new StringBuilder();
	        try {
	            CloseableHttpResponse response = httpClient.execute(httpUriRequest);
	            InputStream is = response.getEntity().getContent();
	            Scanner sc = new Scanner(is);

	            while (sc.hasNext()) {
	                sb.append(sc.nextLine());
	            }
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        return sb.toString();
	    }

		private void insertResponseToDb(List<BasicDBObject> coms) {
	    	
	    	for(BasicDBObject doc : coms){
	    		dao.insert("lagouJob", "city", doc);
	    	}
	    }
	    
	    //fastJSON对爬取结果处理后返回JSONArray
	    private JSONArray getResult(String responseContent) {
	        JSONObject json = JSON.parseObject(responseContent);
	        JSONObject content = JSON.parseObject(json.get("content").toString());
	        JSONArray results = JSON.parseArray(content.get("result").toString());
	        System.out.println("result"+results);
	        return  results;
	    }
	    //从爬取结果中提取有用信息并封装成mongodb的BasicDBObject形式
	    private static List<BasicDBObject> parseArray(JSONArray results) {
	    	List<BasicDBObject> list = new ArrayList<BasicDBObject>();
			for(Object o:results){
				JSONObject ob = (JSONObject)o;
				String city = ob.get("city").toString();
				
				BasicDBObject doc = new BasicDBObject();
				doc.put("city", city);
				list.add(doc);
			}
			return list;
		}
}
