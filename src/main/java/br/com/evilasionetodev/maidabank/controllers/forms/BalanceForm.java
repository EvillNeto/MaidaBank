package br.com.evilasionetodev.maidabank.controllers.forms;

import javax.validation.constraints.NotEmpty;

public class BalanceForm {

	@NotEmpty
	private String account_number;

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
}
