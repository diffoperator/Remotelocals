package edu.columbia.cs.psl.remotelocals.proxy.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import org.objectweb.asm.Opcodes;

public class MethodLoggingAdapter extends AdviceAdapter {

	private MethodNode mn;

	private ExtendedClassNode parent;

	private int frameStateIdx;
	
	private int methodStackLoadIdx;

	private String frameName;

	private int pcTracker = 0;
	
	private String name;
	
	@Override
	public void visitLabel(Label label) {
		// TODO Auto-generated method stub
		pcTracker++;
		super.visitLabel(label);
	}
	
	protected MethodLoggingAdapter(MethodVisitor arg1, int arg2, String arg3,
			String arg4, MethodNode mn, ExtendedClassNode pn) {
		super(ASM4, arg1, arg2, arg3, arg4);
		this.mn = mn;
		this.name = arg3;
		this.parent = pn;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMethodEnter() {
		// TODO Auto-generated method stub
		super.onMethodEnter();
		this.frameStateIdx = mn.localVariables.size() + 1;
		this.frameName = "edu/columbia/cs/psl/remotelocals/transformer/TestThread1_"
				+ mn.name + "_frame";
		mv.visitTypeInsn(NEW, this.frameName);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, this.frameName, "<init>", "()V");
		mv.visitVarInsn(ASTORE, frameStateIdx);
	}
	
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		/* Don't bother logging API calls since we cannot migrate from there */
		if (owner.startsWith("java")) {
			super.visitMethodInsn(opcode, owner, name, desc);
			return;
		}
		
		/* Let the logging code commence! */
		Label methodLoggingVisitEnd = new Label();

		/* Log the values of the local variables */
		for (Object o : this.mn.localVariables) {
			LocalVariableNode lvn = (LocalVariableNode) o;
			if (lvn.name.equals("this") || lvn.desc.contains("Object"))
				continue;

			mv.visitVarInsn(ALOAD, frameStateIdx);
			if ("IZBCS".contains(lvn.desc)) {
				mv.visitVarInsn(ILOAD, lvn.index);
			} else if (lvn.desc.equals("F")) {
				mv.visitVarInsn(FLOAD, lvn.index);
			} else if (lvn.desc.equals("D")) {
				mv.visitVarInsn(DLOAD, lvn.index);
			} else if ("LJ".contains(lvn.desc)) {
				// TODO: Storing long given a String error. Why?
				mv.visitVarInsn(LLOAD, lvn.index);
			} else {
				// We should probably clone a copy here
				mv.visitVarInsn(ALOAD, lvn.index);
			}
			mv.visitFieldInsn(PUTFIELD,
					"edu/columbia/cs/psl/remotelocals/transformer/TestThread1_"
							+ mn.name + "_frame", lvn.name, lvn.desc);
		}

		/* Log the values of the class variables */
		for (Object o : this.parent.getClassNode().fields) {
			FieldNode f = (FieldNode) o;

			mv.visitVarInsn(ALOAD, frameStateIdx);
			loadThis();
			mv.visitFieldInsn(GETFIELD,
					"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
					f.name, f.desc);
			mv.visitFieldInsn(PUTFIELD,
					"edu/columbia/cs/psl/remotelocals/transformer/TestThread1_"
							+ mn.name + "_frame", f.name, f.desc);
		}
		
		/*
		 * Increment the pc.
		 */
		loadThis();
		mv.visitIntInsn(SIPUSH, this.pcTracker);
		mv.visitFieldInsn(PUTFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__pc", "I");

		mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, "edu/columbia/cs/psl/remotelocals/transformer/TestThread1", "__pc", "I");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V");
		
		/* Store in frame state array */
		loadThis();
		mv.visitFieldInsn(GETFIELD, this.parent.getClassNode().name, "__frames",
				"[Ljava/lang/Object;");
		loadThis();
		mv.visitFieldInsn(GETFIELD, this.parent.getClassNode().name, "__frames_fill", "I");
		mv.visitVarInsn(ALOAD, this.frameStateIdx);
		mv.visitInsn(AASTORE);

		/* Increment fill */
		loadThis();
		dup();
		mv.visitFieldInsn(GETFIELD, this.parent.getClassNode().name, "__frames_fill", "I");
		mv.visitInsn(ICONST_1);
		mv.visitInsn(IADD);
		mv.visitFieldInsn(PUTFIELD, this.parent.getClassNode().name, "__frames_fill", "I");

		super.visitMethodInsn(opcode, owner, name, desc);

		/* Decrement fill */
		loadThis();
		dup();
		mv.visitFieldInsn(GETFIELD, this.parent.getClassNode().name, "__frames_fill", "I");
		mv.visitInsn(ICONST_1);
		mv.visitInsn(ISUB);
		mv.visitFieldInsn(PUTFIELD, this.parent.getClassNode().name, "__frames_fill", "I");

		/* Remove from frame state array */
		// We just let it be for now...
		
		/* This is to ensure unique labels for all methods */
		mv.visitLabel(methodLoggingVisitEnd);
	}


	@Override
	public void visitLineNumber(int line, Label start) {
		// TODO Auto-generated method stub
		this.methodStackLoadIdx = line;
		super.visitLineNumber(line, start);
	}
}
