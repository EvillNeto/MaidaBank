package br.com.evilasionetodev.maidabank.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import br.com.evilasionetodev.maidabank.security.TokenService;
import br.com.evilasionetodev.maidabank.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<AccountDto> registerAccount(@RequestBody @Valid AccountForm form, UriComponentsBuilder uriBuilder, HttpServletRequest request){
		String token = tokenService.retrieveToken(request);
		Account ac = service.saveAccount(tokenService.getIdUser(token), form);
		URI uri = uriBuilder.path("/accounts").build().toUri();
		return ResponseEntity.created(uri).body(new AccountDto(ac));
	}
	
	@PostMapping(value="/transfer")
	@Transactional
	public ResponseEntity<TransferDto> transfer(@RequestBody @Valid TransferForm form, UriComponentsBuilder uriBuilder, HttpServletRequest request){
		String token = tokenService.retrieveToken(request);
		service.transfer(tokenService.getIdUser(token), form);
		URI uri = uriBuilder.path("/accounts").build().toUri();
		return ResponseEntity.created(uri).body(new TransferDto(form, service.getUserAccount(tokenService.getIdUser(token), form.getSource_account_number()).getUser()));
	}
	
	@PostMapping(value="/balance")
	public ResponseEntity<BalanceDto> balance(@RequestBody @Valid BalanceForm form, UriComponentsBuilder uriBuilder, HttpServletRequest request){
		String token = tokenService.retrieveToken(request);
		Account ac = service.getUserAccount(tokenService.getIdUser(token), form.getAccount_number());
		URI uri = uriBuilder.path("/accounts").build().toUri();
		return ResponseEntity.created(uri).body(new BalanceDto(ac));
	}
}
