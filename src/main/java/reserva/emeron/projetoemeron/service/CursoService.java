package reserva.emeron.projetoemeron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import reserva.emeron.projetoemeron.model.Curso;
import reserva.emeron.projetoemeron.repository.CursoRepository;
import reserva.emeron.projetoemeron.service.exception.NomeCursoJaCadastradoException;

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

		public void salvar(Curso curso) {

			List<Curso> cursoList = cursoRepository.findByNomeIgnoreCase(
					curso.getNome());
			System.out.println("nome do curso  é " + curso.getNome());
			System.out.println("cursos repetidos são" + cursoList);

			if (!cursoList.isEmpty()) {
				throw new NomeCursoJaCadastradoException("Curso já Cadastrado!!");

			}

			this.cursoRepository.save(curso);
		}

		public Curso findById(Long id) {
			
			return this.cursoRepository.findById(id).get();
		}

	
		
	
	   
}
