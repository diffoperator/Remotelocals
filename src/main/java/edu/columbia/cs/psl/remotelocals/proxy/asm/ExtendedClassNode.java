package edu.columbia.cs.psl.remotelocals.proxy.asm;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/***
 * This class will perform whatever analysis we need for transforms to work.
 * Ideally, the MOD analyzer should go here as well, but it will probably need
 * to be done in an ad-hoc basis.
 * 
 * @author nikhil
 * 
 */
public class ExtendedClassNode {

	private ClassNode cn;

	private List<ExtendedMethodNode> extMethodNodes;

	public ExtendedClassNode(ClassNode cn) {
		super();
		this.cn = cn;
	}

	public void fillExtendedMethodNodes() {
		this.extMethodNodes = new ArrayList<ExtendedMethodNode>();
		for (Object o : this.cn.methods) {
			ExtendedMethodNode emn = new ExtendedMethodNode((MethodNode) o);
			emn.fillMethodLabelIdx();
			this.extMethodNodes.add(emn);
		}
	}

	public ExtendedMethodNode getMethod(String name, String desc) {
		for (ExtendedMethodNode emn : this.extMethodNodes) {
			if (emn.getMethod().name.equals(name)
					&& emn.getMethod().desc.equals(desc)) {
				return emn;
			}
		}
		return null;
	}
	
	public ClassNode getClassNode() {
		return this.cn;
	}
	
	public List<ExtendedMethodNode> getMethods() {
		return this.extMethodNodes;
	}
}
