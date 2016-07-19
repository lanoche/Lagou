package com.lagou.dao;

import org.springframework.stereotype.Component;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
@Component
public class LagouDao {
	
	MongoClient mongo =new MongoClient("192.168.184.128",27017);

	public DB getDB(String dbName){
		return mongo.getDB(dbName);
	}
	
	public DBCursor get(String mouth){
		DB db = this.getDB("lagouJob");
		DBCollection coll = db.getCollection(mouth);
		return coll.find();
	}
}
