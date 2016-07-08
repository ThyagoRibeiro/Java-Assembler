package constantes;

import util.Conversoes;

public enum OpcodeENUM {

	ADD(2), DEC(13), DIV(5), INC(12), JE(6), JG(10), JGE(11), JL(8), JLE(9), JNE(7), MOV(1), MUL(4), SUB(3);

	public int [] opcode;

	OpcodeENUM(int opcode) {

		this.opcode = Conversoes.string2IntArray(Conversoes.dec2bin(opcode));
		
	}

}
