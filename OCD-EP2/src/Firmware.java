
import constantes.FlagTypeENUM;
import util.Conversoes;

public class Firmware {

	private EP2OCD ep2ocd;

	private static final int AX = 0;
	private static final int BX = 1;
	private static final int CX = 2;
	private static final int DX = 3;
	private static final int MBR = 4;
	private static final int MAR = 5;
	private static final int PC = 6;
	private static final int P1 = 7;
	private static final int P2 = 8;
	private static final int OP = 9;
	private Registrador[] registradores = new Registrador[] { 
			new Registrador(AX, 6, 7), // Reg 0
			new Registrador(BX, 8, 9), // Reg 1
			new Registrador(CX, 10, 11), // Reg 2
			new Registrador(DX, 25, 26), // Reg 3
			new Registrador(MBR, 4, 5, 22, 21), // Reg 4
			new Registrador(MAR, 3, 20), // Reg 5
			new Registrador(PC, 1, 2), // Reg 6
			new Registrador(P1, 16, 15), // Reg 7
			new Registrador(P2, 14, 13), // Reg 8
			new Registrador(OP, 0, 0) }; // Reg 9

	public Firmware(EP2OCD ep2ocd) {
		this.ep2ocd = ep2ocd;
		// ep2ocd.setFlagTableValue(FlagTypeENUM.A.column, "<");
		// registradores[AX] = new int []
		// {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		// registradores[AX] = registradores[BX];
		// ep2ocd.setLbl();
		Registrador p1 = new Registrador(AX, 6, 7);
		Registrador p2 = new Registrador(BX, 8, 9);

		p1.setDados(Conversoes.string2IntArray((Conversoes.dec2bin(10))));
		p2.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(15)));
		registradores[AX].setDados(p1.getDados());

		atualiza(p1);
		atualiza(p2);

		//ADD(p1,p2,3);
		// SUB(p1, p2, 3);
		// MUL(p2, 2);
		// INC(p1,2);
		// DEC(p1,2);

	}

	public void atualiza(Registrador p1) {
		if (p1.getIndice() == AX)
			ep2ocd.setLblAx(p1.getDados());
		if (p1.getIndice() == BX)
			ep2ocd.setLblBx(p1.getDados());
		if (p1.getIndice() == CX)
			ep2ocd.setLblCx(p1.getDados());
		if (p1.getIndice() == DX)
			ep2ocd.setLblDx(p1.getDados());
		if (p1.getIndice() == MBR)
			ep2ocd.setLblMbrValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((p1.getDados())))));
		if (p1.getIndice() == MAR)
			ep2ocd.setLblMarValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((p1.getDados())))));
		if (p1.getIndice() == PC)
			ep2ocd.setLblPcValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((p1.getDados())))));
		if (p1.getIndice() == OP)
			ep2ocd.setLblIrOpcodeValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((p1.getDados())))));
		if (p1.getIndice() == P1)
			ep2ocd.setLblIrP1Value(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((p1.getDados())))));
		if (p1.getIndice() == P2)
			ep2ocd.setLblIrP2Value(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((p1.getDados())))));
	}

	public void verificaFlag(int number) {
		if (number < 0) {
			ep2ocd.setFlagTableValue(FlagTypeENUM.S.column, "-");
		} else if (number > 0) {
			ep2ocd.setFlagTableValue(FlagTypeENUM.S.column, "+");
		} else {
			ep2ocd.setFlagTableValue(FlagTypeENUM.S.column, " ");
			ep2ocd.setFlagTableValue(FlagTypeENUM.Z.column, "0");
		}

		if (number % 2 == 0) {
			ep2ocd.setFlagTableValue(FlagTypeENUM.P.column, "1");
		} else {
			ep2ocd.setFlagTableValue(FlagTypeENUM.P.column, "0");
		}
	}

	public void MOV(Registrador p1, Registrador p2, int tipo) {

		switch (tipo) {
		case 1:
			// MAR<--IR(P1)
			registradores[MAR].setDados(registradores[P1].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR<--REG
			registradores[MBR].setDados(p2.getDados());
			// atualiza interface
			atualiza(registradores[MBR]);
			// MEMORIA<--MBR
			int mar3 =Conversoes.bin2dec(Conversoes.bin(registradores[MAR].getDados()));
			ep2ocd.memoria.atualizarLinha(mar3 , Conversoes.array2String(registradores[MBR].getDados()));
			break;
		case 2:
			// MAR <-- IR(P2)
			registradores[MAR].setDados(registradores[P2].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR <-- (memoria)
			String mar = Conversoes.bin(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
			// atualiza interface
			atualiza(registradores[MBR]);
			// REG <-- MBR
			p1.setDados(registradores[MBR].getDados());
			//Atualiza interface
			atualiza(p1);
			break;
		case 3:
			// atualiza registrador
			p1.setDados(p2.getDados());
			// atualiza interface
			atualiza(p1);
			break;
		}
	}

	public void Busca() {
		// MAR <-- PC
		registradores[MAR].setDados(registradores[PC].getDados());
		// Atualiza interface
		atualiza(registradores[PC]);
		atualiza(registradores[MAR]);
		// MBR <-- MEMORIA
		String pc = Conversoes.bin(registradores[PC].getDados());
		int endereco = Conversoes.bin2dec(pc);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
		// Atualiza interface
		atualiza(registradores[MBR]);
		// incrementa PC
		endereco++;
		registradores[PC].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(endereco)));
		// Atualiza interface
		atualiza(registradores[PC]);
		// IR <-- MBR
		int[] IR = new int[16];
		int[] OP_array = new int[16];
		int[] P1_array = new int[16];
		int[] P2_array = new int[16];
		IR = registradores[MBR].getDados();
		for (int i = 0; i < 3; i++) {
			OP_array[i] = IR[i];
		}
		int g = 0;
		for (int i = 4; i < 9; i++) {
			P1_array[g] = IR[i];
		}
		int h = 0;
		for (int i = 10; i < IR.length; i++) {
			P2_array[h] = IR[i];
		}
		registradores[OP].setDados(OP_array);
		registradores[P1].setDados(P1_array);
		registradores[P2].setDados(P2_array);
		// Atualiza interface
		atualiza(registradores[OP]);
		atualiza(registradores[P1]);
		atualiza(registradores[P2]);
	}

	public void Indirecao(int tipo) {

		switch (tipo) {
		case 1:
			// MAR <-- IR(P1)
			registradores[MAR].setDados(registradores[P1].getDados());
			// Atualiza interface
			atualiza(registradores[MAR]);
			// MBR <-- (MEMORIA)
			String mar = Conversoes.bin(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
			// Atualiza interface
			atualiza(registradores[MBR]);
			break;
		case 2:
			// MAR <-- IR(P2)
			registradores[MAR].setDados(registradores[P2].getDados());
			// Atualiza interface
			atualiza(registradores[MAR]);
			// MBR <-- (MEMORIA)
			String mar2 = Conversoes.bin(registradores[MAR].getDados());
			int endereco2 = Conversoes.bin2dec(mar2);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco2)));
			// Atualiza interface
			atualiza(registradores[MBR]);
			break;

		}
	}

	public void ADD(Registrador p1, Registrador p2, int tipo) {
		int p1_dec, p2_dec;

		switch (tipo) {
		case 1:
			// MAR <-- IR(P1)
			registradores[MAR].setDados(registradores[P1].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR<--(memória)
			String mar = Conversoes.bin(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
			// atualiza interface
			atualiza(registradores[MBR]);

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec += p2_dec;

			verificaFlag(p1_dec);

			// atualiza registrador
			registradores[MBR].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(registradores[MBR]);
			// MEMORIA <-- MBR
			// THYAGO DA UMA OLHADA AQUI
			// A POSICAO DA MEMORIA PRA MIM ADICIONAR O MBR TEM Q SER A DEFINADA
			// PELO MAR
			int mar3 =Conversoes.bin2dec(Conversoes.bin(registradores[MAR].getDados()));
			ep2ocd.memoria.atualizarLinha(mar3 , Conversoes.array2String(registradores[MBR].getDados()));
			// MEMORIA.setDados(registrador[4].getDados());

			break;
		case 2:
			// MAR <-- IR(P2)
			registradores[MAR].setDados(registradores[P2].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR <-- (memoria)
			String mar2 = Conversoes.bin(registradores[MAR].getDados());
			int endereco2 = Conversoes.bin2dec(mar2);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco2)));
			// atualiza interface
			atualiza(registradores[MBR]);

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec += p2_dec;

			verificaFlag(p1_dec);

			// atualiza registrador
			registradores[P1].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(registradores[P1]);

			break;

		case 3:
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec += p2_dec;

			verificaFlag(p1_dec);

			// atualiza registrador
			p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(p1);
			break;
		}
	}

	public void SUB(Registrador p1, Registrador p2, int tipo) {
		int p1_dec, p2_dec;

		switch (tipo) {
		case 1:
			// MAR <-- IR(P1)
			registradores[MAR].setDados(registradores[P1].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR<--(memória)
			String mar = Conversoes.bin(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
			// atualiza interface
			atualiza(registradores[MBR]);

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec -= p2_dec;

			verificaFlag(p1_dec);

			// atualiza registrador
			registradores[MBR].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(registradores[MBR]);
			// MEMORIA <-- MBR
			int mar3 =Conversoes.bin2dec(Conversoes.bin(registradores[MAR].getDados()));
			ep2ocd.memoria.atualizarLinha(mar3 , Conversoes.array2String(registradores[MBR].getDados()));

			break;
		case 2:

			// MAR <-- IR(P2)
			registradores[MAR].setDados(registradores[P2].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR <-- (memoria)
			String mar2 = Conversoes.bin(registradores[MAR].getDados());
			int endereco2 = Conversoes.bin2dec(mar2);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco2)));
			// atualiza interface
			atualiza(registradores[MBR]);

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec -= p2_dec;

			verificaFlag(p1_dec);

			// atualiza registrador
			registradores[P1].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(registradores[P1]);
			break;

		case 3:
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p2_dec = Conversoes.bin2dec(Conversoes.bin(p2.getDados()));
			p1_dec -= p2_dec;

			verificaFlag(p1_dec);

			// atualiza registrador
			p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(p1);
		}
	}

	public void MUL(Registrador p1, int tipo) {
		int ax, p1_dec;
		ax = Conversoes.bin2dec(Conversoes.bin(registradores[AX].getDados()));

		switch (tipo) {
		case 1:
			// MAR <-- IR(P1)
			registradores[MAR].setDados(registradores[P1].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR<--(memória)
			String mar = Conversoes.bin(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
			// atualiza interface
			atualiza(registradores[MBR]);

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			ax *= p1_dec;

			verificaFlag(ax);

			// atualiza registrador
			registradores[AX].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(ax)));
			// atualiza interface
			atualiza(p1);
			atualiza(registradores[AX]);
			break;

		case 2:

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			ax *= p1_dec;

			verificaFlag(ax);

			// atualiza registrador
			registradores[AX].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(ax)));

			// atualiza interface
			atualiza(registradores[AX]);
			break;
		}
	}

	public void INC(Registrador p1, int tipo) {
		int p1_dec;

		switch (tipo) {
		case 1:
			// MAR <-- IR(P1)
			registradores[MAR].setDados(registradores[P1].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR<--(memória)
			String mar = Conversoes.bin(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
			// atualiza interface
			atualiza(registradores[MBR]);

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p1_dec += 1;
			
			verificaFlag(p1_dec);

			// atualiza registrador
			p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(p1);
			break;

		case 2:
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p1_dec += 1;
			
			verificaFlag(p1_dec);

			// atualiza registrador
			p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));

			// atualiza interface
			atualiza(p1);
			break;
		}
	}

	public void DEC(Registrador p1, int tipo) {
		int p1_dec;

		switch (tipo) {
		case 1:
			// MAR <-- IR(P1)
			registradores[MAR].setDados(registradores[P1].getDados());
			// atualiza interface
			atualiza(registradores[MAR]);
			// MBR<--(memória)
			String mar = Conversoes.bin(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.memoria.getPalavra(endereco)));
			// atualiza interface
			atualiza(registradores[MBR]);

			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p1_dec -= 1;
			
			verificaFlag(p1_dec);

			// atualiza registrador
			p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			// atualiza interface
			atualiza(p1);
			break;

		case 2:
			p1_dec = Conversoes.bin2dec(Conversoes.bin(p1.getDados()));
			p1_dec -= 1;
			
			verificaFlag(p1_dec);

			// atualiza registrador
			p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));

			// atualiza interface
			atualiza(p1);
			break;
		}
	}

}