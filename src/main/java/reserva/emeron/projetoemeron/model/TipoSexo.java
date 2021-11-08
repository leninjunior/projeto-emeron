package reserva.emeron.projetoemeron.model;

public enum TipoSexo {
	
	MASCULINO("Masculino"),
	FEMININO("Feminino");
	
	
	
	private String nome;
	
	private TipoSexo (String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	


}
