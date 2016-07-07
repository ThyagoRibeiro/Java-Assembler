public class MicroOperacoes {
	
	private static String op;
	private static Registradores p1;
	private static Registradores p2;
	private static boolean indirecao;
	private static String texto;
	
	public MicroOperacoes(String op, Registradores p1, Registradores p2, boolean ind) 
	{	
		this.op = op;
		this.p1 = p1;
		this.p2 = p2;
		this.indirecao = ind;
	}		
	
	public static void Execucao()
	{
		switch (op) {
		
		case "ADD":		
			break;
			
		case "MOV":
			if(p1.getNome().contains("["))
			{
				
			}
			else if(p2.getNome().contains("["))
			{
				
			}
			else
			{
				texto =   "t1: " + p1.getNome() + " <-- " + p2.getNome() + " ( " + p1.getEntrada() + " , " + p2.getEntrada() + ")" + "\n";						
			}			
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
	
	public static void Busca()
	{
		texto =   "t1: MAR <-- PC (3 , 2) \n"
				+ "t2: MBR <-- memória (20, 23, 24, 22) \n"
				+ "t3: ULA <-- PC (2 , 18) \n"
				+ "AC <-- ULA (Incremento) \n"
				+ "PC <-- AC (1 , 19) \n"
				+ "t4: IR <-- MBR (12 , 5)";	
	}
	
	public static void Indirecao()
	{			
		if(p1.getNome().contains("["))
			texto =   "t1: MAR <-- IR(p1) (3 , 15) \n"
					+ "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p1) <-- MBR (16, 5)";
		
		if(p2.getNome().contains("["))
			texto =   "t1: MAR <-- IR(p2) (3 , 13) \n"
					+ "t2: MBR <-- memória (20, 23, 24, 22) \n"
					+ "t3: IR(p2) <-- MBR (14, 5)";
	}
}

















