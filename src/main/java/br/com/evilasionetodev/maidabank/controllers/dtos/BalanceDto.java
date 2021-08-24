package br.com.evilasionetodev.maidabank.controllers.dtos;

import java.math.BigDecimal;

import br.com.evilasionetodev.maidabank.models.Account;

public class BalanceDto {

	private String accounT_number;
	private BigDecimal balance;
	
	public BalanceDto(Account ac){
	this.accounT_number = ac.getNumber();
	this.balance = ac.getBalance();
	}

	public String getAccounT_number() {
		return accounT_number;
	}

	public void setAccounT_number(String accounT_number) {
		this.accounT_number = accounT_number;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
