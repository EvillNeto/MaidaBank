package br.com.evilasionetodev.maidabank.controllers.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class TransferForm {

	@NotEmpty
	private String source_account_number;
	
	@NotEmpty
	private String destination_account_number;
	
	@Positive
	private BigDecimal amount;

	public String getSource_account_number() {
		return source_account_number;
	}

	public void setSource_account_number(String source_account_number) {
		this.source_account_number = source_account_number;
	}

	public String getDestination_account_number() {
		return destination_account_number;
	}

	public void setDestination_account_number(String destination_account_number) {
		this.destination_account_number = destination_account_number;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
