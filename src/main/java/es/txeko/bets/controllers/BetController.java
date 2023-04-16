package es.txeko.bets.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.txeko.bets.builders.BetMapper;
import es.txeko.bets.dtos.BetDTO;
import es.txeko.bets.models.Bet;
import es.txeko.bets.models.Bettor;
import es.txeko.bets.services.BetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("api/v1/bet")
public class BetController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(BetController.class);

	@Autowired
	private BetMapper betMapper;
	
	@Autowired
	private BetService betService;
	
	@Operation(summary = "Get a bet by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the bet", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Bettor.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Bet not found", 
	    content = @Content) })
	@GetMapping("/{id}")
	public Bet findBettorById(Long id) {
		
		LOGGER.info("Find bet by id {}", id);
		
		return betService.getBet(id);
	}
	
	@Operation(summary = "Create a bet")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Bet created", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Bettor.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid bet supplied", 
	    content = @Content), 
	  @ApiResponse(responseCode = "500", description = "Bet create fail", 
	    content = @Content) })
	@PostMapping
	public ResponseEntity<HttpStatus> createNewBet(BetDTO betDTO) {
		
		LOGGER.info("Creating new bet {} ", betDTO.getBetType());
		
		Bet bet = betMapper.betDTOToBet(betDTO);
		
		try {
			
			betService.save(bet);
			
		} catch (Exception e) {
			
			LOGGER.error("Error creating bet {} ", bet.getBetType());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
