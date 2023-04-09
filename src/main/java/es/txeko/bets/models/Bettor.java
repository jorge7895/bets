package es.txeko.bets.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "bettor")
public class Bettor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String name;
	
	@Version
	private Long version;
	
	@OneToMany(mappedBy = "bettor", fetch = FetchType.LAZY)
	private List<Bet> bets = new ArrayList<>();

	public Bettor(Long id, String name, List<Bet> bets) {
		super();
		this.id = id;
		this.name = name;
		this.bets = bets;
	}
	
	public Bettor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bets, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bettor other = (Bettor) obj;
		return Objects.equals(bets, other.bets) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Bettor [id=" + id + ", name=" + name + ", bets=" + bets + "]";
	} 

}
