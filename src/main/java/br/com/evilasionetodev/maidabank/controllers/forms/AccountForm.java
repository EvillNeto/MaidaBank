package br.com.evilasionetodev.maidabank.controllers.forms;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import br.com.evilasionetodev.maidabank.models.Account;
import br.com.evilasionetodev.maidabank.models.User;

public class AccountForm {

	@NotEmpty
	private String number;
	
	@Min(value=0,message="O balanço inicial não pode ser negativo")
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
	
	public Account converter(User user) {
		Account account = new Account(number, balance, user);
		return account;
	}
}
