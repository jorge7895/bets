package es.txeko.bets.builders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.txeko.bets.commons.BetType;
import es.txeko.bets.dtos.BetDTO;
import es.txeko.bets.models.Bet;
import es.txeko.bets.models.Bettor;
import es.txeko.bets.models.BettorException;
import es.txeko.bets.services.BettorService;

@Component
public class BetMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(BetMapper.class);

	@Autowired
	private BettorService bettorService;

	public BetDTO betToBetDTO(Bet bet) {

		BetDTO betDTO = new BetDTO();

		betDTO.setBettorName(bet.getBettor().getName());
		betDTO.setBetType(bet.getBetType().toString());
		betDTO.setCost(bet.getCost());
		betDTO.setNumbers(bet.getNumbers());

		if (bet.isAwarded()) {
			betDTO.setAwarded("Yes");
		} else {
			betDTO.setAwarded("No");
		}

		return betDTO;
	}

	public Bet betDTOToBet(BetDTO betDTO) {

		Bet bet = new Bet();

		bet.setNumbers(betDTO.getNumbers());
		bet.setAwarded(Boolean.FALSE);
		bet.setCost(betDTO.getCost());
		bet.setBettor(findBettor(betDTO.getBettorName()));

		if (BetType.valueOf(betDTO.getBetType()) != null) {

			bet.setBetType(BetType.valueOf(betDTO.getBetType()));
		}

		return bet;
	}

	private Bettor findBettor(String name) {

		Bettor bettor = bettorService.findBettorByName(name);

		if (bettor == null) {

			LOGGER.error("Bettor {} not found!!", name);
			throw new BettorException("Bettor " + name + " not found!!");
		}

		return bettor;
	}
}
