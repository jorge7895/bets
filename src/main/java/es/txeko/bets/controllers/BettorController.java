package es.txeko.bets.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.txeko.bets.builders.BettorMapper;
import es.txeko.bets.dtos.BettorDTO;
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

	@Autowired
	private BettorMapper bettorMapper;

	@Operation(summary = "Get a bettor by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the bettor", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Bettor.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Bettor not found", content = @Content(mediaType = "application/json")) })
	@GetMapping("/{id}")
	public BettorDTO findBettorById(Long id) {

		LOGGER.info("Find bettor by id {}", id);

		return bettorMapper.betToBetDTO(bettorService.getBettor(id));
	}

	@Operation(summary = "Create a bettor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Bettor created"),
			@ApiResponse(responseCode = "400", description = "Invalid bettor supplied")})
	@PostMapping
	public ResponseEntity<HttpStatus> createNewBettor(BettorDTO bettorDTO) {

		LOGGER.info("Creating new bettor {} ", bettorDTO.getName());

		Bettor bettor = bettorMapper.bettorDTOToBettor(bettorDTO);
		
		bettorService.save(bettor);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
