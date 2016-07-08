import java.util.HashMap;

public class Memoria {

	private int endereco;
	private HashMap<Integer, int[]> memoriaAtual = new HashMap<>();

	public void adicionarLinha(int[] palavra) {
		memoriaAtual.put(endereco, palavra);
	}

	public void atualizarLinha(int endereco, int[] palavra) {
		memoriaAtual.put(endereco, palavra);
	}

	public int[] getPalavra(int endereco) {
		return memoriaAtual.get(endereco);
	}

}