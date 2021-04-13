package es.faculdade.sec.vestibular.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.base.exception.PayPalException;
import com.paypal.base.rest.PayPalRESTException;

import es.faculdade.sec.vestibular.model.InscricaoVestibularMod;
import es.faculdade.sec.vestibular.servlet.VestibularPagServlet;
import es.faculdade.vestibular.igts.model.Inscricao;
import es.faculdade.vestibular.igts.payments.PagamentoInscricao;
import es.faculdade.vestibular.igts.payments.model.Pagamentos;

@RestController
@RequestMapping("/inscricaoVest")
public class InscricaoVestibularController {

	private Inscricao inscricao;
	private PagamentoInscricao pagamentoInscricao;
	private InscricaoVestibularMod inscVestMod;
	private Pagamentos pagamentos;
	private VestibularPagServlet vestibularPagServlet;
	private HttpServletRequest httpServletRequest = (HttpServletRequest) vestibularPagServlet;
	private HttpServletResponse httpServletResponse = (HttpServletResponse) vestibularPagServlet;
	
	@RequestMapping(path =  "/inscricaoVest/{inscricao}" , method = RequestMethod.POST )
	public ResponseEntity<Inscricao> inscricaoVestibular(@PathVariable Inscricao inscricao)
		throws PayPalException , PayPalRESTException, ServletException, IOException{
		inscricao.getIdInscricao();
		inscricao.getCandidato();
		inscricao.getDataDaInscricao();
		inscricao.getValorInscricao();
		
		vestibularPagServlet.service(httpServletRequest, httpServletResponse);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path = "/pagamentoInsc" , method = RequestMethod.POST)
	public ResponseEntity<PagamentoInscricao> pagamentoInscricao() throws PayPalRESTException,
	ServletException, IOException{
		pagamentos.getIdPagamentos();
		pagamentos.getDetalhePagamento();
		pagamentos.getLabelStatus();
		pagamentos.getStatus();
		
		vestibularPagServlet.service(httpServletRequest, httpServletResponse);
		
		return ResponseEntity.ok().build();
	}
}
