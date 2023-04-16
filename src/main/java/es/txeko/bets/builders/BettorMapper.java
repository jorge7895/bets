package es.txeko.bets.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.txeko.bets.dtos.BettorDTO;
import es.txeko.bets.models.Bettor;
import es.txeko.bets.validations.BettorValidation;

@Component
public class BettorMapper {


	@Autowired
	private BettorValidation bettorValidation;
	
	public BettorDTO betToBetDTO(Bettor bettor) {

		BettorDTO bettorDTO = new BettorDTO();

		bettorDTO.setName(bettor.getName());
		
		return bettorDTO;
	}
	
	

	public Bettor bettorDTOToBettor(BettorDTO bettorDTO) {

		Bettor bettor = new Bettor();

		bettor.setName(bettorDTO.getName());
		
		bettorValidation.validateBettor(bettor);
		
		return bettor;
	}
}
