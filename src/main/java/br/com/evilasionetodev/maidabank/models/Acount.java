package br.com.evilasionetodev.maidabank.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Acount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	
	private BigDecimal balance;
	
	@ManyToOne
	private User user;

	public Acount(String number, BigDecimal balance,User user) {
		super();
		this.number = number;
		this.balance = balance;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void addBalance(Double transfer) {
		balance = balance.add(BigDecimal.valueOf(transfer));
	}
	
	public void subBalance(Double transfer) {
		balance = balance.subtract(BigDecimal.valueOf(transfer));
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acount other = (Acount) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}