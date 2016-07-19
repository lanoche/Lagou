package com.lagou.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
@Component
public class BasicUtil {
	
	public Map<String,Integer> count(DBCursor cursor){
		Map<String,Integer> map = new HashMap<String,Integer>();
		int i = 0,j = 0,k = 0,m = 0;
		while(cursor.hasNext()){
			DBObject o = cursor.next();
			String workYear = (String) o.get("workYear");
			if(workYear.equals("不限")||workYear.equals("应届毕业生")){
				map.put("应届生", i++);
			}else if(workYear.charAt(0)=='1'){
				map.put("1-3年经验", j++);
			}else if(workYear.charAt(0)=='3'){
				map.put("3-5年经验", k++);
			}else if(workYear.charAt(0)=='5'){
				map.put("5年以上", m++);
			}
		}
		return map;
	}
	
	public String map2Json(Map<String,Integer> map){
		JSONArray array = new JSONArray();
		for(Entry<String, Integer> e:map.entrySet()){
			JSONObject object = new JSONObject();
			object.put("name", e.getKey());
			object.put("value", e.getValue());
			array.add(object);
		}
		return JSON.toJSONString(array);
	}

	public Map<String,String> parse(DBCursor cursor) {
		Map<String,String> map = new HashMap<String,String>();
		Integer[] max=new Integer[4];
		Integer[] min=new Integer[4];
		Integer[] avg=new Integer[4];
		int index0=0,index1=0,index2=0,index3=0;
		int max0 = 0,min0 = 0,sum0 = 0,avg0;
		int max1 = 0,min1 = 0,sum1 = 0,avg1;
		int max2 = 0,min2 = 0,sum2 = 0,avg2;
		int max3 = 0,min3 = 0,sum3 = 0,avg3;

		while(cursor.hasNext()){
			DBObject o = cursor.next();
			String salary = (String) o.get("salary");
			String[] salarys = null;
			if(salary.contains("-")){
				salarys = salary.split("-");
			}else{
				continue;
			}
		
			int maxs=new Integer(salarys[1].substring(0,salarys[1].length()-1));
			int mins=new Integer(salarys[0].substring(0,salarys[0].length()-1));

			String workYear = (String)o.get("workYear");
			if(workYear.equals("不限")||workYear.equals("应届毕业生")){
				System.out.println(workYear+"  "+Arrays.toString(salarys));
			}
			if(workYear.equals("不限")||workYear.equals("应届毕业生")){
				if(index0==0){
					min0=mins;
					index0++;
				}
				if(maxs>max0){
					max0=maxs;
				}
				if(mins<min0){
					min0=mins;
				}
				sum0+=(maxs+mins);
				index0++;
			}else if(workYear.charAt(0)=='1'){
				if(index1==0){
					min1=mins;
					index1++;
				}
				if(maxs>max1){
					max1=maxs;
				}
				if(mins<min1){
					min1=mins;
				}
				sum1+=(maxs+mins);
				index1++;
			}else if(workYear.charAt(0)=='3'){
				if(index2==0){
					min2=mins;
					index2++;
				}
				if(maxs>max2){
					max2=maxs;
				}
				if(mins<min2){
					min2=mins;
				}
				sum2+=(maxs+mins);
				index2++;
			}else if(workYear.charAt(0)=='5'){
				if(index3==0){
					min3=mins;
					index3++;
				}
				if(maxs>max3){
					max3=maxs;
				}
				if(mins<min3){
					min3=mins;
				}
				sum3+=(maxs+mins);
				index3++;
			}
		}
		avg0=sum0/index0;
		avg1=sum1/index1;
		avg2=sum2/index2;
		avg3=sum3/index3;
		min[0]=min0;
		min[1]=min1;
		min[2]=min2;
		min[3]=min3;
		map.put("min", Arrays.toString(min));
		max[0]=max0;
		max[1]=max1;
		max[2]=max2;
		max[3]=max3;
		map.put("max", Arrays.toString(max));
		avg[0]=avg0;
		avg[1]=avg1;
		avg[2]=avg2;
		avg[3]=avg3;
		map.put("avg", Arrays.toString(avg));
		
		return map;
	}

