package br.com.yurineves.backend.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yurineves.backend.entities.TransacaoReport;
import br.com.yurineves.backend.service.TransacaoService;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

  private final TransacaoService service;

  public TransacaoController(TransacaoService service) {
    this.service = service;
  }

  @GetMapping
  public List<TransacaoReport> listAll() {
    return service.listTotaisTransacoesByNomeDaLoja();
  }

}
