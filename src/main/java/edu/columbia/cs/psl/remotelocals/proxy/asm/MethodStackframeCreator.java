package edu.columbia.cs.psl.remotelocals.proxy.asm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

/***
 * This class is responsible for creating the stackframe info for each method.
 * We get the MOD variables for each method which has been pre-computed by the
 * MODAnalyzer class and create an inner class out of it.
 */
public class MethodStackframeCreator {

	private ClassNode activeClass;

	private MethodNode activeMethod;

	public MethodStackframeCreator(ClassNode cn) {
		this.activeClass = cn;
		// TODO Auto-generated constructor stub
	}
	
	public void transform() {
		ClassWriter cw;
		FieldVisitor fv;
		MethodVisitor mv;

		for (Object o : activeClass.methods) {
			MethodNode mn = (MethodNode) o;
			if (mn.name.equals("<init>") || mn.name.equals("main"))
				continue;
			this.activeMethod = mn;
			/* Create the stackframe class for each method */
			cw = new ClassWriter(ClassWriter.COMPUTE_MAXS
					| ClassWriter.COMPUTE_FRAMES);

			cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
					this.activeClass.name + "_" + this.activeMethod.name
							+ "_frame", null, "java/lang/Object", null);

			/* Put in the local variables */
			for (Object o1 : this.activeMethod.localVariables) {
				LocalVariableNode lvn = (LocalVariableNode) o1;
				if (lvn.name.equals("this"))
					continue;
				fv = cw.visitField(Opcodes.ACC_PUBLIC, lvn.name, lvn.desc,
						null, null);
				fv.visitEnd();
			}

			/* Put in the class fields */
			for (Object o2 : this.activeClass.fields) {
				FieldNode fn = (FieldNode) o2;
				/* We don't put in FieldNodes which are final */
				fv = cw.visitField(Opcodes.ACC_PUBLIC, fn.name, fn.desc, null,
						null);
				fv.visitEnd();
			}

			/* Create the PC tracker */
			fv = cw.visitField(Opcodes.ACC_PUBLIC, "__pc", "I", null, null);
			fv.visitEnd();

			buildConstructor(cw);

			cw.visitEnd();

			writeClass(cw, false);
		}

		/* Create the classname_frames class */
		cw = new ClassWriter(ClassWriter.COMPUTE_MAXS
				| ClassWriter.COMPUTE_FRAMES);

		cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER,
				this.activeClass.name + "_frame", null, "java/lang/Object",
				null);

		/* Create the frames */
		for (int i = 0; i < 1000; i++) {
			fv = cw.visitField(Opcodes.ACC_PUBLIC, this.activeClass.name
					+ "_frame_" + i, "java/lang/Object", null, null);
			fv.visitEnd();
		}

		buildConstructor(cw);

		cw.visitEnd();

		writeClass(cw, true);

		return;
	}

	private void writeClass(ClassWriter cw, boolean frame) {
		/* Put it in the file */
		FileOutputStream fos;
		try {
			if (!frame)
				fos = new FileOutputStream("./bin/" + this.activeClass.name
						+ "_" + this.activeMethod.name + "_frame" + ".class");
			else
				fos = new FileOutputStream("./bin/" + this.activeClass.name
						+ "_frame" + ".class");
			fos.write(cw.toByteArray());
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildConstructor(ClassWriter cw) {
		MethodVisitor mv;
		/* Create the constructor */
		mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);

		mv.visitCode();
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>",
				"()V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();
	}
}
