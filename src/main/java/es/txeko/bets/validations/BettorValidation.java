package es.txeko.bets.validations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.txeko.bets.models.Bettor;
import es.txeko.bets.models.BettorException;
import es.txeko.bets.services.BettorService;

@Service
public class BettorValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(BettorValidation.class);
	
	@Autowired
	private BettorService bettorService;
	
	public void validateBettor(Bettor bettor) {
		
		LOGGER.debug("Checking bettor {}", bettor);
		
		checkExistBettor(bettor);
	}
	
	private void checkExistBettor(Bettor bettor) {
		
		Bettor dbBettor = bettorService.findBettorByName(bettor.getName());
		
		if (dbBettor != null) {
			
			LOGGER.error("Bettor with name {} already exists", bettor.getName());
			throw new BettorException("Bettor with name "+bettor.getName()+" already exists");
			
		}else if(bettor.getId() != null && dbBettor == null) {
			
			LOGGER.error("Bettor with name {} does not exists", bettor.getName());
			throw new BettorException("Bettor with name "+bettor.getName()+" does not exists");
			
		}
	}
	
}
