
public class Registrador {

	private int entrada;
	private int entrada2;
	private int indice;
	private int saida;
	private int saida2;
	private int[] dados;
	
	public Registrador(int indice, int entrada, int saida) {
		this.indice = indice;
		this.entrada = entrada;
		this.saida = saida;
		this.dados = new int [32];
		
	}
	
	public Registrador(int indice, int entrada, int saida, int entrada2, int saida2) {
		this.indice = indice;
		this.entrada = entrada;
		this.entrada2 = entrada2;
		this.saida = saida;
		this.saida2 = saida2;
		this.dados = new int [16];
		
	}

	public int getEntrada() {
		return entrada;
	}

	public int getIndice() {
		return indice;
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
