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

public class CrawlClient {

    private static CrawlClient ourInstance = new CrawlClient();

    public static CrawlClient getInstance() {
        return ourInstance;
    }

    private CrawlClient() {
    }

    public static void main(String[] args) {
        CrawlClient crawlClient = new CrawlClient();
        BasicCookieStore cookieStore = new BasicCookieStore();
        
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).build();
        for (int i = 1; i <= 100; i++) {
            HttpUriRequest getOneData = RequestBuilder.post()
                    .setUri("http://www.lagou.com/jobs/positionAjax.json?px=default&city=%E6%AD%A6%E6%B1%89")
                    .addParameter("first", "true")
                    .addParameter("pn", i + "")
                    .addParameter("kd", "java").build();
            
            String responseContent = null;
            try {
                responseContent = crawlClient.getStringFromRequest(httpClient, getOneData);
                System.out.println("responseContent"+responseContent);
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
    	MongoDao dao = new MongoDao();
    	dao.remove("lagouJob", "lagou");
    	for(BasicDBObject doc : coms){
    		dao.insert("lagouJob", "lagou", doc);
    	}
    }
    
    //fastJSON对爬取结果处理后返回JSONArray
    private JSONArray getResult(String responseContent) {
        JSONObject json = JSON.parseObject(responseContent);
        JSONObject content = JSON.parseObject(json.get("content").toString());
        JSONArray results = JSON.parseArray(content.get("result").toString());
        //System.out.println("result"+results);
        return  results;
    }
    //从爬取结果中提取有用信息并封装成mongodb的BasicDBObject形式
    private static List<BasicDBObject> parseArray(JSONArray results) {
    	List<BasicDBObject> list = new ArrayList<BasicDBObject>();
		for(Object o:results){
			JSONObject ob = (JSONObject)o;
			String companyLabelList = ob.get("companyLabelList").toString();
			String companyName = ob.get("companyName").toString();
			String companySize = ob.get("companySize").toString();
			String createTime = ob.get("createTime").toString();
			String education = ob.get("education").toString();
			String financeStage = ob.get("financeStage").toString();
			String industryField = ob.get("industryField").toString();
			String positionAdvantage = ob.get("positionAdvantage").toString();
			String positionId = ob.get("positionId").toString();
			String positionName = ob.get("positionName").toString();
			String positionType = ob.get("positionType").toString();
			String salary = ob.get("salary").toString();
			String workYear = ob.get("workYear").toString();
			
			BasicDBObject doc = new BasicDBObject();
			doc.put("companyLabelList", companyLabelList);
			doc.put("companyName", companyName);
			doc.put("companySize", companySize);
			doc.put("createTime", createTime);
			doc.put("education", education);
			doc.put("financeStage", financeStage);
			doc.put("industryField", industryField);
			doc.put("positionAdvantage", positionAdvantage);
			doc.put("positionId", positionId);
			doc.put("positionName", positionName);
			doc.put("positionType", positionType);
			doc.put("salary", salary);
			doc.put("workYear", workYear);
			list.add(doc);
//			com.setCompanyLabelList(companyLabelList);
//			com.setCompanyName(companyName);
//			com.setCompanySize(companySize);
//			com.setCreateTime(createTime);
//			com.setEducation(education);
//			com.setFinanceStage(financeStage);
//			com.setIndustryField(industryField);
//			com.setPositionAdvantage(positionAdvantage);
//			com.setPositionId(positionId);
//			com.setPositionName(positionName);
//			com.setPositionType(positionType);
//			com.setSalary(salary);
//			com.setWorkYear(workYear);
//			JSONObject jsonOb = (JSONObject) JSON.toJSON(ob);
//			list.add(jsonOb);
		}
		return list;
	}
}
