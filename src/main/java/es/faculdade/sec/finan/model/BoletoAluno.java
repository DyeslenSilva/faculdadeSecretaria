package es.faculdade.sec.finan.model;

import org.springframework.beans.factory.annotation.Autowired;

import es.faculdade.financeiro.igts.cobranca.GerandoBoleto;
import es.faculdade.financeiro.igts.cobranca.GerandoBoletoPagSeguro;
import lombok.Data;

@Data
public class BoletoAluno {
	
	@Autowired
	private GerandoBoleto gerandoBoleto;
	
	@Autowired
	private GerandoBoletoPagSeguro boletoPagSeguro;
}
