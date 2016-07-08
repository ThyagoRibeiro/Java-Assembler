public class MicroOperacoes {

	private static boolean indirecao;
	private static String op;
	private static Registrador p1;
	private static Registrador p2;
	private static String texto;
	private static int tipo;
	public static void Busca() {
		texto = "t1: MAR <-- PC (3 , 2) \n" + "t2: MBR <-- memória (20, 23, 24, 22) \n" + "t3: ULA <-- PC (2 , 18) \n"
				+ "AC <-- ULA (Incremento) \n" + "PC <-- AC (1 , 19) \n" + "t4: IR <-- MBR (12 , 5)";
	}

	public static void Execucao() {
		switch (op) {

		case "ADD":
			break;

		case "MOV":
			/*if (p1.getIndice().contains("[")) {
					//tipo1
			} else if (p2.getIndice().contains("[")) {
					//tipo2
			} else {
					//tipo3
				texto = "t1: " + p1.getIndice() + " <-- " + p2.getIndice() + " ( " + p1.getEntrada() + " , "
						+ p2.getEntrada() + ")" + "\n";
			}*/
			break;

		case "SUB":
			break;

		case "MUL":
			break;

		case "INC":
			break;

		case "DEC":
			break;

		case "DIV":
			break;

		default:
			break;
		}
	}

	public static void Indirecao() {
		//tipo 1
		/*if (p1.getIndice().contains("["))
			texto = "t1: MAR <-- IR(p1) (3 , 15) \n" + "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p1) <-- MBR (16, 5)";
		//tipo 2
		if (p2.getIndice().contains("["))
			texto = "t1: MAR <-- IR(p2) (3 , 13) \n" + "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p2) <-- MBR (14, 5)";
					*/
	}

	public MicroOperacoes(String op, Registrador p1, Registrador p2, boolean ind) {
		this.op = op;
		this.p1 = p1;
		this.p2 = p2;
		this.indirecao = ind;
	}
}
