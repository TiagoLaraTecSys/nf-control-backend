package com.rich.nf_control.adapter.out.database.notaFiscal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotaFiscalJpaRepository extends JpaRepository<NotaFiscalEntity, UUID> {

}