package br.com.evilasionetodev.maidabank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evilasionetodev.maidabank.models.Acount;

public interface AcountRepository  extends JpaRepository<Acount, Long> {

	public Optional<Acount> findByNumber(String number);
	public Optional<Acount> findByNumberAndUserId(String number,Long id);
}
