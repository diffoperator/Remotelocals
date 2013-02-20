package com.acestime.proxy;


import java.util.Random;

import edu.columbia.cs.psl.remotelocals.proxy.asm.proxy.model.MyBean;
import edu.columbia.cs.psl.remotelocals.proxy.asm.proxy.model.PrintShop;
import edu.columbia.cs.psl.remotelocals.proxy.asm.proxy.service.AddFieldTransformation;
import edu.columbia.cs.psl.remotelocals.proxy.asm.proxy.service.ClassTransformation;
import edu.columbia.cs.psl.remotelocals.proxy.asm.proxy.service.MethodTransformation;
import edu.columbia.cs.psl.remotelocals.proxy.asm.proxy.service.PrintingTransformation;

import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldNode;

/**
 * Unit test for simple App.
 */
public class AsmOperationsTest {

    @Test
    public void addField() throws Exception{
        FieldNode fieldNode = new FieldNode(Opcodes.ACC_PUBLIC, "ownerName", Type.getType(String.class).getDescriptor(), null, null);
        ClassTransformation transformation = new AddFieldTransformation(fieldNode);
        Class<PrintShop> aClass = transformation.execute(PrintShop.class);

        Object obj = aClass.newInstance();
        obj.getClass().getField(fieldNode.name).set(obj, "Bruce Willis");

        System.out.println("objModified = " + obj);
    }

    @Test
    public void changeMethod() throws Exception{

        boolean valid = false;
        
        MyBean myBean = new MyBean();
        myBean.authorize(valid);

        //transform method...
        ClassTransformation transformation = new MethodTransformation();
        Class<MyBean> aClass = transformation.execute(MyBean.class);

        Object obj = aClass.newInstance();
        obj.getClass().getMethod("authorize", boolean.class).invoke(obj, valid);
    }

//    @Test
    public void testPrintClass() throws Exception{

        ClassTransformation transformation = new PrintingTransformation();
        transformation.execute(PrintShop.class);
    }
}
