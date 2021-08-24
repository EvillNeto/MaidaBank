package br.com.evilasionetodev.maidabank.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.evilasionetodev.maidabank.controllers.dtos.AccountDto;
import br.com.evilasionetodev.maidabank.controllers.dtos.BalanceDto;
import br.com.evilasionetodev.maidabank.controllers.dtos.TransferDto;
import br.com.evilasionetodev.maidabank.controllers.forms.AccountForm;
import br.com.evilasionetodev.maidabank.controllers.forms.BalanceForm;
import br.com.evilasionetodev.maidabank.controllers.forms.TransferForm;
import br.com.evilasionetodev.maidabank.models.Account;
import br.com.evilasionetodev.maidabank.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@PostMapping(value="/{id}")
	@Transactional
	public ResponseEntity<AccountDto> registerAccount(@RequestBody @Valid AccountForm form, @PathVariable Long id, UriComponentsBuilder uriBuilder){
		service.saveAccount(id, form);
		URI uri = uriBuilder.path("/accounts").build().toUri();
		return ResponseEntity.created(uri).body(new AccountDto(service.getUserAccount(id, form.getNumber())));
	}
	
	@PostMapping(value="/{id}/transfer")
	@Transactional
	public ResponseEntity<TransferDto> trnsfer(@RequestBody @Valid TransferForm form, @PathVariable Long id, UriComponentsBuilder uriBuilder){
		service.transfer(id, form);
		URI uri = uriBuilder.path("/accounts").build().toUri();
		return ResponseEntity.created(uri).body(new TransferDto(form, service.getUserAccount(id, form.getSource_account_number()).getUser()));
	}
	
	@PostMapping(value="/{id}/balance")
	@Transactional
	public ResponseEntity<BalanceDto> trnsfer(@RequestBody @Valid BalanceForm form, @PathVariable Long id, UriComponentsBuilder uriBuilder){
		Account ac = service.getUserAccount(id, form.getAccount_number());
		URI uri = uriBuilder.path("/accounts").build().toUri();
		return ResponseEntity.created(uri).body(new BalanceDto(ac));
	}
}
