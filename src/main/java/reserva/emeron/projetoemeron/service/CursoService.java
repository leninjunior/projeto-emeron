package reserva.emeron.projetoemeron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import reserva.emeron.projetoemeron.model.Curso;
import reserva.emeron.projetoemeron.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;
	
	/*public List<Curso> getCursoAll(){
		return cursoRepository.findAll();
	}*/
		
	   public List<Curso> buscarTodos(){
	        Iterable<Curso> cursoIterable = this.cursoRepository.findAll();
	       return Streamable.of(cursoIterable).toList();
	   }

	   public void salvar(Curso curso){
	        this.cursoRepository.save(curso);

	    }
}
