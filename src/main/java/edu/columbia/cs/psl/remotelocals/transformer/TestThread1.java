package edu.columbia.cs.psl.remotelocals.transformer;

import java.net.UnknownHostException;

public class TestThread1 extends Thread {
	// public class fact_frame {
	// int n;
	// }
	// private int _privateint = 3;
	//
	// private char _privatechar = 'a';
	//
	// private short _privateshort = 100;
	//
	// private long _privatelong = 420;
	//
	// private float _privatefloat = 3.1415927f;
	//
	// private double _privatedouble = 10000;
	//
	// private String _privatestring = "Columbia";
	//
	// private Object _privateobject;

	/*
	 * public Mongo db;
	 * 
	 * public DB mDb;
	 * 
	 * @Override public void run() { System.out.println("Running!"); mDb =
	 * db.getDB("mabel");
	 * 
	 * DBCollection coll = mDb.getCollection("mabel");
	 * 
	 * BasicDBObject doc = new BasicDBObject();
	 * 
	 * doc.put("name", "MongoDB"); doc.put("type", "database"); doc.put("count",
	 * 1);
	 * 
	 * BasicDBObject info = new BasicDBObject();
	 * 
	 * info.put("x", 203); info.put("z", 102);
	 * 
	 * doc.put("info", info); coll.insert(doc);
	 * System.out.println(coll.count());
	 * 
	 * DBObject myDoc = coll.findOne(); System.out.println(myDoc); }
	 */

	// private boolean __isMain;
	//
	//private int _pcp;

	//
	// private Mongo __mongo;
	//
	// private DB __mDB;
	//
	// private DBCollection __mDBCollection;
	//
	// private BasicDBObject __dbObject;
	//
	// private Object[] __frames = new Object[1000];
	//
	// int __fill = 0;

	public TestThread1() throws UnknownHostException {
		// List<ServerAddress> addrs = new ArrayList<ServerAddress>();
		// addrs.add(new ServerAddress("localhost", 27017));
		// addrs.add(new ServerAddress("localhost", 27018));
		//
		// this.__mongo = new Mongo(addrs);
		// this.__mDB = this.__mongo.getDB("test_thread");
		// this.__mDBCollection = this.__mDB.getCollection("mabel");
		// this.__dbObject = new BasicDBObject();
		// this.__frames = new Object[1000];
	}

	// private void migrate() throws Exception {
	// // Put the framelocals in Mongo
	// this.__setpc();
	// this.__mDBCollection.insert(this.__dbObject);
	// System.out.println(this.__mDBCollection.count());
	// throw new Exception();
	// }
	//
	// private int __getprivateint() {
	// /*
	// * Get the stuff from the mongo instance; This method is to be created
	// * for each internal variable
	// */
	// return 0;
	// }
	//
	// private void __setprivateint(int value) {
	// /*
	// * Put the value in the mongo instance This method is to be created for
	// * each internal variable
	// */
	// return;
	// }
	//
	// private int __getpc() {
	// /*
	// * Get the pc from the mongo instance;
	// */
	// if (this.__isMain)
	// return 0;
	// System.out.println(this.__mDBCollection.count());
	// DBObject obj = this.__mDBCollection.findOne();
	//
	// return (int) obj.get("__pc");
	// }
	//
	// private void __setpc() {
	// /*
	// * Set the pc to the mongo instance;
	// */
	// this.__dbObject.put("__pc", this.__pc);
	// return;
	// }

	// private HashMap<Map.Entry<String, Integer>, Object> __getFrameLocals() {
	// return null;
	// }
	//
	// private void __setFrameLocals(HashMap<Map.Entry<String, Integer>, Object>
	// value) {
	// /* Set the value */
	// return;
	// }

	private int fact(int n) {
		if (n == 1)
			return 1;
		else
			return n * fact(n - 1);
	}

	private int fib(int n) {
		if (n == 1 || n == 0)
			return n;
		else
			return fib(n - 1);// + fib(n - 2);
	}

	@Override
	public void run() {
		/// if (we are reconstructing) {
		/// get current framestate and start getting all the variables
//		switch (this._pcp) {
//		case 1:
//			break;
//		case 2:
//			break;
//		case 3:
//			break;
//		case 4:
//			run();
//		default:
//			break;
//		}
//		Object startTime = System.nanoTime();
//		Object endTime = null;
//		// if (!this.__isMain) {
		// /*
		// * Set all our variables here by getting appropriate values from
		// * Mongo
		// */
		// this._privateint = this.__getprivateint();
		//
		// //this.__frameLocals = this.__getframeLocals;
		// /* Restore values */
		// /*for (Entry entry : this.__frameLocals.entrySet()) {
		// this.__localVarType = (String) ((Entry)entry.getKey()).getKey();
		// this.__localVarIndex = (int) ((Entry)entry.getKey()).getValue();
		// if (this.__localVarType.equals("I")) {
		// //BIPUSH entry.value
		// //ISTORE
		// //_internalint = (int)entry.getValue();
		// }
		// }*/
		//
		// /*
		// * Switch case here that lets us know where to jump after we have
		// * been given the artificial PC.
		// */
		// this.__pc = this.__getpc();
		// switch (__pc) {
		// case 1:
		// //break;
		// System.out.println("PC is one, we would have jumped");
		// break;
		// case 2:
		// // goto
		// break;
		// default:
		// // throwexception
		// break;
		// }
		// }
		//
		//System.out.println(this._pc);
		
		fact(10);
		// System.out.println(fact(10));
		//System.out.println(fib(30));
		// __fs.cvDesc.put("I", this._privateint);
		// System.out.println("Current thread id is: " + this.getId());

		fact(5);
			
		/*
		 * Go back to the top OR exit, for now we exit.
		 */
		/*
		 * try { migrate(); } catch (Exception e) { return; }
		 */

		// System.out.println("Current thread id is: " + this.getId() + "\n"
		// + "and isMain is: " + this.__isMain);
		//
		// System.out.println("Printing values");
		//
		// System.out.println(this._privateint);
		//endTime = System.nanoTime();
		//
		//System.out.println((long) endTime - (long) startTime);
	}

	public static void main(String[] args) throws UnknownHostException {
		String test = "primary";
		TestThread1 t = new TestThread1();
		// if (test.equals("primary")) {
		// t._privateint = 42;
		// t._privatechar = '.';
		// t._privatelong = 0xCAFEBABE;
		// t._privateshort = 666;
		// t._privatestring = "Did you notice a sign in front of my house...";
		// t._privatefloat = 3.14159f;
		// t._privatefloat = t._privatefloat * 2;
		// // t.__isMain = true;
		// } else
		// // t.__isMain = false;
		// System.out.println("Main run");
		t.run();

	}
}
