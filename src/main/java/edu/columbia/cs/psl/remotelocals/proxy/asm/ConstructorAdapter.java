package edu.columbia.cs.psl.remotelocals.proxy.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class ConstructorAdapter extends AdviceAdapter {

	protected ConstructorAdapter(MethodVisitor arg1, int arg2, String arg3,
			String arg4) {
		super(ASM4, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

	protected void onMethodEnter() {
		/* Put all the logic for initializing the MongoDB replicas here */
		mv.visitTypeInsn(NEW, "java/util/ArrayList");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>",
				"()V");
		mv.visitVarInsn(ASTORE, 1);

		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(NEW, "com/mongodb/ServerAddress");
		mv.visitInsn(DUP);
		mv.visitLdcInsn("localhost");
		mv.visitIntInsn(SIPUSH, 27017);
		mv.visitMethodInsn(INVOKESPECIAL, "com/mongodb/ServerAddress",
				"<init>", "(Ljava/lang/String;I)V");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add",
				"(Ljava/lang/Object;)Z");
		mv.visitInsn(POP);

		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(NEW, "com/mongodb/ServerAddress");
		mv.visitInsn(DUP);
		mv.visitLdcInsn("localhost");
		mv.visitIntInsn(SIPUSH, 27018);
		mv.visitMethodInsn(INVOKESPECIAL, "com/mongodb/ServerAddress",
				"<init>", "(Ljava/lang/String;I)V");
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add",
				"(Ljava/lang/Object;)Z");
		mv.visitInsn(POP);

		mv.visitVarInsn(ALOAD, 0);
		mv.visitTypeInsn(NEW, "com/mongodb/Mongo");
		mv.visitInsn(DUP);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKESPECIAL, "com/mongodb/Mongo", "<init>",
				"(Ljava/util/List;)V");
		mv.visitFieldInsn(PUTFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__mongo", "Lcom/mongodb/Mongo;");

		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__mongo", "Lcom/mongodb/Mongo;");
		mv.visitLdcInsn("test_thread");
		mv.visitMethodInsn(INVOKEVIRTUAL, "com/mongodb/Mongo", "getDB",
				"(Ljava/lang/String;)Lcom/mongodb/DB;");
		mv.visitFieldInsn(PUTFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__mDB", "Lcom/mongodb/DB;");

		loadThis();
		dup();
		mv.visitFieldInsn(GETFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__mDB", "Lcom/mongodb/DB;");
		mv.visitLdcInsn("mabel");
		mv.visitMethodInsn(INVOKEVIRTUAL, "com/mongodb/DB", "getCollection",
				"(Ljava/lang/String;)Lcom/mongodb/DBCollection;");
		mv.visitFieldInsn(PUTFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__mDBCollection", "Lcom/mongodb/DBCollection;");

		loadThis();
		mv.visitTypeInsn(NEW, "com/mongodb/BasicDBObject");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, "com/mongodb/BasicDBObject",
				"<init>", "()V");
		mv.visitFieldInsn(PUTFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__dbObject", "Lcom/mongodb/BasicDBObject;");

		loadThis();
		mv.visitIntInsn(SIPUSH, 1000);
		mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
		mv.visitFieldInsn(PUTFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__frames", "[Ljava/lang/Object;");

	}
}
