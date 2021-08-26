package br.com.evilasionetodev.maidabank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evilasionetodev.maidabank.controllers.forms.AccountForm;
import br.com.evilasionetodev.maidabank.controllers.forms.TransferForm;
import br.com.evilasionetodev.maidabank.models.Account;
import br.com.evilasionetodev.maidabank.models.User;
import br.com.evilasionetodev.maidabank.repositories.AccountRepository;
import br.com.evilasionetodev.maidabank.repositories.UserRepository;
import br.com.evilasionetodev.maidabank.service.exceptions.BadRequestException;
import br.com.evilasionetodev.maidabank.service.exceptions.ForbiddenException;
import br.com.evilasionetodev.maidabank.service.exceptions.NotFoundException;
import br.com.evilasionetodev.maidabank.service.exceptions.UnauthorizedException;

@Service
public class AccountService {

	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private AccountRepository aRepository;
	
	public Account saveAccount(Long userId, AccountForm form) {
		checkAccount(form.getNumber());
		Account ac = form.converter(getUser(userId));
		aRepository.save(ac);
		return ac;
	}
	
	public void transfer(Long userId, TransferForm form) {
		Account source =  getUserAccount(userId, form.getSource_account_number());
		Account destination = getAccount(form.getDestination_account_number());
		if(source.getBalance().doubleValue() < form.getAmount().doubleValue()) {
			throw new ForbiddenException("Saldo insuficiente na conta de origem");
		}
		source.subBalance(form.getAmount());
		destination.addBalance(form.getAmount());
		aRepository.save(source);
		aRepository.save(destination);
	}
	
	public User getUser(Long userId) {
		Optional<User> op = uRepository.findById(userId);
		if(op.isEmpty()) {
			throw new UnauthorizedException("Acesso Negado");
		}
		return op.get();
	}
	
	public Account getAccount(String number) {
		Optional<Account> op = aRepository.findByNumber(number);
		if(op.isEmpty()) {
			throw new NotFoundException("Conta destino não encontada");
		}
		return op.get();
	}
	
	public Account getUserAccount(Long userId, String number) {
		Optional<Account> op = aRepository.findByNumberAndUserId(number,userId);
		if(op.isEmpty()) {
			throw new NotFoundException("Conta de origem não encontrada para o usuário informado");
		}
		return op.get();
	}
	
	public void checkAccount(String number) {
		Optional<Account> op = aRepository.findByNumber(number);
		if(op.isPresent()) {
			throw new BadRequestException("ja existe uma conta com esse número");
		}
	}
}
