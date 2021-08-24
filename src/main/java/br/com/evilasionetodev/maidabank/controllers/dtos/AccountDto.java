package br.com.evilasionetodev.maidabank.controllers.dtos;

import java.math.BigDecimal;

import br.com.evilasionetodev.maidabank.models.Account;

public class AccountDto {

	private String number;
	private BigDecimal balance;
	private UserDto user;
	
	public AccountDto(Account account) {
		this.number = account.getNumber();
		this.balance = account.getBalance();
		this.user = new UserDto(account.getUser());
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

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
}
