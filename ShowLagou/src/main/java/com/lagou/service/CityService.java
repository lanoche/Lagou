package com.lagou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lagou.dao.LagouDao;
import com.lagou.util.BasicUtil;
import com.mongodb.DBCursor;
@Service
public class CityService {
	LagouDao dao;
	BasicUtil util;
	@Autowired
	public void setLagouDao(LagouDao dao,BasicUtil util){
		this.dao=dao;
		this.util=util;
	}
	public String get(String collection) {
		DBCursor cursor = dao.get(collection);
		String city = util.parseCity(cursor);
		return city;
	}

}
