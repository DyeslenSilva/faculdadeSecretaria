package es.faculdade.sec.matricula.finan.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetail {

	public OrderDetail(String idPagamento2, String detalhePagamento2, String labelStatus2, String status2) {
		// TODO Auto-generated constructor stub
	}
	private String idPagamento;
	private String detalhePagamento;
	private String labelStatus;
	private String status;
	private String remessa;
	private String totalTaxa;
	private String taxaInsc;
}
