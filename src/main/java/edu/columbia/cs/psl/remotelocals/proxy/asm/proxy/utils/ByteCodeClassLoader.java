package edu.columbia.cs.psl.remotelocals.proxy.asm.proxy.utils;


public class ByteCodeClassLoader extends ClassLoader{

    public Class defineClass(String name, byte[] bytes) {
        return defineClass(name, bytes, 0, bytes.length);
    }
}
