package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reserva.emeron.projetoemeron.model.Curso;

@Repository
@Transactional
public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	
	
	 
	
	@Query(value = "select count(1) > 0 as existe from curso where nome = ?", nativeQuery = true)
	public boolean cursoJaExiste(String nome);


		@Query(value = "SELECT COUNT(*) FROM curso", nativeQuery = true)
		public Integer quantidadeDeCurso();
	
}
