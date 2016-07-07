import java.util.HashMap;

public class Memoria {

	private HashMap<Integer, boolean[]> memoriaAtual = new HashMap<>();
	private int endereco;
	
	public void adicionarCelula(boolean[] palavra){
		memoriaAtual.put(endereco, palavra);
	}
	
	public void atualizarCelula(int endereco, boolean[] palavra){
		memoriaAtual.put(endereco, palavra);
	}
	
	public boolean[] getPalavra(int endereco){
		return memoriaAtual.get(endereco);
	}
	
}
