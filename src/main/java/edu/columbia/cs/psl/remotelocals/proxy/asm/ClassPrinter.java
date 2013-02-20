package edu.columbia.cs.psl.remotelocals.proxy.asm;


import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.ASM4;

public class ClassPrinter extends ClassVisitor {

    public ClassPrinter(ClassWriter arg1) {
        super(ASM4, arg1);
    }

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {

    }

    public void visitOuterClass(String owner, String name, String desc) {
    
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {
    
    }

    public void visitInnerClass(String name, String outerName,
                                String innerName, int access) {
    	System.out.println("    " + name + " " + outerName);
    }

    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        System.out.println("    " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        System.out.println("    " + name + desc + " { ");
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodPrinter mp = new MethodPrinter(mv);
        return mp;
    }

    public void visitEnd() {
        System.out.println("}");
    }
}
