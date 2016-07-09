import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Memoria {

	private int endereco;
	private HashMap<Integer, String> memoriaAtual = new HashMap<>();

	public void adicionarLinha(String palavra) {
		memoriaAtual.put(endereco++, palavra);
	}

	public void atualizarLinha(int endereco, String palavra) {
		memoriaAtual.put(endereco, palavra);
	}

	public String getPalavra(int endereco) {
		return memoriaAtual.get(endereco);
	}

	public int getSize(){
		return memoriaAtual.size();
	}
	
	public Set<Entry<Integer, String>> getEntrySet(){
		return memoriaAtual.entrySet();
	}
	
	public void limparMemoria(){
		memoriaAtual.clear();
	}
	
}