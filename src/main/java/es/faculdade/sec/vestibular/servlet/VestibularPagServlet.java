package es.faculdade.sec.vestibular.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.faculdade.sec.matricula.finan.model.OrderDetail;
import es.faculdade.vestibular.igts.model.Inscricao;
import es.faculdade.vestibular.igts.payments.model.Pagamentos;
import lombok.NoArgsConstructor;

@WebServlet("/pagVestibular")
@NoArgsConstructor
public class VestibularPagServlet extends HttpServlet{

	
	private Pagamentos pagamentos;
	private Inscricao inscricao;
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String idPagamento = req.getParameter(pagamentos.getIdPagamentos());
			String detalhePagamento = req.getParameter(pagamentos.getDetalhePagamento());
			String labelStatus = req.getParameter(pagamentos.getLabelStatus());
			String status = req.getParameter(pagamentos.getStatus());
			
			OrderDetail detail = new OrderDetail(idPagamento, detalhePagamento, labelStatus, status);
			
			
			
			
			super.doPost(req, resp);
		}
	
}
