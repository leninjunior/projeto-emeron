package reserva.emeron.projetoemeron.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Table(name = "reserva")
@Entity(name = "reserva")
public class Reserva {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	
	
	@NotBlank
	private String nome;

	@ManyToOne
    @JoinColumn(name = "curso_id")
	private Curso curso;
	

	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	
	
	@ManyToOne
	@JoinColumn(name = "codigo_locais")
	private Locais locais;
	
	
	@JoinColumn(name = "reserva_status")
	@Enumerated(EnumType.STRING)
	private ReservaStatus reservaStatus;
	
	/*
	 * @FutureOrPresent private LocalDate data;
	 * 
	 * @FutureOrPresent private LocalTime horaInicial;
	 * 
	 * private LocalTime horaFinal;
	 */
	
	


	
	
	public Locais getLocais() {
		return locais;
	}


	public ReservaStatus getReservaStatus() {
		return reservaStatus;
	}


	public void setReservaStatus(ReservaStatus reservaStatus) {
		this.reservaStatus = reservaStatus;
	}


	public void setLocais(Locais locais) {
		this.locais = locais;
	}

	/*
	 * public LocalDate getData() { return data; }
	 * 
	 * public void setData(LocalDate data) { this.data = data; }
	 * 
	 * public LocalTime getHoraInicial() { return horaInicial; }
	 * 
	 * public void setHoraInicial(LocalTime horaInicial) { this.horaInicial =
	 * horaInicial; }
	 * 
	 * public LocalTime getHoraFinal() { return horaFinal; }
	 * 
	 * public void setHoraFinal(LocalTime horaFinal) { this.horaFinal = horaFinal;
	 }*/	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Reserva() {
	
	}

	public Reserva(String nome, Curso curso) {
	
		this.nome = nome;
		this.curso = curso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", nome=" + nome + ", curso=" + curso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
