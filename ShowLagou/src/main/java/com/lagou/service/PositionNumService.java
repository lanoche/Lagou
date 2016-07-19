package com.lagou.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lagou.dao.LagouDao;
import com.lagou.util.BasicUtil;
import com.mongodb.DBCursor;

@Service
public class PositionNumService {
	LagouDao dao ;
	BasicUtil util;
	@Autowired
	public void setDao(LagouDao dao) {
		this.dao = dao;
	}
	public String get(String collection){
		DBCursor cursor = dao.get(collection);
		Map<String,Integer> map = util.count(cursor);
		System.out.println("  ww"+util.map2Json(map));
		return util.map2Json(map);
	}
	@Autowired
	public void setUtil(BasicUtil util) {
		this.util = util;
	}
}
