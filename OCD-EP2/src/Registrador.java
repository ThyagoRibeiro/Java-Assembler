
public class Registrador {

	private int[] entrada;
	private String nome;
	private int[] saida;

	public Registrador(String nome, int[] entrada, int[] saida) {
		this.nome = nome;
		this.entrada = entrada;
		this.saida = saida;
	}

	public int[] getEntrada() {
		return entrada;
	}

	public String getNome() {
		return nome;
	}

	public int[] getSaida() {
		return saida;
	}
}