	public String map2Json1(Map<String, String> map) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);
		return JSON.toJSONString(jsonObject);
	}

	public String parseCity(DBCursor cursor) {
		JSONArray array = new JSONArray();
/*		Map<String,Integer> help = new HashMap<String,Integer>();
		help.put("北京", 0);  help.put("天津", 0); help.put("上海", 0); help.put("深圳", 0); help.put("广州", 0); help.put("杭州", 0);
		help.put("成都", 0);  help.put("南京", 0); help.put("武汉", 0); help.put("西安", 0); help.put("厦门", 0); help.put("长沙", 0);
		help.put("苏州", 0);  help.put("石家庄", 0); help.put("昆明", 0); help.put("海口", 0); help.put("南昌", 0);  help.put("南宁", 0); 
		help.put("哈尔滨", 0); help.put("烟台", 0); help.put("贵阳", 0);  help.put("长春", 0); help.put("兰州", 0); */
		
		JSONObject beijin=new JSONObject(); beijin.put("name", "北京");	int bj=0;
		JSONObject tianjin=new JSONObject(); tianjin.put("name", "天津");	int tj=0;
		JSONObject shanghai=new JSONObject(); shanghai.put("name", "上海"); int sh=0;
		JSONObject shenzhen=new JSONObject(); shenzhen.put("name", "深圳"); int sz=0;
		JSONObject guangzhou=new JSONObject(); guangzhou.put("name", "广州");	int gz=0;
		JSONObject hangzhou=new JSONObject(); hangzhou.put("name", "杭州");	int hz=0;
		JSONObject chendu=new JSONObject();  chendu.put("name", "成都");		int cd=0;
		JSONObject nanjing=new JSONObject();  nanjing.put("name", "南京");	int nj=0;
		JSONObject wuhan=new JSONObject();  wuhan.put("name", "武汉");	int wh=0;
		JSONObject xian=new JSONObject();  xian.put("name", "西安");	int xa=0;
		JSONObject xiamen=new JSONObject();  xiamen.put("name", "厦门");	int xm=0;
		JSONObject changsha=new JSONObject();  changsha.put("name", "长沙");	int cs=0;
		JSONObject suzhou=new JSONObject(); suzhou.put("name", "苏州");	int szh=0;
		JSONObject shijiazhuang=new JSONObject();  shijiazhuang.put("name", "石家庄");	int sjz=0;
		JSONObject kunming=new JSONObject();  kunming.put("name", "昆明");	int km=0;
		JSONObject haikou=new JSONObject();  haikou.put("name", "海口");	int hk=0;
		JSONObject nanchang=new JSONObject();  nanchang.put("name", "南昌");	int nc=0;
		JSONObject nanning=new JSONObject(); nanning.put("name", "南宁");	int nn=0;
		JSONObject haerbing=new JSONObject(); haerbing.put("name", "哈尔滨");	int heb=0;
		JSONObject yantai=new JSONObject(); yantai.put("name", "烟台");	int yt=0;
		JSONObject guiyang=new JSONObject();  guiyang.put("name", "贵阳");	int gy=0;
		JSONObject changchun=new JSONObject();  changchun.put("name", "长春");	int cc=0;
		JSONObject lanzhou=new JSONObject();  lanzhou.put("name", "兰州");	int lz=0;
		while(cursor.hasNext()){
			DBObject o = cursor.next();
			String city=(String)o.get("city");
			System.out.print(city+"  -");
			if(city.equals("北京")){
				bj++;
				
			}else if(city.equals("天津")){
				tj++;
				
			}else if(city.equals("上海")){
				sh++;
				
			}else if(city.equals("深圳")){
				sz++;
				
			}else if(city.equals("广州")){
				gz++;
				
			}else if(city.equals("杭州")){
				hz++;
				
			}else if(city.equals("成都")){
				cd++;
				
			}else if(city.equals("南京")){
				nj++;
				
			}else if(city.equals("武汉")){
				wh++;
				
			}else if(city.equals("西安")){
				xa++;
				
			}else if(city.equals("厦门")){
				xm++;
				
			}else if(city.equals("长沙")){
				cs++;
				
			}else if(city.equals("苏州")){
				szh++;
				
			}else if(city.equals("石家庄")){
				sjz++;
				
			}else if(city.equals("昆明")){
				km++;
				
			}else if(city.equals("海口")){
				hk++;
				
			}else if(city.equals("南昌")){
				nc++;
				
			}else if(city.equals("南宁")){
				nn++;
				
			}else if(city.equals("哈尔滨")){
				heb++;
				
			}else if(city.equals("烟台")){
				yt++;
				
			}else if(city.equals("贵阳")){
				gy++;
				
			}else if(city.equals("长春")){
				cc++;
				
			}else if(city.equals("兰州")){
				lz++;
				
			}
			
		}
		beijin.put("value", bj);
		tianjin.put("value", tj);
		shanghai.put("value", sh);
		shenzhen.put("value", sz);
		guangzhou.put("value", gz);
		hangzhou.put("value", hz);
		chendu.put("value", cd);
		nanjing.put("value", nj);
		wuhan.put("value", wh);
		xian.put("value", xa);
		xiamen.put("value", xm);
		changsha.put("value", cs);
		suzhou.put("value", szh);
		shijiazhuang.put("value", sjz);
		kunming.put("value", km);
		haikou.put("value", hk);
		nanchang.put("value", nc);
		nanning.put("value", nn);
		haerbing.put("value", heb);
		guiyang.put("value", gy);
		changchun.put("value", cc);
		lanzhou.put("value", lz);
		
		array.add(beijin);
		array.add(tianjin);
		array.add(shanghai);
		array.add(shenzhen);
		array.add(guangzhou);
		array.add(hangzhou);
		array.add(chendu);
		array.add(nanjing);
		array.add(wuhan);
		array.add(xian);
		array.add(xiamen);
		array.add(changsha);
		array.add(suzhou);
		array.add(shijiazhuang);
		array.add(kunming);
		array.add(haikou);
		array.add(nanchang);
		array.add(nanning);
		array.add(haerbing);
		array.add(yantai);
		array.add(guiyang);
		array.add(changchun);
		array.add(lanzhou);
		return JSON.toJSONString(array);
		
	}
}
