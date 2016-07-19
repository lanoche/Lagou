package com.fishman.CrawerLagou1.Dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDao {
	private final MongoClient mongo;
	private DB db;
	public MongoDao(){
		mongo=new MongoClient("10.0.0.131",27017);
	}
	
	public void insert(String dbName,String collName,DBObject doc){
		if(db==null){
			db = mongo.getDB(dbName);
		}
		DBCollection coll = db.getCollection(collName);
		if(coll==null){
			DBObject option = new BasicDBObject();
			option.put("size", 20);
			option.put("capped", 5);
			option.put("max", 100);
			coll=db.createCollection(collName,option);
		}
		coll.insert(doc);
	}
	
	public void remove(String dbName,String collName){
		mongo.getDB(dbName).getCollection(collName).drop();
	}
}
