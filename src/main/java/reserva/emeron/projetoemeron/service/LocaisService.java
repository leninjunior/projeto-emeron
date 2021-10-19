package reserva.emeron.projetoemeron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reserva.emeron.projetoemeron.model.Locais;
import reserva.emeron.projetoemeron.repository.LocaisRepository;

@Service
public class LocaisService {
	
	
	@Autowired
	private LocaisRepository locaisRepository;
	
	
	public List<Locais> buscarTodosLocais(){
		
		return this.locaisRepository.findAll();
		
	}
	
	
	public Locais salvarLocal(Locais local) {
		return this.locaisRepository.save(local);
	}
	
	public boolean locaisExiste(String nome) {

		return locaisRepository.localExiste(nome);

	}
	
	
	public Locais findById(Long id) {

		return this.locaisRepository.findById(id).get();
	}


	public void excluir(Long id) {
		// TODO Auto-generated method stub
		this.locaisRepository.deleteById(id);
		
	}

}
