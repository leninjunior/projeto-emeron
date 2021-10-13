package reserva.emeron.projetoemeron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.repository.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	
	
	
	public void salvarDados(Reserva reserva){
	     
		reservaRepository.save(reserva);
		  
	  }
	
	public List<Reserva> buscarTodos(){
		return reservaRepository.findAll();
	}
	
	
	public Integer countReserva() {
		 return reservaRepository.quantidadeDeReserva();
		 
		}

	
	
	public Reserva findById(Long id) {
		
		return this.reservaRepository.findById(id).get();
		
		
	}
}
