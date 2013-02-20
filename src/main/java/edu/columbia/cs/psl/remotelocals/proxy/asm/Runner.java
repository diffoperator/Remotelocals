package edu.columbia.cs.psl.remotelocals.proxy.asm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;

public class Runner extends ClassLoader {
	/***
	 * Class transformer for modifying programs for thread migration support
	 * 
	 * Current support is for classes that subclass Thread 1)
	 * 
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		ClassReader cr = new ClassReader(
				"edu.columbia.cs.psl.remotelocals.transformer.TestThread1");
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		ExtendedClassNode ecn = new ExtendedClassNode(new ClassNode());
		cr.accept(ecn.getClassNode(), ClassReader.EXPAND_FRAMES);
		ecn.fillExtendedMethodNodes();
		
		MethodStackframeCreator ica = new MethodStackframeCreator(ecn.getClassNode());
		ica.transform();
		
		TransformClass tc = new TransformClass(Opcodes.ASM4, cw, ecn);
		ecn.getClassNode().accept(tc);
		
		cr = new ClassReader(cw.toByteArray());
		ecn = new ExtendedClassNode(new ClassNode());
		cr.accept(ecn.getClassNode(), ClassReader.EXPAND_FRAMES);
		
		cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		MethodReplayInserter mri = new MethodReplayInserter(ecn.getClassNode(), cw);
		mri.getExtendedClassNode().getClassNode().accept(mri);
		
//		/* Print the class if we want to inspect it */
//		cr = new ClassReader(cw.toByteArray());
//		ClassWriter pcw = new ClassWriter(0);
//		ClassPrinter cp = new ClassPrinter(pcw);
//		cr.accept(cp, 0);
		
		/* Run the class */
		byte[] code = cw.toByteArray();

		FileOutputStream fos = new FileOutputStream("TestThread1.class");
		fos.write(code);
		fos.close();

		Runner loader = new Runner();
		Class<?> exampleClass = loader.defineClass(
				"edu.columbia.cs.psl.remotelocals.transformer.TestThread1",
				code, 0, code.length);

		/* Invoke the main method */
		try {
			exampleClass.getMethods()[0].invoke(null, new Object[] { null });
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}