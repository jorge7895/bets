package es.txeko.bets.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.txeko.bets.models.Bettor;
import es.txeko.bets.models.BettorException;
import es.txeko.bets.repository.BettorRepository;

@Service
public class BettorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BettorService.class);

	@Autowired
	private BettorRepository bettorRepository;

	public Bettor getBettor(Long id) {

		Optional<Bettor> bettor = bettorRepository.findById(id);

		if (!bettor.isPresent()) {

			LOGGER.error("Bettor with id {} not found in database!!", id);
			return null;

		}

		return bettor.get();
	}

	public Bettor findBettorByName(String name) {

		Optional<Bettor> bettor = bettorRepository.findByName(name);

		if (!bettor.isPresent()) {

			LOGGER.error("Bettor with name {} not found in database!!", name);
			return null;

		}

		return bettor.get();
	}

	public void save(Bettor bettor) {

		bettorRepository.save(bettor);
	}

	public void update(Bettor bettor) {

		if (bettor == null || bettor.getId() == null) {

			LOGGER.error("Cannot update bettor without id");
			throw new BettorException("Cannot update bettor without id");
		}

		Bettor dbBettor = getBettor(bettor.getId());

		if (dbBettor == null) {

			throw new BettorException("Bettor with id " + bettor.getId() + " not found in database!!");

		}
		
		dbBettor.setName(bettor.getName());

		bettorRepository.save(dbBettor);
	}
}
