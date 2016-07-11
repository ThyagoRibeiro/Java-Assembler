public class MicroOperacoes {

	private int indirecao; // 1 - Primeiro Parâmetro; 2- Segundo parâmetro;
							// !1||2 Não tem

	public String busca() {
		return "Ciclo de Busca\n\nt1: MAR <-- PC (3 , 2) \n" + "t2: MBR <-- memória (20, 23, 24, 22) \n"
				+ "t3: ULA <-- PC (2 , 18) \n" + "    AC <-- ULA (Incremento) \n" + "t3: PC <-- AC (1 , 19) \n"
				+ "t4: IR <-- MBR (12 , 5)";
	}

	public String execucao(Registrador p1, Registrador p2, int op, int indirecao) {

		String texto = "Ciclo de Execução\n\n";

		switch (op) {

		case 1:
			if (indirecao == 1) {
				indirecao(indirecao);
				texto = texto + "t4: MBR <-- " + p2.getNome() + " (4 , " + p2.getEntrada() + ") " + "\n";
			} else if (indirecao == 2) {
				indirecao(indirecao);
				texto = texto + "t4: " + p1.getNome() + " <-- MBR (" + p1.getEntrada() + " , 5) " + "\n";
			} else {
				texto = texto +  "t1: " + p1.getNome() + " <-- " + p2.getNome() + " (" + p1.getEntrada() + " , "
						+ p2.getEntrada() + ") " + "\n";
			}
			break;

		case 2:
			if (indirecao == 1) {
				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p2.getNome() + "(17 , " + p2.getSaida()
						+ ")\n" + "    AC <-- ULA (adição)\n" + "t6: AX <-- AC (6, 19)\n ";
			} else if (indirecao == 2) {
				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p1.getNome() + "(17 , " + p1.getSaida()
						+ ")\n" + "    AC <-- ULA (adição)\n" + "t6: AX <-- AC (6, 19)\n ";
			} else {
				texto = texto + "t1: ULA <-- " + p1.getNome() + "(18 , " + p1.getSaida() + ")\n" + "t2: X <-- "
						+ p2.getNome() + "(17 , " + p2.getSaida() + ")\n" + "    AC <-- ULA (adição)\n"
						+ "t3: AX <-- AC (6, 19)\n ";
			}
			break;

		case 3:
			if (indirecao == 1) {

				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p2.getNome() + "(17 , " + p2.getSaida()
						+ ")\n" + "    AC <-- ULA (Subtração)\n" + "t6: AX <-- AC (6, 19)\n ";
			} else if (indirecao == 2) {

				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p1.getNome() + "(17 , " + p1.getSaida()
						+ ")\n" + "    AC <-- ULA (Subtração)\n" + "t6: AX <-- AC (6, 19)\n ";
			} else {

				texto = texto + "t1: ULA <-- " + p1.getNome() + "(18 , " + p1.getSaida() + ")\n" + "t2: X <-- "
						+ p2.getNome() + "(17 , " + p2.getSaida() + ")\n" + "    AC <-- ULA (Subtração)\n"
						+ "t3: AX <-- AC (6, 19)\n ";
			}
			break;

		case 4:
			if (indirecao == 1) {

				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p2.getNome() + "(17 , " + p2.getSaida()
						+ ")\n" + "    AC <-- ULA (Multiplicação)\n" + "t6: AX <-- AC (6, 19)\n ";
			} else if (indirecao == 2) {

				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p1.getNome() + "(17 , " + p1.getSaida()
						+ ")\n" + "    AC <-- ULA (Multiplicação)\n" + "t6: AX <-- AC (6, 19)\n ";
			} else {

				texto = texto + "t1: ULA <-- " + p1.getNome() + "(18 , " + p1.getSaida() + ")\n" + "t2: X <-- "
						+ p2.getNome() + "(17 , " + p2.getSaida() + ")\n" + "    AC <-- ULA (Multiplicação)\n"
						+ "t3: AX <-- AC (6, 19)\n ";
			}
			break;

		case 12:
			if (indirecao == 1) {

				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "    AC <-- ULA (Incremento)\n" + "t5: " + p1.getNome()
						+ " <-- AC (" + p1.getEntrada() + ", 19)\n ";
			} else {

				texto = texto +  "t1: ULA <-- " + p1.getNome() + "(18 , " + p1.getSaida() + ")\n"
						+ "    AC <-- ULA (Incremento)\n" + "t2: " + p1.getNome() + " <-- AC (" + p1.getEntrada()
						+ ", 19)\n ";
			}
			break;

		case 13:
			if (indirecao == 1) {

				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "    AC <-- ULA (Decremento)\n" + "t5: " + p1.getNome()
						+ " <-- AC (" + p1.getEntrada() + ", 19)\n ";
			} else {

				texto = "t1: ULA <-- " + p1.getNome() + "(18 , " + p1.getSaida() + ")\n"
						+ "    AC <-- ULA (Decremento)\n" + "t2: " + p1.getNome() + " <-- AC (" + p1.getEntrada()
						+ ", 19)\n ";
			}
			break;

		case 5:
			if (indirecao == 1) {

				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p2.getNome() + "(17 , " + p2.getSaida()
						+ ")\n" + "    AC <-- ULA (Divisão)\n" + "t6: AX <-- AC (6, 19)\n " + "    AC <-- ULA (resto)\n"
						+ "t7: DX <-- AC (25 , 19)\n";

			} else if (indirecao == 2) {

				indirecao(indirecao);
				texto = texto + "t4: ULA <-- MBR(18 , 5)\n" + "t5: X <-- " + p1.getNome() + "(17 , " + p1.getSaida()
						+ ")\n" + "    AC <-- ULA (Divisão)\n" + "t6: AX <-- AC (6, 19)\n " + "    AC <-- ULA (resto)\n"
						+ "t7: DX <-- AC (25 , 19)\n";
			} else {

				texto = texto +  "t1: ULA <-- " + p1.getNome() + "(18 , " + p1.getSaida() + ")\n" + "t2: X <-- "
						+ p2.getNome() + "(17 , " + p2.getSaida() + ")\n" + "    AC <-- ULA (Divisão)\n"
						+ "t3: AX <-- AC (6, 19)\n " + "    AC <-- ULA (resto)\n" + "t4: DX <-- AC (25 , 19)\n";

			}
			break;

		default:
			break;
		}

		return texto;
	}

	public String indirecao(int indirecao) {
		String texto = "Ciclo de Indireção\n\n";
		if (indirecao == 1) {
			texto = "t1: MAR <-- IR(p1) (3 , 15) \n" + "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p1) <-- MBR (16, 5)";
		}

		if (indirecao == 2) {
			texto = "t1: MAR <-- IR(p2) (3 , 13) \n" + "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p2) <-- MBR (14, 5)";
		}

		return texto;
	}
}
