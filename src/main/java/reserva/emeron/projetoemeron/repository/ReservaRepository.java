package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reserva.emeron.projetoemeron.model.Reserva;

@Repository
@Transactional
public interface  ReservaRepository extends JpaRepository<Reserva, Long>{
	

	@Query(value = "SELECT COUNT(*) FROM reserva", nativeQuery = true)
	public Integer quantidadeDeReserva();
	

}
