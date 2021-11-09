package reserva.emeron.projetoemeron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reserva.emeron.projetoemeron.model.Curso;
import reserva.emeron.projetoemeron.model.Locais;
import reserva.emeron.projetoemeron.model.Professor;
import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.model.ReservaStatus;
import reserva.emeron.projetoemeron.model.Usuario;

@Repository
@Transactional
public interface  ReservaRepository extends JpaRepository<Reserva, Long>{
	

	

	@Transactional(readOnly = true)
	@Query(value = "select count (1) > 0 as existe from reserva where nome = ?", nativeQuery = true)
	public boolean reservaExistente(String nome);

	
	

	/*
	 * @Modifying
	 * 
	 * @Query(value = "update reserva set nome=?1,   where id=?2") 
	 * public void
	 * updateReserva(String nome, Long id);
	 */

	
	@Modifying
	@Query(value = "update reserva  r set r.nome =:nome, r.curso =:curso, r.locais =:codigo_locais, r.professor =:professor  where r.id=:id")
	public void updateTesteAgora(@Param("nome") String nome, @Param("curso") Curso curso,@Param("codigo_locais") Locais locais,@Param("professor") Professor professor ,@Param("id") Long id);
	
	
	@Modifying
	@Query(value = "update reserva  r set r.nome =:nome, r.curso =:curso, r.locais =:codigo_locais, r.reservaStatus =:reserva_status where r.id=:id")
	public void adminUpdate(@Param("nome") String nome, @Param("curso") Curso curso,@Param("codigo_locais") Locais locais,@Param("reserva_status") ReservaStatus reservaStatus ,@Param("id") Long id);
	 
	  @Query(value = "select r from reserva r where r.usuario =:codigo_usuario")
	  public List<Reserva> findByReservaUser(@Param("codigo_usuario") Usuario id);
	  
	 
	  
	  @Query(value = "select * from reserva where reserva_status = 'ANALISE'", nativeQuery = true)
	  public List<Reserva> listaDeReservaEmAnalise();
	  
	  
	  
	  	@Modifying
		@Query(value = "update reserva set reserva_status='RESERVADO' where id=?1", nativeQuery = true)
		public void comfirmarReserva(Reserva id);
	  	
	  	
		@Modifying
		@Query(value = "update reserva set reserva_status='CANCELADO' where id=?1", nativeQuery = true)
		public void cancelarReserva(Reserva id);
	  	
	  	
		@Query(value = "SELECT COUNT (*) FROM reserva WHERE reserva_status = 'ANALISE'", nativeQuery = true)
	  	public Integer countAnalise();
	  	
		@Query(value = "SELECT COUNT (*) FROM reserva WHERE reserva_status = 'CANCELADO'", nativeQuery = true)
	  	public Integer countCancelado();
		
		
		@Query(value = "SELECT COUNT (*) FROM reserva WHERE reserva_status = 'RESERVADO'", nativeQuery = true)
	  	public Integer countConfirmada();
		
	
//@Query(value = "select count(1) > 0 as existe from curso where nome = ?", nativeQuery = true)
//public boolean cursoJaExiste(String nome);
}
