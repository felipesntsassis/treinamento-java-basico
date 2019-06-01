package br.com.escolpi.ecommerce.servlet.logic.vendedor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;

public class ExcluirVendedorLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");

		Long id = Long.valueOf(req.getParameter("id"));

		VendedorDao dao = new VendedorDao();
		
		if (dao.temPedidosAtendidos(id)) {
			req.setAttribute("feedback", new Feedback(TipoFeedback.ALERTA, 
					"Este vendedor tem atendimentos de por um ou mais pedidos e não pode ser excluído!"));
		}
		dao.remover(id);

		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Vendedor excluído com sucesso!"));

		return "mvc?logica=ListarVendedor";
	}

}
