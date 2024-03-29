import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Memoria {

	private int endereco;
	private HashMap<Integer, String> memoriaAtual = new HashMap<>();

	public Memoria() {
		iniciarMemoria();

		Object[] test = memoriaAtual.keySet().toArray();

	}

	public int adicionarLinha(String palavra) {
		int temp = endereco;
		memoriaAtual.put(endereco++, palavra);
		return temp;
	}

	public void atualizarLinha(int endereco, String palavra) {

		memoriaAtual.put(endereco, palavra);
	}

	public int getSize() {
		return memoriaAtual.size();
	}

	public String getString(int endereco) {
		return memoriaAtual.get(endereco);
	}

	public String getPalavra(int endereco) {

		if (!memoriaAtual.containsKey(endereco)) {
			memoriaAtual.put(endereco, "{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0");
		}

		return memoriaAtual.get(endereco).replace("{", "").replace("}", "").replace(", ", "");
	}

	public Set<Entry<Integer, String>> getEntrySet() {
		return memoriaAtual.entrySet();
	}

	public void limparMemoria() {
		memoriaAtual.clear();
		iniciarMemoria();
	}

	private void iniciarMemoria() {
		endereco = 0;

		// registradores
		for (int i = 0; i < 4; i++) {
			adicionarLinha("{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}");
		}
	}

	private Set<Entry<Integer, String>> getSet() {
		
		return memoriaAtual.entrySet();
	}

}