package edu.columbia.cs.psl.remotelocals.proxy.asm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import edu.columbia.cs.psl.remotelocals.transformer.TestThread1;

public class TransformClass extends ClassVisitor {

	@Override
	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
		// TODO Auto-generated method stub
		System.out.println("Visiting inner class");
		super.visitInnerClass(name, outerName, innerName, access);
	}

	private ExtendedClassNode ecn;

	public void setExtendedClassNode(ExtendedClassNode c) {
		this.ecn = c;
	}

	private MethodNode getMethodNode(String name, String desc) {
		for (Object mn : ecn.getClassNode().methods) {
			if (((MethodNode) mn).name.equals(name)
					&& ((MethodNode) mn).desc.equals(desc))
				return (MethodNode) mn;
		}
		return null;
	}

	public TransformClass(int arg1, ClassWriter arg2, ExtendedClassNode arg3) {
		super(arg1, arg2);
		setExtendedClassNode(arg3);
		// TODO Auto-generated constructor stub
	}

	private static Map<String, String> existingFields = new HashMap<String, String>();

	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		existingFields.put(name, desc);
		return cv.visitField(access, name, desc, signature, value);
	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		if (name.equals("main"))
			return mv;

		MethodNode mn = new MethodNode();
		if (name.equals("<init>"))
			mv = new ConstructorAdapter(mv, access, name, desc);
		else {
			mn = this.getMethodNode(name, desc);
			mv = new MethodLoggingAdapter(mv, access, name, desc, mn, this.ecn);
		}
		return mv;
	}

	@Override
	public void visitEnd() {
		FieldVisitor fv;

		/* Create the MongoDB and other book-keeping variables */
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__isMain", "Z", null, null);
		fv.visitEnd();
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__pc", "I", null, null);
		fv.visitEnd();
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__mongo",
				"Lcom/mongodb/Mongo;", null, null);
		fv.visitEnd();
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__mDB", "Lcom/mongodb/DB;",
				null, null);
		fv.visitEnd();
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__mDBCollection",
				"Lcom/mongodb/DBCollection;", null, null);
		fv.visitEnd();
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__dbObject",
				"Lcom/mongodb/BasicDBObject;", null, null);
		fv.visitEnd();

		/* Stack frame storage array */
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__frames",
				"[Ljava/lang/Object;", null, null);
		fv.visitEnd();
		fv = cv.visitField(Opcodes.ACC_PRIVATE, "__frames_fill", "I", null, 0);
		fv.visitEnd();

		cv.visitEnd();
	}
}
