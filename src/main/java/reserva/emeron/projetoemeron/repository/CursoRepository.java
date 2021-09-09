package reserva.emeron.projetoemeron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reserva.emeron.projetoemeron.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	
	List<Curso> findByNomeIgnoreCase(String nome);

	
	
}
