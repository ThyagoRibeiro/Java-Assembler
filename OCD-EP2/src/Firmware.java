
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import constantes.FlagTypeENUM;
import constantes.OpcodeENUM;
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
	int[] OP_array = new int[4];
	int[] P1_array = new int[6];
	int[] P2_array = new int[6];
	private Registrador[] registradores = new Registrador[] { new Registrador(AX, 6, 7), new Registrador(BX, 8, 9),
			new Registrador(CX, 10, 11), new Registrador(DX, 25, 26), new Registrador(MBR, 4, 5, 22, 21),
			new Registrador(MAR, 3, 20), new Registrador(PC, 1, 2), new Registrador(P1, 16, 15),
			new Registrador(P2, 14, 13), new Registrador(OP, 0, 0) };
	private ArrayList<String> arrayPalavras = new ArrayList<>();
	private HashMap<String, Integer> labels = new HashMap<>();
	private int CMP, comecoCodigo;

	public Firmware(EP2OCD ep2ocd) {
		this.ep2ocd = ep2ocd;
		// ep2ocd.setFlagTableValue(FlagTypeENUM.A.column, "<");
		// registradores[AX] = new int []
		// {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		// registradores[AX] = registradores[BX];
		// ep2ocd.setLbl();
		/*
		 * Registrador p1 = new Registrador(AX, 6, 7); Registrador p2 = new
		 * Registrador(BX, 8, 9);
		 * 
		 * p1.setDados(Conversoes.string2IntArray((Conversoes.dec2bin(10))));
		 * p2.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(25)));
		 * registradores[AX].setDados(p1.getDados());
		 * 
		 * atualiza(p1); atualiza(p2);
		 */

		// ADD(p1,p2,3);
		// SUB(p1, p2, 3);
		// MUL(p2, 2);
		// INC(p1,2);
		// DEC(p1,2);
		// MOV(p1,p2,3);

		/*
		 * ep2ocd.adicionarLinha(
		 * "{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}");
		 * ep2ocd.adicionarLinha(
		 * "{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}");
		 */

		// cicloInstrucao();
	}

	public void atualiza(Registrador p1) {

		int[] dados = p1.getDados();

		if (p1.isDadoNegativo())
			dados = Conversoes.changeSizeArray(dados, 32, 1);

		if (p1.getIndice() == AX)
			ep2ocd.setLblAx(dados);
		if (p1.getIndice() == BX)
			ep2ocd.setLblBx(dados);
		if (p1.getIndice() == CX)
			ep2ocd.setLblCx(dados);
		if (p1.getIndice() == DX)
			ep2ocd.setLblDx(dados);
		if (p1.getIndice() == MBR)
			ep2ocd.setLblMbrValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((dados)))));
		if (p1.getIndice() == MAR)
			ep2ocd.setLblMarValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((dados)))));
		if (p1.getIndice() == PC)
			ep2ocd.setLblPcValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((dados)))));
		if (p1.getIndice() == OP)
			ep2ocd.setLblIrOpcodeValue(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((dados)))));
		if (p1.getIndice() == P1)
			ep2ocd.setLblIrP1Value(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((dados)))));
		if (p1.getIndice() == P2)
			ep2ocd.setLblIrP2Value(Integer.toString(Conversoes.bin2dec(Conversoes.array2String((dados)))));
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

		if ((long) number > (49 * 10 ^ 10)) {
			ep2ocd.setFlagTableValue(FlagTypeENUM.C.column, "1");
		} else {
			ep2ocd.setFlagTableValue(FlagTypeENUM.C.column, " ");
		}
	}

	public void busca() {
		// MAR <-- PC
		registradores[MAR].setDados(registradores[PC].getDados());
		// Atualiza interface
		atualiza(registradores[MAR]);
		// MBR <-- MEMORIA
		String pc = Conversoes.array2String(registradores[PC].getDados());
		int endereco = Conversoes.bin2dec(pc);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco)));
		// Atualiza interface
		atualiza(registradores[MBR]);
		// incrementa PC
		endereco++;
		registradores[PC].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(endereco)));
		// Atualiza interface
		atualiza(registradores[PC]);
		// IR <-- MBR
		int[] IR = registradores[MBR].getDados();

		for (int i = 0; i < 4; i++) {
			OP_array[i] = IR[i];
		}
		int g = 0;

		for (int i = 4; i < 10; i++) {
			P1_array[g] = IR[i];
			g++;
		}
		int h = 0;
		for (int i = 10; i < IR.length; i++) {
			P2_array[h] = IR[i];
			h++;
		}

		registradores[OP].setDados(OP_array);
		registradores[P1].setDados(
				Conversoes.string2IntArray(ep2ocd.getPalavra(Conversoes.bin2dec(Conversoes.array2String(P1_array)))));
		registradores[P2].setDados(
				Conversoes.string2IntArray(ep2ocd.getPalavra(Conversoes.bin2dec(Conversoes.array2String(P2_array)))));

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
			String mar = Conversoes.array2String(registradores[MAR].getDados());
			int endereco = Conversoes.bin2dec(mar);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco)));
			// Atualiza interface
			atualiza(registradores[MBR]);
			break;
		case 2:
			// MAR <-- IR(P2)
			registradores[MAR].setDados(registradores[P2].getDados());
			// Atualiza interface
			atualiza(registradores[MAR]);
			// MBR <-- (MEMORIA)
			String mar2 = Conversoes.array2String(registradores[MAR].getDados());
			int endereco2 = Conversoes.bin2dec(mar2);
			registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco2)));
			// Atualiza interface
			atualiza(registradores[MBR]);
			break;

		}
	}

	public void MOV(Registrador p1, Registrador p2, int tipo) {
		int p1_dec, p2_dec;

		// MAR <-- IR(P2)
		registradores[MAR].setDados(P2_array);
		// atualiza interface
		atualiza(registradores[MAR]);
		// MBR <-- (memoria)
		String mar2 = Conversoes.array2String(registradores[MAR].getDados());
		int endereco2 = Conversoes.bin2dec(mar2);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco2)));
		// atualiza interface
		atualiza(registradores[MBR]);

		p1_dec = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));
		p2_dec = Conversoes.bin2dec(Conversoes.array2String(p2.getDados()));
		p1_dec = p2_dec;

		verificaFlag(p1_dec);

		// atualiza registrador
		registradores[P1].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));

		// System.out.println("tipo: " + tipo);
		//
		// System.out.println("opa " +
		// Conversoes.bin2dec(Conversoes.array2String(P1_array)));

		// atualiza interface
		atualiza(registradores[P1]);

		if (tipo == 0) {

			registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))].setDados(registradores[P1].getDados());
			atualiza(registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))]);
			atualiza(registradores[P1]);
		}
		// ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
		// Conversoes.array2String(Conversoes.string2IntArray(Conversoes.addZeros(Conversoes.array2String(registradores[P1].getDados()),
		// 16)), ", "));
		ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
				Conversoes.array2String(Conversoes.changeSizeArray(registradores[P1].getDados(), 16, 0), ", "));

	}

	public void ADD(Registrador p1, Registrador p2, int tipo) {
		int p1_dec, p2_dec;

		// MAR <-- IR(P2)
		registradores[MAR].setDados(P2_array);
		// atualiza interface
		atualiza(registradores[MAR]);
		// MBR <-- (memoria)
		String mar2 = Conversoes.array2String(registradores[MAR].getDados());
		int endereco2 = Conversoes.bin2dec(mar2);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco2)));
		// atualiza interface
		atualiza(registradores[MBR]);

		p1_dec = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));
		p2_dec = Conversoes.bin2dec(Conversoes.array2String(p2.getDados()));
		p1_dec += p2_dec;

		verificaFlag(p1_dec);

		// atualiza registrador
		registradores[P1].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));

		// System.out.println("tipo: " + tipo);
		//
		// System.out.println("opa " +
		// Conversoes.bin2dec(Conversoes.array2String(P1_array)));

		// atualiza interface
		atualiza(registradores[P1]);

		if (tipo == 0) {

			registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))].setDados(registradores[P1].getDados());
			atualiza(registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))]);
			atualiza(registradores[P1]);
		}
		// ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
		// Conversoes.array2String(Conversoes.string2IntArray(Conversoes.addZeros(Conversoes.array2String(registradores[P1].getDados()),
		// 16)), ", "));
		ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
				Conversoes.array2String(Conversoes.changeSizeArray(registradores[P1].getDados(), 16, 0), ", "));

	}

	public void SUB(Registrador p1, Registrador p2, int tipo) {
		int p1_dec, p2_dec;

		// MAR <-- IR(P2)
		registradores[MAR].setDados(P2_array);
		// atualiza interface
		atualiza(registradores[MAR]);
		// MBR <-- (memoria)
		String mar2 = Conversoes.array2String(registradores[MAR].getDados());
		int endereco2 = Conversoes.bin2dec(mar2);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco2)));
		// atualiza interface
		atualiza(registradores[MBR]);

		p1_dec = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));
		p2_dec = Conversoes.bin2dec(Conversoes.array2String(p2.getDados()));
		p1_dec -= p2_dec;

		verificaFlag(p1_dec);

		// atualiza registrador
		registradores[P1]
				.setDados(Conversoes.changeSizeArray(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)), 16, 0));

		int[] valor = Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec));

		if (valor.length > 16) {
			valor = Conversoes.changeSizeArray(valor, 16, 0);
			registradores[P1].setDadoNegativo(true);

			if (tipo == 0) {
				registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))].setDadoNegativo(true);
			}

		}

		// System.out.println("tipo: " + tipo);
		//
		// System.out.println("opa " +
		// Conversoes.bin2dec(Conversoes.array2String(P1_array)));

		// atualiza interface

		atualiza(registradores[P1]);

		if (tipo == 0) {

			registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))].setDados(registradores[P1].getDados());
			atualiza(registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))]);
			atualiza(registradores[P1]);

		}
		// ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
		// Conversoes.array2String(Conversoes.string2IntArray(Conversoes.addZeros(Conversoes.array2String(registradores[P1].getDados()),
		// 16)), ", "));
		ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
				Conversoes.array2String(Conversoes.changeSizeArray(registradores[P1].getDados(), 16, 0), ", "));

		if (registradores[P1].isDadoNegativo())
			registradores[P1].setDadoNegativo(false);
	}

	public void MUL(Registrador p1, int tipo) {

		int ax, p1_dec;
		ax = Conversoes.bin2dec(Conversoes.array2String(registradores[AX].getDados()));

		// MAR <-- IR(P1)
		registradores[MAR].setDados(registradores[P1].getDados());
		// atualiza interface
		atualiza(registradores[MAR]);
		// MBR<--(memória)
		String mar = Conversoes.array2String(registradores[MAR].getDados());
		int endereco = Conversoes.bin2dec(mar);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco)));
		// atualiza interface
		atualiza(registradores[MBR]);

		p1_dec = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));
		ax *= p1_dec;

		verificaFlag(ax);

		// atualiza registrador
		registradores[AX].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(ax)));
		// atualiza interface
		atualiza(p1);
		atualiza(registradores[AX]);

	}

	public void DIV(Registrador p1, int tipo) {
		int ax, temp, p1_dec;
		ax = Conversoes.bin2dec(Conversoes.array2String(registradores[AX].getDados()));
		temp = ax;
		// MAR <-- IR(P1)
		registradores[MAR].setDados(registradores[P1].getDados());
		// atualiza interface
		atualiza(registradores[MAR]);
		// MBR<--(memória)
		String mar = Conversoes.array2String(registradores[MAR].getDados());
		int endereco = Conversoes.bin2dec(mar);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco)));
		// atualiza interface
		atualiza(registradores[MBR]);

		p1_dec = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));

		ax /= p1_dec;
		// atualiza registrador
		registradores[AX].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(ax)));

		registradores[DX].setDados(Conversoes.int2array(temp % p1_dec));

		verificaFlag(ax);

		// atualiza interface
		atualiza(p1);
		atualiza(registradores[AX]);
		atualiza(registradores[DX]);

	}

	public void INC(Registrador p1, int tipo) {

		int p1_dec;

		p1_dec = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));

		// MAR <-- IR(P1)
		registradores[MAR].setDados(registradores[P1].getDados());
		// atualiza interface
		atualiza(registradores[MAR]);
		// MBR<--(memória)
		String mar = Conversoes.array2String(registradores[MAR].getDados());
		int endereco = Conversoes.bin2dec(mar);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco)));
		// atualiza interface
		atualiza(registradores[MBR]);

		p1_dec++;
		p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));

		verificaFlag(p1_dec);

		atualiza(registradores[P1]);

		// atualiza registrador
		if (tipo == 0) {
			registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))]
					.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			atualiza(registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))]);
		} else {
			ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
					Conversoes.array2String(Conversoes.changeSizeArray(registradores[P1].getDados(), 16, 0), ", "));

		}

	}

	public void DEC(Registrador p1, int tipo) {
		int p1_dec;

		p1_dec = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));

		// MAR <-- IR(P1)
		registradores[MAR].setDados(registradores[P1].getDados());
		// atualiza interface
		atualiza(registradores[MAR]);
		// MBR<--(memória)
		String mar = Conversoes.array2String(registradores[MAR].getDados());
		int endereco = Conversoes.bin2dec(mar);
		registradores[MBR].setDados(Conversoes.string2IntArray(ep2ocd.getPalavra(endereco)));
		// atualiza interface
		atualiza(registradores[MBR]);

		p1_dec--;
		p1.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));

		verificaFlag(p1_dec);

		atualiza(registradores[P1]);

		// atualiza registrador
		if (tipo == 0) {
			registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))]
					.setDados(Conversoes.string2IntArray(Conversoes.dec2bin(p1_dec)));
			atualiza(registradores[Conversoes.bin2dec(Conversoes.array2String(P1_array))]);
		} else {
			ep2ocd.atualizarLinha(Conversoes.bin2dec(Conversoes.array2String(P1_array)),
					Conversoes.array2String(Conversoes.changeSizeArray(registradores[P1].getDados(), 16, 0), ", "));

		}
	}

	public void CMP(Registrador p1, Registrador p2, int tipo) {
		// P1 - P2
		int p1_decimal = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));
		int p2_decimal = Conversoes.bin2dec(Conversoes.array2String(p2.getDados()));
		switch (tipo) {
		// memoria - registrador
		case 1:
			int compara = Integer.parseInt((ep2ocd.getPalavra(p1_decimal)));
			CMP = compara - p2_decimal;
			verificaFlag(CMP);
			break;
		// registrador - memoria
		case 2:
			int compara2 = Integer.parseInt((ep2ocd.getPalavra(p2_decimal)));
			CMP = p1_decimal - compara2;
			verificaFlag(CMP);
			break;
		// registrador - registrador
		case 3:
			p1_decimal = Conversoes.bin2dec(Conversoes.array2String(p1.getDados()));
			p2_decimal = Conversoes.bin2dec(Conversoes.array2String(p2.getDados()));
			CMP = p1_decimal - p2_decimal;
			verificaFlag(CMP);
			break;
		}

	}

	public void jnz() {

		if (ep2ocd.getFlagTableValue(6) == null) {
			// PULA PRA LABEL
		}

	}

	public void jz() {
		if (ep2ocd.getFlagTableValue(6) != null) {
			// PULA PRA LABEL
		}
	}

	public void jle() {
		if (CMP <= 0) {
			// PULA PRA LABEL
		}
	}

	public void jl() {
		if (CMP < 0) {
			// PULA PRA LABEL
		}
	}

	public void jge() {
		if (CMP >= 0) {
			// PULA PRA LABEL
		}
	}

	public void jg() {
		if (CMP > 0) {
			// PULA PRA LABEL
		}
	}

	public void jne() {
		if (CMP != 0) {
			// PULA PRA LABEL
		}
	}

	public void je() {
		if (CMP == 0) {
			// PULA PRA LABEL
		}
	}

	public void atualizaPc(int i) {
		registradores[PC].setDados(Conversoes.string2IntArray(Conversoes.dec2bin(i)));
	}

	public void executa_opcode(int opcode, int tipo, int indirecao) {
		MicroOperacoes microop = new MicroOperacoes(opcode, registradores[P1], registradores[P2], indirecao);

		if (opcode == 1) {
			MOV(registradores[P1], registradores[P2], tipo);
			microop.Execucao();
		}
		if (opcode == 2) {
			ADD(registradores[P1], registradores[P2], tipo);
			microop.Execucao();
		}
		if (opcode == 3) {
			SUB(registradores[P1], registradores[P2], tipo);
			microop.Execucao();
		}
		if (opcode == 4) {
			MUL(registradores[P1], tipo);
			microop.Execucao();
		}
		if (opcode == 5) {
			DIV(registradores[P1], tipo);
			microop.Execucao();
		}
		if (opcode == 6) {
			
			microop.Execucao();
		}
		if (opcode == 7) {
			// JNE;
			microop.Execucao();
		}
		if (opcode == 8) {
			// Jl;
			microop.Execucao();
		}
		if (opcode == 9) {
			// JlE;
			microop.Execucao();
		}
		if (opcode == 10) {
			// Jg;
			microop.Execucao();
		}
		if (opcode == 11) {
			// JgE;
			microop.Execucao();
		}
		if (opcode == 12) {
			INC(registradores[P1], tipo);
			microop.Execucao();
		}
		if (opcode == 13) {
			// DEC;
			DEC(registradores[P1], tipo);
			microop.Execucao();
		}
	}

	public void cicloInstrucao() {

		busca();
		MicroOperacoes microop_busca = new MicroOperacoes(0, registradores[P1], registradores[P2], 0);
		microop_busca.Busca();

		int p1_dec = Conversoes.bin2dec(Conversoes.array2String(registradores[P1].getDados()));
		int p2_dec = Conversoes.bin2dec(Conversoes.array2String(registradores[P2].getDados()));
		int opcode = Conversoes.bin2dec(Conversoes.array2String(registradores[OP].getDados()));

		// COM APENAS UM PARAMETRO
		if (Conversoes.bin2dec(Conversoes.array2String(P1_array)) < 4) {
			// registrador tipo 0
			executa_opcode(opcode, 0, 2);

		} else {
			// memoria ou constante tipo 1
			executa_opcode(opcode, 1, 0);
		}

	}

	public void codigoParaPalavra(String codigo, int n) {

		int[] palavra = new int[16];
		String opcodeString = null, p1String = null, p2String = null;

		opcodeString = codigo.split(" ")[0];

		if (Conversoes.array2String(Conversoes.changeSizeArray(descobrirOpcode(opcodeString), 4, 0)).equals(Conversoes.array2String(Conversoes
				.string2IntArray("0000")))) {
			// verifica se é label
			System.out.println("é label");
			labels.put(opcodeString, n);

		} else {

			if (codigo.split(" ").length >= 2) {

				p1String = codigo.split(" ")[1].replace(",", "");

				palavra = preencherArray(palavra, Conversoes.changeSizeArray(descobrirOpcode(opcodeString), 4, 0), 0);

				if (p1String.contains("x")) {
					// trata registrador

					palavra = preencherArray(palavra, Conversoes.changeSizeArray(descobrirRegistrador(p1String), 6, 0),
							4);
				} else if (p1String.matches("[0-9]+") || p1String.toUpperCase().contains("H")
						|| p1String.toUpperCase().contains("B") || p1String.toUpperCase().contains("D")) {
					// trata constante

					int numero = 0;
					if (p1String.toUpperCase().contains("H"))
						numero = Conversoes.hex2dec(p1String.toUpperCase().replace("H", ""));
					else if (p1String.toUpperCase().contains("B"))
						numero = Conversoes.bin2dec(p1String.toUpperCase().replace("H", ""));
					else
						numero = Integer.parseInt(p1String.toUpperCase().replace("D", ""));

					int endereco = ep2ocd.adicionarLinha(Conversoes.array2String(
							Conversoes.changeSizeArray(Conversoes.string2IntArray(Conversoes.dec2bin(numero)), 16, 0),
							", "));

					palavra = preencherArray(palavra,
							Conversoes.changeSizeArray(Conversoes.string2IntArray(Conversoes.dec2bin(endereco)), 6, 0),
							4);
				} else {
					// trata endereco de memoria

					palavra = preencherArray(palavra,
							Conversoes.changeSizeArray(
									Conversoes.string2IntArray(Conversoes
											.dec2bin(Integer.parseInt(p1String.replace("[", "").replace("]", "")))),
									6, 0),
							4);
				}
			}

			if (codigo.split(" ").length == 3) {

				p2String = codigo.split(" ")[2];

				if (p2String != null)
					if (p2String.contains("x")) {
						// trata registrador
						palavra = preencherArray(palavra,
								Conversoes.changeSizeArray(descobrirRegistrador(p2String), 6, 0), 10);
					} else if (p2String.matches("[0-9]+") || p2String.toUpperCase().contains("H")
							|| p2String.toUpperCase().contains("B") || p2String.toUpperCase().contains("D")) {
						// trata constante

						int numero = 0;
						if (p2String.toUpperCase().contains("H"))
							numero = Conversoes.hex2dec(p2String.toUpperCase().replace("H", ""));
						else if (p2String.toUpperCase().contains("B"))
							numero = Conversoes.bin2dec(p2String.toUpperCase().replace("H", ""));
						else
							numero = Integer.parseInt(p2String.toUpperCase().replace("D", ""));

						int endereco = ep2ocd
								.adicionarLinha(
										Conversoes.array2String(
												Conversoes.changeSizeArray(
														Conversoes.string2IntArray(Conversoes.dec2bin(numero)), 16, 0),
										", "));

						palavra = preencherArray(palavra, Conversoes
								.changeSizeArray(Conversoes.string2IntArray(Conversoes.dec2bin(endereco)), 6, 0), 10);
					} else {
						// trata endereco de memoria
						palavra = preencherArray(palavra,
								Conversoes.changeSizeArray(
										Conversoes.string2IntArray(Conversoes
												.dec2bin(Integer.parseInt(p2String.replace("[", "").replace("]", "")))),
								6, 0), 10);
					}
			}

		}
		arrayPalavras.add(Conversoes.array2String(palavra, ", "));


	}

	private int[] preencherArray(int[] array1, int[] array2, int c) {

		for (int i = 0; i < array2.length; i++) {
			array1[i + c] = array2[i];
		}
		return array1;
	}

	private int[] descobrirOpcode(String opcodeString) {
		switch (opcodeString.toUpperCase()) {

		case "ADD":
			return OpcodeENUM.ADD.opcodeArray;

		case "DEC":
			return OpcodeENUM.DEC.opcodeArray;

		case "DIV":
			return OpcodeENUM.DIV.opcodeArray;

		case "INC":
			return OpcodeENUM.INC.opcodeArray;

		case "JE":
			return OpcodeENUM.JE.opcodeArray;

		case "JG":
			return OpcodeENUM.JG.opcodeArray;

		case "JGE":
			return OpcodeENUM.JGE.opcodeArray;

		case "JL":
			return OpcodeENUM.JL.opcodeArray;

		case "JLE":
			return OpcodeENUM.JLE.opcodeArray;

		case "JNE":
			return OpcodeENUM.JNE.opcodeArray;

		case "MOV":
			return OpcodeENUM.MOV.opcodeArray;

		case "MUL":
			return OpcodeENUM.MUL.opcodeArray;

		case "SUB":
			return OpcodeENUM.SUB.opcodeArray;

		default:
			return Conversoes.string2IntArray("0000");
		}
	}

	private int[] descobrirRegistrador(String pString) {
		switch (pString.toUpperCase()) {
		case "AX":
			return Conversoes.string2IntArray(Conversoes.dec2bin(AX));

		case "BX":
			return Conversoes.string2IntArray(Conversoes.dec2bin(BX));

		case "CX":
			return Conversoes.string2IntArray(Conversoes.dec2bin(CX));

		case "DX":
			return Conversoes.string2IntArray(Conversoes.dec2bin(DX));

		}
		return null;
	}

	public void enviarCodigoParaMemoria(int pc) {

		for (String palavra : arrayPalavras) {
			ep2ocd.adicionarLinha(palavra);
		}

		arrayPalavras.clear();
		
		this.comecoCodigo = pc;
		atualizaPc(pc);
		
		for (Entry<String, Integer> entry : labels.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();

			System.out.println("label: " + key + ", pula para " + (value + comecoCodigo));

		}
	}

}