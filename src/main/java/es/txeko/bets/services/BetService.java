package es.txeko.bets.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.txeko.bets.models.Bet;
import es.txeko.bets.models.BettorException;
import es.txeko.bets.repository.BetRepository;

@Service
public class BetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BetService.class);

	@Autowired
	private BetRepository betRepository;
	
	public Bet getBet(Long id) {

		Optional<Bet> bet = betRepository.findById(id);

		if (!bet.isPresent()) {

			LOGGER.error("Bet with id {} not found in database!!", id);
			return null;

		}

		return bet.get();
	}
	
	public void save(Bet bet) {

		betRepository.save(bet);
	}

	public void update(Bet bet) {

		if (bet == null || bet.getId() == null) {

			LOGGER.error("Cannot update bet without id");
			throw new BettorException("Cannot update bet without id");
			
		}
		
		Bet dbBet = getBet(bet.getId());
		
		
		
		betRepository.save(dbBet);
	}
}
