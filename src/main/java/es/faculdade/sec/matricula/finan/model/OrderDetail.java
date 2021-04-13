package es.faculdade.sec.matricula.finan.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetail {

	private String idPagamento;
	private String detalhePagamento;
	private String labelStatus;
	private String status;
}
