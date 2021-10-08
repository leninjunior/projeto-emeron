package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import reserva.emeron.projetoemeron.model.Reserva;

@Repository
public interface  ReservaRepository extends JpaRepository<Reserva, Long>{
	

	@Query(value = "SELECT COUNT(*) FROM reserva", nativeQuery = true)
	public Integer quantidadeDeReserva();
	

}
