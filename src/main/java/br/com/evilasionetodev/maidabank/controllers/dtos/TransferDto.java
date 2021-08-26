package br.com.evilasionetodev.maidabank.controllers.dtos;

import java.math.BigDecimal;

import br.com.evilasionetodev.maidabank.controllers.forms.TransferForm;
import br.com.evilasionetodev.maidabank.models.User;

public class TransferDto {

	private BigDecimal amount;
	private String source_account_number;
	private String destination_account_number;
	private UserDto user_transfer;
	
	public TransferDto(TransferForm form, User user) {
		this.amount = form.getAmount();
		this.source_account_number = form.getSource_account_number();
		this.destination_account_number = form.getDestination_account_number();
		this.user_transfer = new UserDto(user);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

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

	public UserDto getUser_tranfer() {
		return user_transfer;
	}

	public void setUser_tranfer(UserDto user) {
		this.user_transfer = user;
	}
}
