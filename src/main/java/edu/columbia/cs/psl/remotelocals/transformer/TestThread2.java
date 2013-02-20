package edu.columbia.cs.psl.remotelocals.transformer;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;

public class TestThread2 extends Thread {

	public Mongo db;

	public DB mDb;
	
	@Override
	public void run() {
		System.out.println("Running!");
		List<ServerAddress> addrs = new ArrayList<ServerAddress>();
		try {
			addrs.add(new ServerAddress("localhost", 27018));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db = new Mongo(addrs);
		mDb = db.getDB("mabel");

		DBCollection coll = mDb.getCollection("mabel");
		System.out.println(coll.count());
		DBObject myDoc = coll.findOne();
		System.out.println(myDoc);
	}

	public static void main(String[] args) {
		TestThread2 t = new TestThread2();
		try {
			t.db = new Mongo(new ServerAddress("localhost", 27018));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.run();
	}

}
