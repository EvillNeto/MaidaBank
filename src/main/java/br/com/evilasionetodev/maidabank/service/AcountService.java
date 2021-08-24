package br.com.evilasionetodev.maidabank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.evilasionetodev.maidabank.controllers.forms.AcountForm;
import br.com.evilasionetodev.maidabank.controllers.forms.TransferForm;
import br.com.evilasionetodev.maidabank.models.Acount;
import br.com.evilasionetodev.maidabank.models.User;
import br.com.evilasionetodev.maidabank.repositories.AcountRepository;
import br.com.evilasionetodev.maidabank.repositories.UserRepository;
import br.com.evilasionetodev.maidabank.service.exceptions.AlreadyExistsException;
import br.com.evilasionetodev.maidabank.service.exceptions.ForbiddenException;
import br.com.evilasionetodev.maidabank.service.exceptions.NotFoundException;

public class AcountService {

	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private AcountRepository aRepository;
	
	public void saveAcount(Long userId, AcountForm form) {
		checkAcount(form.getNumber());
		User user = uRepository.findById(userId).get();
		aRepository.save(form.converter(user));
	}
	
	public void transfer(Long userId, TransferForm form) {
		Acount source =  getUserAcount(userId, form.getSource_account_number());
		Acount destination = getAcount(form.getDestination_account_number());
		if(source.getBalance().doubleValue() < form.getAmount().doubleValue()) {
			throw new ForbiddenException("Saldo insuficiente");
		}
		source.subBalance(form.getAmount());
		destination.addBalance(form.getAmount());
		aRepository.save(source);
		aRepository.save(destination);
	}
	
	public Acount getAcount(String number) {
		Optional<Acount> op = aRepository.findByNumber(number);
		if(op.isEmpty()) {
			throw new NotFoundException("Conta numero: "+ number +" não encontrada");
		}
		return op.get();
	}
	
	public Acount getUserAcount(Long userId, String number) {
		Optional<Acount> op = aRepository.findByNumberAndUserId(number,userId);
		if(op.isEmpty()) {
			throw new NotFoundException("Conta numero: "+ number +" não encontrada");
		}
		return op.get();
	}
	
	public void checkAcount(String number) {
		Optional<Acount> op = aRepository.findByNumber(number);
		if(op.isPresent()) {
			throw new AlreadyExistsException("ja existe uma conta com esse número");
		}
	}
}
