
public class Registrador {

	private int entrada;
	private int entrada2;
	private String nome;
	private int saida;
	private int saida2;
	private int[] dados;
	
	public Registrador(String nome, int entrada, int saida) {
		this.nome = nome;
		this.entrada = entrada;
		this.saida = saida;
		this.dados = new int [32];
		
	}
	
	public Registrador(String nome, int entrada, int saida, int entrada2, int saida2) {
		this.nome = nome;
		this.entrada = entrada;
		this.entrada2 = entrada2;
		this.saida = saida;
		this.saida2 = saida2;
		this.dados = new int [32];
		
	}

	public int getEntrada() {
		return entrada;
	}

	public String getNome() {
		return nome;
	}

	public int getSaida() {
		return saida;
	}
	
	public int[] getDados(){
		return dados;
	}
	
	public void setDados(int[] dados) {
		this.dados = dados;
	}
	
	
}
