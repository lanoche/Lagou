package com.fishman.CrawerLagou1;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;


public class TestMongodb {
	public static void main(String[] args) {
		Mongo mongo = new Mongo("10.0.0.131",27017);
		DB db = mongo.getDB("test");
		DBCollection coll = db.getCollection("testColl");
		DBObject object=new BasicDBObject();
		object.put("name", "李四");  
		object.put("age",23);
		coll.insert(object);
	}
}
