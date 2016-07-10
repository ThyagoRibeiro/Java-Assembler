public class MicroOperacoes {
	
	private static String op;
	private static Registrador p1;
	private static Registrador p2;
	private static String texto;
	private static int tipo;
	private static int indirecao; // 1 - Primeiro Parâmetro; 2- Segundo parâmetro; !1||2 Não tem
	
	public MicroOperacoes(String op, Registrador p1, Registrador p2, int ind) {
		this.op = op;
		this.p1 = p1;
		this.p2 = p2;
		this.indirecao = ind;
	}
	
	public static void Busca() {
		texto = "t1: MAR <-- PC (3 , 2) \n" 
				+"t2: MBR <-- memória (20, 23, 24, 22) \n" + "t3: ULA <-- PC (2 , 18) \n"
				+ "    AC <-- ULA (Incremento) \n" 
				+ "t3: PC <-- AC (1 , 19) \n" 
				+ "t4: IR <-- MBR (12 , 5)";
	}

	public static void Execucao() {
		
		switch (op) {
		case "ADD":
			if (indirecao == 1) {
				//tipo1
				tipo = 1;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (adição)\n"
						  + "t6: AX <-- AC (6, 19)\n ";
			} else if (indirecao == 2) {
				//tipo2
				tipo = 2;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p1.getIndice() + "(17 , " + p1.getSaida()+ ")\n"
						  + "    AC <-- ULA (adição)\n"
						  + "t6: AX <-- AC (6, 19)\n ";
			} else {
				//tipo3
				tipo = 3;
				texto = "t1: ULA <-- "+ p1.getIndice() + "(18 , " + p1.getSaida()+ ")\n"
						  + "t2: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (adição)\n"
						  + "t3: AX <-- AC (6, 19)\n ";
			}
			break;

		case "MOV":
			if (indirecao == 1) {
				//tipo1
				tipo = 1;
				Indirecao();
				texto = texto + "t4: MBR <-- " + p2.getIndice() + " (4 , " + p2.getEntrada() + ") " + "\n";
			} else if (indirecao == 2) {
				//tipo2
				tipo = 2;
				Indirecao();
				texto = texto +  "t4: " + p1.getIndice() + " <-- MBR (" + p1.getEntrada() + " , 5) " + "\n";
			} else {
				//tipo3
				tipo = 3;
				texto = "t1: " + p1.getIndice() + " <-- " + p2.getIndice() + " (" + p1.getEntrada() + " , "
						+ p2.getEntrada() + ") " + "\n";
			}
			break;

		case "SUB":
			if (indirecao == 1) {
				//tipo1
				tipo = 1;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (Subtração)\n"
						  + "t6: AX <-- AC (6, 19)\n ";
			} else if (indirecao == 2) {
				//tipo2
				tipo = 2;
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p1.getIndice() + "(17 , " + p1.getSaida()+ ")\n"
						  + "    AC <-- ULA (Subtração)\n"
						  + "t6: AX <-- AC (6, 19)\n ";
			} else {
				//tipo3
				tipo = 3;
				texto =   "t1: ULA <-- "+ p1.getIndice() + "(18 , " + p1.getSaida()+ ")\n"
						  + "t2: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (Subtração)\n"
						  + "t3: AX <-- AC (6, 19)\n ";
			}
			break;

		case "MUL":
			if (indirecao == 1) {
				//tipo1
				tipo = 1;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (Multiplicação)\n"
						  + "t6: AX <-- AC (6, 19)\n ";
			} else if (indirecao == 2) {
				//tipo2
				tipo = 2;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p1.getIndice() + "(17 , " + p1.getSaida()+ ")\n"
						  + "    AC <-- ULA (Multiplicação)\n"
						  + "t6: AX <-- AC (6, 19)\n ";
			} else {
				//tipo3
				tipo = 3;
				texto =   "t1: ULA <-- "+ p1.getIndice() + "(18 , " + p1.getSaida()+ ")\n"
						  + "t2: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (Multiplicação)\n"
						  + "t3: AX <-- AC (6, 19)\n ";
			}
			break;

		case "INC":
			if (indirecao == 1) {
				//tipo1
				tipo = 1;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"						  
						  + "    AC <-- ULA (Incremento)\n"
						  + "t5: " +p1.getIndice() + " <-- AC ("+p1.getEntrada()+", 19)\n ";
			} else {
				//tipo2	
				tipo = 2;
				texto =  	"t1: ULA <-- "+ p1.getIndice() +"(18 , " + p1.getSaida() + ")\n"						  
						  + "    AC <-- ULA (Incremento)\n"
						  + "t2: "+p1.getIndice() + " <-- AC ("+p1.getEntrada()+", 19)\n ";
			}
			break;

		case "DEC":
			if (indirecao == 1) {
				//tipo1
				tipo = 1;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"						  
						  + "    AC <-- ULA (Decremento)\n"
						  + "t5: " +p1.getIndice() + " <-- AC ("+p1.getEntrada()+", 19)\n ";			
			} else {
				//tipo2
				tipo = 2;
				texto =  	"t1: ULA <-- "+ p1.getIndice() +"(18 , " + p1.getSaida() + ")\n"						  
						  + "    AC <-- ULA (Decremento)\n"
						  + "t2: " + p1.getIndice() + " <-- AC ("+p1.getEntrada()+", 19)\n ";
			}
			break;

		case "DIV":
			if (indirecao == 1) {
				//tipo1
				tipo = 1;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (Divisão)\n"
						  + "t6: AX <-- AC (6, 19)\n "
						  + "    AC <-- ULA (resto)\n"
						  + "t7: DX <-- AC (25 , 19)\n";;
			} else if (indirecao == 2) {
				//tipo2
				tipo = 2;
				Indirecao();
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n"
						  + "t5: X <-- " + p1.getIndice() + "(17 , " + p1.getSaida()+ ")\n"
						  + "    AC <-- ULA (Divisão)\n"
						  + "t6: AX <-- AC (6, 19)\n "
						  + "    AC <-- ULA (resto)\n"
						  + "t7: DX <-- AC (25 , 19)\n";
			} else {
				//tipo3
				tipo = 3;
				texto =   "t1: ULA <-- "+ p1.getIndice() + "(18 , " + p1.getSaida()+ ")\n"
						  + "t2: X <-- " + p2.getIndice() + "(17 , " + p2.getSaida()+ ")\n"
						  + "    AC <-- ULA (Divisão)\n"
						  + "t3: AX <-- AC (6, 19)\n "
						  + "    AC <-- ULA (resto)\n"
						  + "t4: DX <-- AC (25 , 19)\n";
				
			}
			break;
		default:
			break;
		}
	}

	public static void Indirecao() {
		//tipo 1
		if (indirecao == 1){
			tipo = 1;
			texto = "t1: MAR <-- IR(p1) (3 , 15) \n" 
					+ "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p1) <-- MBR (16, 5)";
		}
		
		//tipo 2
		if (indirecao == 2){
			tipo = 2;
			texto = "t1: MAR <-- IR(p2) (3 , 13) \n"
					+ "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p2) <-- MBR (14, 5)";
		}
	}
}
