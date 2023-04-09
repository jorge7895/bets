package es.txeko.bets.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.txeko.bets.commons.BetType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "bet")
public class Bet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private List<Long> numbers = new ArrayList<>();
	
	private BetType betType;
	
	private Double cost;
	
	private boolean awarded;
	
	@Version
	private Long version;
	
	@ManyToOne
	@JoinColumn(name = "bettor_id")
	private Bettor bettor;

	public Bet(Long id, List<Long> numbers, BetType betType, Double cost, Bettor bettor) {
		super();
		this.id = id;
		this.numbers = numbers;
		this.betType = betType;
		this.cost = cost;
		this.bettor = bettor;
	}
	
	public Bet() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Long> numbers) {
		this.numbers = numbers;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Bettor getBettor() {
		return bettor;
	}

	public void setBettor(Bettor bettor) {
		this.bettor = bettor;
	}

	public boolean isAwarded() {
		return awarded;
	}

	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}

	@Override
	public int hashCode() {
		return Objects.hash(betType, bettor, cost, id, numbers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bet other = (Bet) obj;
		return betType == other.betType && Objects.equals(bettor, other.bettor) && Objects.equals(cost, other.cost)
				&& Objects.equals(id, other.id) && Objects.equals(numbers, other.numbers);
	}

	@Override
	public String toString() {
		return "Bet [id=" + id + ", numbers=" + numbers + ", betType=" + betType + ", cost=" + cost + ", bettor="
				+ bettor + "]";
	}

}
