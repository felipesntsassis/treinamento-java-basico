package br.com.escolpi.ecommerce.servlet.logic.vendedor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jpa.repository.departamento.DepartamentoRepository;
import br.com.escolpi.ecommerce.jpa.repository.vendedor.VendedorRepository;
import br.com.escolpi.ecommerce.modelo.Vendedor;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;
import br.com.escolpi.ecommerce.util.NumberUtil;

public class SalvarVendedorLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Vendedor vendedor = new Vendedor();
		VendedorRepository repository = new VendedorRepository(ENTITY_MANAGER);
		DepartamentoRepository departamentoRepository = new DepartamentoRepository(ENTITY_MANAGER);
		

		if (req.getParameter("id") != null)
			vendedor.setId(Long.valueOf(req.getParameter("id")));

		vendedor.setEmail(req.getParameter("email"));
		vendedor.setNome(req.getParameter("nome"));
		vendedor.setEmail(req.getParameter("email"));
		vendedor.setDepartamento(departamentoRepository.obterPorId(
				Long.valueOf(req.getParameter("departamento"))));
		vendedor.setPercentualComissao(NumberUtil.parseDouble(req.getParameter("percComissao")));
		String feedback = !NumberUtil.isNullOrZero(vendedor.getId()) ? "alterado" : "incluído";
		
		repository.salvar(vendedor);

		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Vendedor " + feedback + " com sucesso!"));

		return "mvc?logica=ListarVendedor";
	}

}
