package reserva.emeron.projetoemeron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import reserva.emeron.projetoemeron.model.Professor;
import reserva.emeron.projetoemeron.repository.ProfessorRepository;

//import reserva.emeron.projetoemeron.service.exception.NomeprofessorJaCadastradoException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public boolean professorExiste(String nome) {

		return professorRepository.professorJaExiste(nome);

	}

	public void excluir(Long id) {

		professorRepository.deleteById(id);
	}

	public void update(Professor professor) {
		professorRepository.save(professor);

	}

	/*
	 * public List<professor> getprofessorAll(){ return professorRepository.findAll(); }
	 */

	public List<Professor> buscarTodosProfessores(){
		
		return this.professorRepository.findAll();
		
	}
	

	public void salvar(Professor professor) {

		this.professorRepository.save(professor);

	}

	public Professor findById(Long id) {

		return this.professorRepository.findById(id).get();
	}

	public Integer countprofessors() {
		return (int) professorRepository.count();
	}

}
