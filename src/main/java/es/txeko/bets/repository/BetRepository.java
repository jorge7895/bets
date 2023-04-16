package es.txeko.bets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.txeko.bets.models.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {

}
