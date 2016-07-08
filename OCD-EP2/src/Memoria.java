import java.util.HashMap;

public class Memoria {

	private int endereco;
	private HashMap<Integer, boolean[]> memoriaAtual = new HashMap<>();

	public void adicionarLinha(boolean[] palavra) {
		memoriaAtual.put(endereco, palavra);
	}

	public void atualizarLinha(int endereco, boolean[] palavra) {
		memoriaAtual.put(endereco, palavra);
	}

	public boolean[] getPalavra(int endereco) {
		return memoriaAtual.get(endereco);
	}

}