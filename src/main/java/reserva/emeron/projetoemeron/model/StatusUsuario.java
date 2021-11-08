package reserva.emeron.projetoemeron.model;

public enum StatusUsuario {
	
	ATIVO("Ativo"),
	INATIVO("Inativo");
	
	
	
	private String nome;
	
	private StatusUsuario (String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	


}
