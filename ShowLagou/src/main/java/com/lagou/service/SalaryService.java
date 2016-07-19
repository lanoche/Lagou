package com.lagou.service;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lagou.dao.LagouDao;
import com.lagou.util.BasicUtil;
import com.mongodb.DBCursor;

@Service
public class SalaryService {
	LagouDao dao ;
	BasicUtil util;
	@Autowired
	public void setDao(LagouDao dao) {
		this.dao = dao;
	}
	@Autowired
	public void setUtil(BasicUtil util) {
		this.util = util;
	}
	public String get(String collection){
		DBCursor cursor = dao.get(collection);
		Map<String,String> map = util.parse(cursor);
		
		for(Entry<String,String> e:map.entrySet()){
			System.out.println(e.getValue());
		}
		String s = util.map2Json1(map);
		return s;
	}
}
