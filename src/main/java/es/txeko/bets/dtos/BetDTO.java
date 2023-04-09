package es.txeko.bets.dtos;

import java.util.ArrayList;
import java.util.List;

public class BetDTO {
	
	private List<Long> numbers = new ArrayList<>();
	
	private String betType;
	
	private Double cost;
	
	private String bettorName;
	
	private String awarded;

	public List<Long> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Long> numbers) {
		this.numbers = numbers;
	}

	public String getBetType() {
		return betType;
	}

	public void setBetType(String betType) {
		this.betType = betType;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getBettorName() {
		return bettorName;
	}

	public void setBettorName(String bettorName) {
		this.bettorName = bettorName;
	}

	public String getAwarded() {
		return awarded;
	}

	public void setAwarded(String awarded) {
		this.awarded = awarded;
	}

}
