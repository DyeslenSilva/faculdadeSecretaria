package es.faculdade.sec.finan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.faculdade.financeiro.igts.cobranca.GerandoBoleto;
import es.faculdade.financeiro.igts.cobranca.GerandoBoletoPagSeguro;
import es.faculdade.financeiro.igts.model.Boleto;

@RestController
@RequestMapping("/boletosSec")
public class BoletosMensais {

	private GerandoBoleto gerandoBoleto;
	private GerandoBoletoPagSeguro boletoPagSeguro;

	@GetMapping("/gerandoBoletoSec")
	public Boleto getGerandoBoleto(){
		return gerandoBoleto.getBoleto();
	}
	
	public void boletoPagSeguro() {
		boletoPagSeguro.notify();
	}

}	
