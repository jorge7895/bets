package es.txeko.bets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.txeko.bets.models.Bettor;

public interface BettorRepository extends JpaRepository<Bettor, Long>{
	
	Optional<Bettor> findByName(String name);

}
