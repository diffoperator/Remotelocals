package edu.columbia.cs.psl.remotelocals.proxy.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import org.objectweb.asm.Opcodes;

public class MethodPrinter extends MethodVisitor {

	public MethodPrinter(MethodVisitor arg1) {
		super(org.objectweb.asm.Opcodes.ASM4, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void visitFieldInsn(int arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		switch(arg0) {
		case Opcodes.GETFIELD:
			System.out.print("\t\tGETFIELD ");
			break;
		case Opcodes.GETSTATIC:
			System.out.print("\t\tGETSTATIC ");
			break;
		case Opcodes.PUTFIELD:
			System.out.print("\t\tPUTFIELD ");
			break;
		case Opcodes.PUTSTATIC:
			System.out.print("\t\tPUTSTATIC ");
		default:
			System.out.print(arg0);
		}
		System.out.println(arg1 + " " + arg2 + " " + arg3);
		super.visitFieldInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2,
			String arg3) {
		// TODO Auto-generated method stub
		System.out.println("\t\tTRYCATCHBLOCK " + arg0.toString() + " " + 
		arg1.toString() + " " + arg2.toString() + " " + arg3);
		super.visitTryCatchBlock(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitIincInsn(int arg0, int arg1) {
		// TODO Auto-generated method stub
		System.out.println("\t\tIINC " + arg1);
		super.visitIincInsn(arg0, arg1);
	}

	@Override
	public void visitIntInsn(int arg0, int arg1) {
		// TODO Auto-generated method stub
		switch(arg0) {
		case Opcodes.SIPUSH:
			System.out.println("\t\tSIPUSH " + arg1);
			break;
		case Opcodes.BIPUSH:
			System.out.println("\t\tBIPUSH " + arg1);
			break;
		default:
			System.out.println(arg0 + " " + arg1);
			break;
		}
		super.visitIntInsn(arg0, arg1);
	}

	@Override
	public void visitLdcInsn(Object arg0) {
		// TODO Auto-generated method stub
		System.out.println("\t\tLDC " + arg0);
		super.visitLdcInsn(arg0);
	}

	@Override
	public void visitTypeInsn(int arg0, String arg1) {
		// TODO Auto-generated method stub
		switch(arg0) {
		case Opcodes.NEW:
			System.out.println("\t\tNEW " + arg1);
			break;
		default:
			System.out.println(arg0 + " " + arg1);
			break;
		}
		super.visitTypeInsn(arg0, arg1);
	}

	@Override
	public void visitVarInsn(int arg0, int arg1) {
		// TODO Auto-generated method stub
		switch(arg0) {
		case Opcodes.AALOAD:
			System.out.println("\t\tAALOAD " + arg1);
			break;
		case Opcodes.AASTORE:
			System.out.println("\t\tAASTORE " + arg1);
			break;
		case Opcodes.ALOAD:
			System.out.println("\t\tALOAD " + arg1);
			break;
		case Opcodes.ASTORE:
			System.out.println("\t\tASTORE " + arg1);
			break;
		case Opcodes.DLOAD:
			System.out.println("\t\tDLOAD " + arg1);
			break;
		case Opcodes.DSTORE:
			System.out.println("\t\tDSTORE " + arg1);
			break;
		case Opcodes.FLOAD:
			System.out.println("\t\tFLOAD " + arg1);
			break;
		case Opcodes.FSTORE:
			System.out.println("\t\tFSTORE " + arg1);
			break;
		case Opcodes.ILOAD:
			System.out.println("\t\tILOAD " + arg1);
			break;
		case Opcodes.ISTORE:
			System.out.println("\t\tISTORE " + arg1);
			break;
		case Opcodes.LLOAD:
			System.out.println("\t\tLLOAD " + arg1);
			break;
		case Opcodes.LSTORE:
			System.out.println("\t\tLSTORE " + arg1);
			break;
		case Opcodes.FALOAD:
			System.out.println("\t\tFALOAD " + arg1);
			break;
		case Opcodes.FASTORE:
			System.out.println("\t\tFASTORE " + arg1);
			break;
		case Opcodes.IALOAD:
			System.out.println("\t\tIALOAD " + arg1);
			break;
		case Opcodes.IASTORE:
			System.out.println("\t\tIASTORE " + arg1);
			break;
		case Opcodes.DALOAD:
			System.out.println("\t\tDALOAD " + arg1);
			break;
		case Opcodes.DASTORE:
			System.out.println("\t\tDASTORE " + arg1);
			break;
		case Opcodes.LALOAD:
			System.out.println("\t\tLALOAD " + arg1);
			break;
		case Opcodes.LASTORE:
			System.out.println("\t\tLASTORE " + arg1);
			break;
		}
		super.visitVarInsn(arg0, arg1);
	}

	@Override
	public void visitJumpInsn(int arg0, Label arg1) {
		// TODO Auto-generated method stub
		switch(arg0) {
		case Opcodes.IF_ACMPEQ:
			System.out.println("\t\tIF_ACMPEQ " + arg1);
			break;
		case Opcodes.IF_ACMPNE:
			System.out.println("\t\tIF_ACMPNE " + arg1);
			break;
		case Opcodes.IF_ICMPEQ:
			System.out.println("\t\tIF_ICMPEQ " + arg1);
			break;
		case Opcodes.IF_ICMPGE:
			System.out.println("\t\tIF_ICMPGE " + arg1);
			break;
		case Opcodes.IF_ICMPGT:
			System.out.println("\t\tIF_ICMPGT " + arg1);
			break;
		case Opcodes.IF_ICMPLE:
			System.out.println("\t\tIF_ICMPLE " + arg1);
			break;
		case Opcodes.IF_ICMPLT:
			System.out.println("\t\tIF_ICMPLT " + arg1);
			break;
		case Opcodes.IF_ICMPNE:
			System.out.println("\t\tIF_ICMPNE " + arg1);
			break;
		case Opcodes.IFEQ:
			System.out.println("\t\tIF_EQ " + arg1);
			break;
		case Opcodes.IFGE:
			System.out.println("\t\tIF_GE " + arg1);
			break;
		case Opcodes.IFGT:
			System.out.println("\t\tIF_GT " + arg1);
			break;
		case Opcodes.IFLE:
			System.out.println("\t\tIF_LE " + arg1);
			break;
		case Opcodes.IFLT:
			System.out.println("\t\tIF_LT " + arg1);
			break;
		case Opcodes.IFNE:
			System.out.println("\t\tIF_NE " + arg1);
			break;
		case Opcodes.IFNONNULL:
			System.out.println("\t\tIF_NONNULL " + arg1);
			break;
		case Opcodes.IFNULL:
			System.out.println("\t\tIF_NULL " + arg1);
			break;
		case Opcodes.GOTO:
			System.out.println("\t\tGOTO " + arg1);
			break;
		default:
			System.out.println(arg0 + " " + arg1);
			break;
		}
		super.visitJumpInsn(arg0, arg1);
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		// TODO Auto-generated method stub
		System.out.println("\t\tLine: " + line + " " + start);
		super.visitLineNumber(line, start);
	}

	@Override
	public void visitLabel(Label arg0) {
		// TODO Auto-generated method stub
		super.visitLabel(arg0);
	}

	@Override
	public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
		// TODO Auto-generated method stub
		super.visitLookupSwitchInsn(arg0, arg1, arg2);
	}

	@Override
	public void visitMethodInsn(int arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case Opcodes.INVOKEINTERFACE:
			System.out.print("\t\tINVOKEINTERFACE ");
			break;
		case Opcodes.INVOKESPECIAL:
			System.out.print("\t\tINVOKESPECIAL ");
			break;
		case Opcodes.INVOKESTATIC:
			System.out.print("\t\tINVOKESTATIC ");
			break;
		case Opcodes.INVOKEVIRTUAL:
			System.out.print("\t\tINVOKEVIRTUAL ");
			break;
		default:
			System.out.print(arg0);
		}
		System.out.println(arg1 + " " + arg2 + " " + arg3);
		super.visitMethodInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitInsn(int arg) {
		switch(arg) {
		case Opcodes.AALOAD:
			System.out.println("\t\tAALOAD");
			break;
		case Opcodes.AASTORE:
			System.out.println("\t\tAASTORE");
			break;
		case Opcodes.ACONST_NULL:
			System.out.println("\t\tACONST_NULL");
			break;
		case Opcodes.ANEWARRAY:
			System.out.println("\t\tANEWARRAY");
			break;
		case Opcodes.ARETURN:
			System.out.println("\t\tARETURN");
			break;
		case Opcodes.ARRAYLENGTH:
			System.out.println("\t\tARRAYLENGTH");
			break;
		case Opcodes.ASTORE:
			System.out.println("\t\tASTORE");
			break;
		case Opcodes.ATHROW:
			System.out.println("\t\tATHROW");
			break;
		case Opcodes.BALOAD:
			System.out.println("\t\tBALOAD");
			break;
		case Opcodes.BASTORE:
			System.out.println("\t\tBASTORE");
			break;
		case Opcodes.BIPUSH:
			System.out.println("\t\tBIPUSH");
			break;
		case Opcodes.CALOAD:
			System.out.println("\t\tCALOAD");
			break;
		case Opcodes.CASTORE:
			System.out.println("\t\tCASTORE");
			break;
		case Opcodes.CHECKCAST:
			System.out.println("\t\tCHECKCAST");
			break;
		case Opcodes.D2F:
			System.out.println("\t\tD2F");
			break;
		case Opcodes.D2I:
			System.out.println("\t\tD2I");
			break;
		case Opcodes.D2L:
			System.out.println("\t\tD2L");
			break;
		case Opcodes.DADD:
			System.out.println("\t\tDADD");
			break;
		case Opcodes.DALOAD:
			System.out.println("\t\tDALOAD");
			break;
		case Opcodes.DASTORE:
			System.out.println("\t\tDASTORE");
			break;
		case Opcodes.DCMPG:
			System.out.println("\t\tDCMPG");
			break;
		case Opcodes.DCMPL:
			System.out.println("\t\tDCMPL");
			break;
		case Opcodes.DCONST_0:
			System.out.println("\t\tDCONST_0");
			break;
		case Opcodes.DCONST_1:
			System.out.println("\t\tDCONST_1");
			break;
		case Opcodes.DDIV:
			System.out.println("\t\tDDIV");
			break;
		case Opcodes.DLOAD:
			System.out.println("\t\tDLOAD");
			break;
		case Opcodes.DMUL:
			System.out.println("\t\tDMUL");
			break;
		case Opcodes.DNEG:
			System.out.println("\t\tDNEG");
			break;
		case Opcodes.DREM:
			System.out.println("\t\tDREM");
			break;
		case Opcodes.DRETURN:
			System.out.println("\t\tDRETURN");
			break;
		case Opcodes.DSTORE:
			System.out.println("\t\tDSTORE");
			break;
		case Opcodes.DSUB:
			System.out.println("\t\tDSUB");
			break;
		case Opcodes.DUP:
			System.out.println("\t\tDUP");
			break;
		case Opcodes.DUP2:
			System.out.println("\t\tDUP2");
			break;
		case Opcodes.DUP2_X1:
			System.out.println("\t\tDUP2_X1");
			break;
		case Opcodes.DUP2_X2:
			System.out.println("\t\tDUP2_X2");
			break;
		case Opcodes.DUP_X1:
			System.out.println("\t\tDUP_X1");
			break;
		case Opcodes.DUP_X2:
			System.out.println("\t\tDUP_X2");
			break;
		case Opcodes.F2D:
			System.out.println("\t\tF2D");
			break;
		case Opcodes.F2I:
			System.out.println("\t\tF2I");
			break;
		case Opcodes.F2L:
			System.out.println("\t\tF2L");
			break;
		case Opcodes.FADD:
			System.out.println("\t\tFADD");
			break;
		case Opcodes.FALOAD:
			System.out.println("\t\tFALOAD");
			break;
		case Opcodes.FASTORE:
			System.out.println("\t\tFASTORE");
			break;
		case Opcodes.FCMPG:
			System.out.println("\t\tFCMPG");
			break;
		case Opcodes.FCMPL:
			System.out.println("\t\tFCMPL");
			break;
		case Opcodes.FCONST_0:
			System.out.println("\t\tFCONST_0");
			break;
		case Opcodes.FCONST_1:
			System.out.println("\t\tFCONST_1");
			break;
		case Opcodes.FDIV:
			System.out.println("\t\tFDIV");
			break;
		case Opcodes.FLOAD:
			System.out.println("\t\tFLOAD");
			break;
		case Opcodes.FMUL:
			System.out.println("\t\tFMUL");
			break;
		case Opcodes.FNEG:
			System.out.println("\t\tFNEG");
			break;
		case Opcodes.FREM:
			System.out.println("\t\tFREM");
			break;
		case Opcodes.FRETURN:
			System.out.println("\t\tFRETURN");
			break;
		case Opcodes.FSTORE:
			System.out.println("\t\tFSTORE");
			break;
		case Opcodes.FSUB:
			System.out.println("\t\tFSUB");
			break;
		case Opcodes.F_CHOP:
			System.out.println("\t\tF_CHOP");
			break;
		case Opcodes.F_FULL:
			System.out.println("\t\tF_FULL");
			break;
		case Opcodes.F_NEW:
			System.out.println("\t\tF_NEW");
			break;
		case Opcodes.GETFIELD:
			System.out.println("\t\tGETFIELD");
			break;
		case Opcodes.GETSTATIC:
			System.out.println("\t\tGETSTATIC");
			break;
		case Opcodes.GOTO:
			System.out.println("\t\tGOTO");
			break;
		case Opcodes.IADD:
			System.out.println("\t\tIADD");
			break;
		case Opcodes.JSR:
			System.out.println("\t\tJSR");
			break;
		case Opcodes.MONITORENTER:
			System.out.println("\t\tMONITORENTER");
			break;
		case Opcodes.MONITOREXIT:
			System.out.println("\t\tMONITOREXIT");
			break;
		case Opcodes.MULTIANEWARRAY:
			System.out.println("\t\tMULTINEWARRAY");
			break;
		case Opcodes.NEW:
			System.out.println("\t\tNEW");
			break;
		case Opcodes.NEWARRAY:
			System.out.println("\t\tNEWARRAY");
			break;
		case Opcodes.POP:
			System.out.println("\t\tPOP");
			break;
		case Opcodes.POP2:
			System.out.println("\t\tPOP2");
			break;
		case Opcodes.PUTFIELD:
			System.out.println("\t\tPUTFIELD");
			break;
		case Opcodes.PUTSTATIC:
			System.out.println("\t\tPUTSTATIC");
			break;
		case Opcodes.RET:
			System.out.println("\t\tRET");
			break;
		case Opcodes.RETURN:
			System.out.println("\t\tRETURN");
			break;
		case Opcodes.SALOAD:
			System.out.println("\t\tSALOAD");
			break;
		case Opcodes.SASTORE:
			System.out.println("\t\tSASTORE");
			break;
		case Opcodes.SIPUSH:
			System.out.println("\t\tSIPUSH");
			break;
		case Opcodes.SWAP:
			System.out.println("\t\tSWAP");
			break;
		case Opcodes.T_BYTE:
			System.out.println("\t\tT_BYTE");
			break;
		case Opcodes.T_CHAR:
			System.out.println("\t\tT_CHAR");
			break;
		case Opcodes.T_DOUBLE:
			System.out.println("\t\tT_DOUBLE");
			break;
		case Opcodes.T_FLOAT:
			System.out.println("\t\tT_FLOAT");
			break;
		case Opcodes.T_INT:
			System.out.println("\t\tT_INT");
			break;
		case Opcodes.T_SHORT:
			System.out.println("\t\tT_SHORT");
			break;
		case Opcodes.TABLESWITCH:
			System.out.println("\t\tTABLESWITCH");
			break;
		case Opcodes.ISUB:
			System.out.println("\t\tISUB");
			break;
		case Opcodes.ICONST_0:
			System.out.println("\t\tICONST_0");
			break;
		case Opcodes.ICONST_1:
			System.out.println("\t\tICONST_1");
			break;
		default:
			System.out.println(arg);
			break;
		}
	}
	
	@Override
	public void visitEnd() {
		System.out.println("    } ");
	}
}
