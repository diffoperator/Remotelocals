package edu.columbia.cs.psl.remotelocals.proxy.asm;

import java.util.Iterator;
import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.IincInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class MethodReplayInserter extends ClassVisitor implements Opcodes {
	/* Insert replay instructions using the Tree API */
	private ExtendedClassNode cn;

	public MethodReplayInserter(ClassNode c, ClassWriter cw) {
		super(ASM4, cw);
		this.cn = new ExtendedClassNode(c);
		cn.fillExtendedMethodNodes();
	}

	public void transform() {
		for (ExtendedMethodNode emn : this.cn.getMethods()) {
			emn.fillMethodLabelIdx();
			LabelNode lastLabelNode = null;
			// if (!emn.getMethod().name.equals("<init>"))
			// this.genReplayMethodNode(emn.getMethod(), emn.getLabelSize());
			System.out.println(emn.getTotalLabels());
			// if (lastLabelNode != null)
			// emn.getMethod().instructions.insert(new
			// JumpInsnNode(Opcodes.GOTO, lastLabelNode));
			// emn.getMethodLabel(index)
		}
	}

	public MethodVisitor genReplayMethodNode(MethodNode m, int sz) {
		MethodVisitor mv = this.cv.visitMethod(m.access, m.name, m.desc,
				m.signature, null);

		/* Get the pc from the frame */
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD,
				"edu/columbia/cs/psl/remotelocals/transformer/TestThread1",
				"__pc", "I");

		// Label l1 = new Label();
		// Label l2 = new Label();
		// Label l3 = new Label();
		// Label l4 = new Label();
		// Label l5 = new Label();
		// Label l6 = new Label();
		// Label l7 = new Label();
		// mv.visitTableSwitchInsn(1, 3, l4, new Label[] { l1, l2, l3 });
		// mv.visitLabel(l1);
		// mv.visitLineNumber(8, l1);
		// mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		// mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
		// "Ljava/io/PrintStream;");
		// mv.visitLdcInsn("Case 1 jumps");
		// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
		// "(Ljava/lang/String;)V");
		// mv.visitJumpInsn(GOTO, l5);
		// mv.visitLabel(l2);
		// mv.visitLineNumber(11, l2);
		// mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		// mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
		// "Ljava/io/PrintStream;");
		// mv.visitLdcInsn("Case 2");
		// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
		// "(Ljava/lang/String;)V");
		// mv.visitLabel(l3);
		// mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		// mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
		// "Ljava/io/PrintStream;");
		// mv.visitLdcInsn("Case 3 jumps");
		// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
		// "(Ljava/lang/String;)V");
		// mv.visitJumpInsn(GOTO, l6);
		// mv.visitLabel(l4);
		// mv.visitLineNumber(17, l4);
		// mv.visitLabel(l5);
		// mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		// mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
		// "Ljava/io/PrintStream;");
		// mv.visitLdcInsn("Case 1 comes here");
		// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
		// "(Ljava/lang/String;)V");
		// mv.visitJumpInsn(GOTO, l7);
		// mv.visitLabel(l6);
		// mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		// mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
		// "Ljava/io/PrintStream;");
		// mv.visitLdcInsn("Check 3 comes here");
		// mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
		// "(Ljava/lang/String;)V");
		// mv.visitLabel(l7);
		// mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);

		String[] exceptions = new String[m.exceptions.size()];
		System.arraycopy(m.exceptions.toArray(), 0, exceptions, 0,
				m.exceptions.size());

		Label[] switchLabels = new Label[sz + 1];
		Label[] destinationLabels = new Label[sz + 1];
		for (int i = 0; i < sz + 1; i++) {
			switchLabels[i] = new Label();
			destinationLabels[i] = new Label();
		}

		/* Add bytecode here to check if we are replaying */

		/* Add bytecode here to reload the class variables */

		/* This is where we do the switch */
		mv.visitTableSwitchInsn(1, sz, switchLabels[sz], switchLabels);

		for (int i = 0; i < sz; i++) {
			mv.visitLabel(switchLabels[i]);
			mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			mv.visitJumpInsn(GOTO, destinationLabels[i]);
		}

		int destLabelIdx = 0;

		/*
		 * Things are a bit more complicated here, we need an index associated
		 * with the labels from the previous thing
		 */
		for (Iterator<?> it = m.instructions.iterator(); it.hasNext();) {
			AbstractInsnNode ain = (AbstractInsnNode) it.next();
			System.out.println(ain.toString());
			if (ain instanceof LabelNode) {
				mv.visitLabel(destinationLabels[destLabelIdx]);
				mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
				mv.visitLabel(switchLabels[destLabelIdx]);
				destLabelIdx++;
			}
			switch (ain.getType()) {
			case AbstractInsnNode.FIELD_INSN:
				FieldInsnNode fin = (FieldInsnNode) ain;
				mv.visitFieldInsn(fin.getOpcode(), fin.owner, fin.name,
						fin.desc);
				break;
			case AbstractInsnNode.FRAME:
				FrameNode frin = (FrameNode) ain;
				mv.visitFrame(frin.type, frin.local.size(),
						frin.local.toArray(), frin.stack.size(),
						frin.stack.toArray());
				break;
			case AbstractInsnNode.IINC_INSN:
				IincInsnNode iinc = (IincInsnNode) ain;
				mv.visitIincInsn(iinc.getOpcode(), iinc.incr);
				break;
			case AbstractInsnNode.INT_INSN:
				IntInsnNode iin = (IntInsnNode) ain;
				mv.visitIntInsn(iin.getOpcode(), iin.operand);
				break;
			case AbstractInsnNode.INSN:
				InsnNode in = (InsnNode) ain;
				mv.visitInsn(in.getOpcode());
				break;
			case AbstractInsnNode.JUMP_INSN:
				//TODO
				System.out.println("Jump insn!");
				break;
			case AbstractInsnNode.LABEL:
				//TODO
				//LabelNode ln = (LabelNode) ain;
				
				//mv.visitLabel(ln.getLabel());
				break;
			case AbstractInsnNode.LDC_INSN:
				LdcInsnNode lin = (LdcInsnNode) ain;
				mv.visitLdcInsn(lin.cst);
				break;
			case AbstractInsnNode.LOOKUPSWITCH_INSN:
				break;
			case AbstractInsnNode.METHOD_INSN:
				MethodInsnNode min = (MethodInsnNode) ain;
				mv.visitMethodInsn(min.getOpcode(), min.owner, min.name,
						min.desc);
				break;
			case AbstractInsnNode.MULTIANEWARRAY_INSN:
				break;
			case AbstractInsnNode.TABLESWITCH_INSN:
				break;
			case AbstractInsnNode.TYPE_INSN:
				TypeInsnNode tin = (TypeInsnNode) ain;
				mv.visitTypeInsn(tin.getOpcode(), tin.desc);
				break;
			case AbstractInsnNode.VAR_INSN:
				VarInsnNode vin = (VarInsnNode) ain;
				mv.visitVarInsn(vin.getOpcode(), vin.var);
				break;
			}
		}

//		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//		mv.visitInsn(RETURN);
		
		return mv;
	}

	public ExtendedMethodNode getCurrentMethodNode(String name, String desc) {
		for (ExtendedMethodNode emn : this.cn.getMethods()) {
			if (emn.getMethod().name.equals(name)
					&& emn.getMethod().desc.equals(desc))
				return emn;
		}
		return null;
	}

	public ExtendedClassNode getExtendedClassNode() {
		return this.cn;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		if (name.equals("<init>"))
			return super.visitMethod(access, name, desc, signature, exceptions);

		ExtendedMethodNode currentMethod = this
				.getCurrentMethodNode(name, desc);
		if (currentMethod == null || name.equals("fib") || name.equals("fact")
				|| name.equals("A") || name.equals("main"))
			return super.visitMethod(access, name, desc, signature, exceptions);

		return this.genReplayMethodNode(currentMethod.getMethod(),
				currentMethod.getTotalLabels());
	}
}
