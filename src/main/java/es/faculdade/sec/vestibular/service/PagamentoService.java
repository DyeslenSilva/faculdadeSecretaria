package es.faculdade.sec.vestibular.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.net.AprEndpoint;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import es.faculdade.sec.finan.model.PagamentoInscricaoSec;
import es.faculdade.sec.finan.model.currency.Currency;
import es.faculdade.sec.matricula.finan.model.OrderDetail;
import es.faculdade.sec.model.enumer.Candidato;
import es.faculdade.sec.model.enumer.ModoCandidato;
import es.faculdade.sec.urls.UrlSecretaria;
import es.faculdade.sec.vestibular.servlet.ExecutePayment;
import es.faculdade.vestibular.igts.payments.model.Pagamentos;

public class PagamentoService {

	//private Candidato candidato;
	
	
	private static final Candidato candidatoID = Candidato.idCandidato;
	private static final Candidato candidatoSecret = Candidato.candidatoSecret;
	private static final ModoCandidato mode = ModoCandidato.sandbox ;
	
	private Pagamentos pagamentos;
	private PagamentoInscricaoSec pagamentoInscricao;
	private es.faculdade.vestibular.igts.model.Candidato candidato;
	
	private Currency currency;
	
	private UrlSecretaria urlSecretaria;
	
	private String intent;
	public String autorizacaoPagamento(OrderDetail orderDetail)
		throws PayPalRESTException{
		
		Payer payer = getPayerInformation();
		RedirectUrls redirectUrls = getRedirectUrls(urlSecretaria);
		List<Transaction> listTransaction = getTransactionInformation(orderDetail);
		
		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransaction);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setPayer(payer);
		requestPayment.setIntent(intent);
		
		APIContext apiContext = new APIContext();
		
		Payment approvedPayment = requestPayment.create(apiContext);
		return getApprovalLink(approvedPayment, urlSecretaria);
	}
	
	
	public Payer getPayerInformation() {
		Payer payer = new Payer();
		payer.setPaymentMethod(pagamentos.getIdPagamentos());
		
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName(candidato.getNomeDoCandidato())
			.setLastName(candidato.getUltimoNome())
			.setEmail(candidato.getEmail());
		
		
		payer.setPayerInfo(payerInfo);
		return payer;
	}
	
	public RedirectUrls getRedirectUrls(UrlSecretaria urlSecretaria) {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setReturnUrl(urlSecretaria.getReturnUrl());
		redirectUrls.setCancelUrl(urlSecretaria.getCancelUrl());
		return redirectUrls;
	}
	
	public List<Transaction> getTransactionInformation(OrderDetail detail){
		Details details = new Details();
		details.setShipping(detail.getRemessa());
		details.setSubtotal(detail.getTaxaInsc());
		details.setTax(detail.getTotalTaxa());
		
		Amount amount = new Amount();
		amount.setCurrency(currency.getReal());
		amount.setTotal(detail.getTotalTaxa());
		amount.setDetails(details);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription(detail.getTaxaInsc());
		
		ItemList list = new ItemList();
		List<Item> items = new ArrayList<>();
		
		Item item = new Item();
		item.setCurrency(currency.getReal());
		item.setName(detail.getRemessa());
		item.setPrice(detail.getTaxaInsc());
		item.setTax(detail.getTotalTaxa());
		
		items.add(item);
		list.setItems(items);
		transaction.setItemList(list);
		
		List<Transaction> listTransactions = new ArrayList<>();
		listTransactions.add(transaction);
		
		return listTransactions;
	}
	
	public String getApprovalLink(Payment approvedPayment,
			UrlSecretaria urlSec) {
		List<Links> links = approvedPayment.getLinks();
		String approvaLink = null;
		
		for(Links link : links) {
			if(link.getRel().equalsIgnoreCase(urlSec.getApprove())) {
				approvaLink = link.getHref();
				break;
			}
		}
		
		return approvaLink;	
	}
	
	@SuppressWarnings("deprecation")
	public Payment getPaymentDetails(String idPagamento) throws PayPalRESTException {
		APIContext apiContext = new APIContext();
		return Payment.get(apiContext, idPagamento);
	}
	
	@SuppressWarnings("deprecation")
	public Payment executePaymet(String idPagamento, String pagadorID) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(idPagamento);
		
		Payment payment = new Payment().setId(pagadorID);
		
		APIContext context = new APIContext();
		
		return payment.execute(context, paymentExecution);
		
		
	}

	
}
