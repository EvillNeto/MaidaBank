package br.com.evilasionetodev.maidabank.controllers.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.evilasionetodev.maidabank.models.Acount;
import br.com.evilasionetodev.maidabank.models.User;

public class AcountForm {

	@NotEmpty
	private String number;
	
	@NotNull
	private BigDecimal balance;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public Acount converter(User user) {
		Acount acount = new Acount(number, balance, user);
		return acount;
	}
}
