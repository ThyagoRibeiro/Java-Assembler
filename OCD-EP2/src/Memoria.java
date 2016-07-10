import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Memoria {

	private int endereco;
	private HashMap<Integer, String> memoriaAtual = new HashMap<>();

	public Memoria() {
		iniciarMemoria();
	}
	
	public int adicionarLinha(String palavra) {
		int temp = endereco;
		memoriaAtual.put(endereco++, palavra);
		return temp;
	}

	public void atualizarLinha(int endereco, String palavra) {
		memoriaAtual.put(endereco, palavra);
	}

	public int getSize(){
		return memoriaAtual.size();
	}
	
	public String getPalavra(int endereco) {
		return memoriaAtual.get(endereco);
	}
	
	public Set<Entry<Integer, String>> getEntrySet(){
		return memoriaAtual.entrySet();
	}
	
	public void limparMemoria(){
		memoriaAtual.clear();
		iniciarMemoria();
	}
	
	private void iniciarMemoria(){
		endereco = 0;
		
		// registradores
		for (int i = 0; i < 4; i++) {
			adicionarLinha("{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}");
		}
	}
	
}