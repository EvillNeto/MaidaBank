package br.com.evilasionetodev.maidabank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evilasionetodev.maidabank.models.Account;

public interface AccountRepository  extends JpaRepository<Account, Long> {

	public Optional<Account> findByNumber(String number);
	public Optional<Account> findByNumberAndUserId(String number,Long id);
}
