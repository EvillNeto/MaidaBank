package br.com.evilasionetodev.maidabank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evilasionetodev.maidabank.models.Acount;

public interface AcountRepository  extends JpaRepository<Acount, Long> {

}
