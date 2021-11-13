package reserva.emeron.projetoemeron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.model.Usuario;
import reserva.emeron.projetoemeron.repository.ReservaRepository;

@Service
public class ReservaService {
	
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	
	
	
	
	public boolean reservaExiste(String nome) {

		return reservaRepository.reservaExistente(nome);

	}

	/*
	 * public boolean reservaExistente() {
	 * 
	 * return reservaRepository.reservaExistente();
	 * 
	 * }
	 */
	
	
	
	  public boolean localJaReservado(Reserva reserva) {
	  
	  return reservaRepository.isLocalJaReservado(reserva.getLocais(),
	  reserva.getDataReserva(), reserva.getHoraInicial(), reserva.getHoraFinal());
	  
	  }
	  
	  public boolean localJaReservado1(Reserva reserva) {
		  
		  return reservaRepository.isLocalJaReservado1(reserva.getLocais(),
		  reserva.getDataReserva(), reserva.getHoraInicial(), reserva.getHoraFinal());
		  
		  }
	 
	
	public void salvarDados(Reserva reserva){
	     
		reservaRepository.save(reserva);
		  
	  }
	
	public List<Reserva> buscarTodos(){
		return reservaRepository.findAll();
	}
	
	
	public Integer countReserva() {
		 return (int) reservaRepository.count();
		 
		}
	
	public Integer countReservaAnalise() {
		
		return reservaRepository.countAnalise();
	}
	
	
	public Integer countReservaCancelado() {
		
		return reservaRepository.countCancelado();
	}
	
	
	public Integer countReservaConfirmado() {
		
		return reservaRepository.countConfirmada();
	}
	
	
		public void confirmarReserva(Reserva id) {
			
			reservaRepository.comfirmarReserva(id);
		}
		 
	
	

		public void cancelarReserva(Reserva id) {
			
			reservaRepository.cancelarReserva(id);
		}
		 
	
		
		/*
		 * public void updateReserva(Reserva reserva ){
		 * 
		 * reservaRepository.updateReserva(reserva.getNome(), reserva.getId());
		 * 
		 * }
		 */
		  
		  public void usuarioUpdate(Reserva reserva) {
			  reservaRepository.updateTesteAgora(reserva.getNome(), reserva.getCurso(), reserva.getLocais() , reserva.getProfessor(), reserva.getDataReserva(),
					  reserva.getHoraInicial(), reserva.getHoraFinal(), reserva.getId());
		  }
		  
		  
		  public void adminUpdate(Reserva reserva) {
			  reservaRepository.adminUpdate(reserva.getNome(), reserva.getCurso(), reserva.getLocais() , reserva.getReservaStatus(), reserva.getDataReserva(),
					  reserva.getHoraInicial(), reserva.getHoraFinal(), reserva.getId());
		  }
		 

	
	
	public Reserva findById(Long id) {
		
		return this.reservaRepository.findById(id).get();
		
		
	}

	public List<Reserva> buscarReservaPorUsuario(Usuario usuario) {
		return reservaRepository.findByReservaUser(usuario);
	}

	public List<Reserva> reservaEmAnalise() {
		return reservaRepository.listaDeReservaEmAnalise();
	}

	
	/*
	 * public void updateReserva(String nome, Long id_curso, LocalDate data,
	 * LocalTime hora_inicial, LocalTime hora_final, Long codigo_locais ){
	 * 
	 * reservaRepository.updateReserva(nome, id_curso, data, hora_inicial,
	 * hora_final, codigo_locais);
	 * 
	 * }
	 */
}
