package reserva.emeron.projetoemeron.model;




public enum ReservaStatus {
	

	ANALISE("ANÁLISE"),
	RESERVADO("RESERVADO"),
	CANCELADO("CANCELADO");
	
	
	
	private String nome;
	
	private ReservaStatus (String nome) {
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	

}


