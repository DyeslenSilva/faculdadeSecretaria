package es.faculdade.sec.vestibular.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import es.faculdade.sec.matricula.finan.model.AtributoPagamento;
import es.faculdade.sec.urls.UrlSecretaria;
import es.faculdade.sec.vestibular.service.PagamentoService;
import es.faculdade.vestibular.igts.model.Candidato;
import es.faculdade.vestibular.igts.payments.model.Pagamentos;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@WebServlet("/revisaoDePagamento")
public class RevisaoPagamentoServ extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pagamentos pagamentos;
	private Candidato candidato;
	private AtributoPagamento atributoPagamento;
	private UrlSecretaria urlSecretaria;
	
	protected String error;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp,
				Integer index) throws ServletException, IOException{
			Integer idPagamento = Integer.parseInt(req.getParameter(pagamentos.getIdPagamentos()));
			String idCandidato = req.getParameter(Integer.toString(candidato.getIdCandidato()));
			
			try {
				PagamentoService pagamentoService = new PagamentoService();
				Payment payment = pagamentoService.getPaymentDetails(idCandidato);
				
				PayerInfo payerInfo = payment.getPayer().getPayerInfo();
				Transaction transaction = payment.getTransactions().get(index);
				ShippingAddress shippingAddress = transaction.getItemList()
						.getShippingAddress();
				req.setAttribute(atributoPagamento.getPagamento(), payerInfo);
				req.setAttribute(atributoPagamento.getTransacao(), transaction);
				req.setAttribute(atributoPagamento.getShippingAddress(), shippingAddress);
				
				String url = urlSecretaria.getPageRedirect() +" ID Pagamento"+ pagamentos.getIdPagamentos() +
						"ID Candidato"+candidato.getIdCandidato();
				
				req.getRequestDispatcher(url).forward(req, resp);
				
			}catch (PayPalRESTException e) {
				req.setAttribute(error, e.getMessage());
				e.printStackTrace();
				req.getRequestDispatcher(urlSecretaria.getErrorPage())
					.forward(req, resp);
			}
			
			super.doGet(req, resp);
		}
}
