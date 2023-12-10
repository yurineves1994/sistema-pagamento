package br.com.yurineves.backend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.yurineves.backend.entities.Transacao;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

  // select * from transacao order by nome_da_loja asc, id desc;
  List<Transacao> findAllByOrderByNomeDaLojaAscIdDesc();
}
