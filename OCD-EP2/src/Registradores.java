
public class Registradores {
	
	private String nome;
	private int[] entrada;
	private int[] saida;
	
	public Registradores(String nome, int[] entrada, int[] saida) {
		this.nome = nome;
		this.entrada = entrada;
		this.saida = saida;
	}

	public String getNome() {
		return nome;
	}
	
	public int[] getEntrada() {
		return entrada;
	}
	
	public int[] getSaida() {
		return saida;
	}
}
