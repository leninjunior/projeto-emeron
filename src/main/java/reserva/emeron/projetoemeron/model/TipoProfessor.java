package reserva.emeron.projetoemeron.model;

public enum TipoProfessor {
	
	MAGISTRADO("Magistrado"),
	MAGISTRADA("Magistrada"),
	SERVIDOREFETIVO("Servidor Efetivo"),
	SERVIDORCOMISSIONADO("Servidor Comissionado"),
	OUTROS("Outros");
	
	
	
	private String nome;
	
	private TipoProfessor (String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	


}
