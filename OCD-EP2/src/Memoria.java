import java.util.ArrayList;
import java.util.HashMap;

public class Memoria {

	private int endereco;
	private HashMap<Integer, boolean[]> memoriaAtual = new HashMap<>();
	
	
	
	
	private static final int AX = 0;
	private static final int BX = 1;
	private static final int CX = 2;
	private static final int DX = 3;
	private static final int PC = 4;
	private static final int MBR = 5;
	private static final int MAR = 6;
	private static final int P1 = 7;
	private static final int P2 = 8;
	private static final int OP = 9;

	private int[][] teste = new int[10][2];

	public void adicionarCelula(boolean[] palavra) {
		memoriaAtual.put(endereco, palavra);

		teste[AX] = new int[]{1, 2};
		
	}

	public void atualizarCelula(int endereco, boolean[] palavra) {
		memoriaAtual.put(endereco, palavra);
	}

	public boolean[] getPalavra(int endereco) {
		return memoriaAtual.get(endereco);
	}

}
