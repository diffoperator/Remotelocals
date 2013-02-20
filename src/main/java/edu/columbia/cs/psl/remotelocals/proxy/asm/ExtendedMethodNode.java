package edu.columbia.cs.psl.remotelocals.proxy.asm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;

public class ExtendedMethodNode {

	private Map<Integer, LabelNode> methodLabelIdx;

	private MethodNode mn;

	private int totalLabels;
	
	public ExtendedMethodNode(MethodNode mn) {
		this.mn = mn;
	}

	public void fillMethodLabelIdx() {
		this.totalLabels = 0;
		this.methodLabelIdx = new HashMap<Integer, LabelNode>();
		LabelNode lastLabel = null;
		int methodIdx = 0;
		for (Iterator<AbstractInsnNode> abstractInsnIterator 
				= this.mn.instructions.iterator(); 
				abstractInsnIterator.hasNext();) {
			AbstractInsnNode ainsn = abstractInsnIterator.next();
			if (ainsn instanceof LabelNode) {
				lastLabel = (LabelNode) ainsn;
				this.totalLabels++;
			} else if (ainsn instanceof MethodInsnNode) {
				if (lastLabel == null)
					continue;
				methodLabelIdx.put(methodIdx, lastLabel);
				methodIdx++;
			}
		}
	}

	public MethodNode getMethod() {
		return this.mn;
	}

	public LabelNode getMethodLabel(int index) {
		return this.methodLabelIdx.get(Integer.valueOf(index));
	}

	public int getLabelSize() {
		return this.methodLabelIdx.size();
	}
	
	public int getTotalLabels() {
		return this.totalLabels;
	}
}
