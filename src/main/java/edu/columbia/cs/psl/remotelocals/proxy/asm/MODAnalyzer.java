package edu.columbia.cs.psl.remotelocals.proxy.asm;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Takes a ClassNode and generates the MOD list for each method
 * invocation. The MOD list consists of all the class variables, 
 * as well as local variables that may be modified by invoking the
 * particular method. Having the MOD list will allow us to skip
 * logging those variables that will not be modified by following
 * through with the INVOKEX byte code. 
 */
public class MODAnalyzer {
	
	private ClassNode cn;
	
	public MODAnalyzer(ClassNode cn) {
		this.cn = cn;
	}

	public void analyze() {
		for (Object o: this.cn.methods) {
			MethodNode mn = (MethodNode) o;
			
		}
	}
	
}
