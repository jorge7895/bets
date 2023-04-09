package es.txeko.bets.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.txeko.bets.models.Bettor;
import es.txeko.bets.services.BettorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/bettor")
public class BettorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BettorController.class);

	@Autowired
	private BettorService bettorService;
	
	@Operation(summary = "Get a bettor by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the bettor", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Bettor.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Bettor not found", 
	    content = @Content) })
	@GetMapping("/{id}")
	public Bettor findBettorById(Long id) {
		
		LOGGER.info("Find bettor by id {}", id);
		
		return bettorService.getBettor(id);
	}
}
