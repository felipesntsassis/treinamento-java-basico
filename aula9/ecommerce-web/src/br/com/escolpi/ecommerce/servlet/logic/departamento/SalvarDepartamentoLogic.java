package br.com.escolpi.ecommerce.servlet.logic.departamento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jpa.repository.departamento.DepartamentoRepository;
import br.com.escolpi.ecommerce.modelo.Departamento;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;
import br.com.escolpi.ecommerce.util.NumberUtil;

public class SalvarDepartamentoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Departamento departamento = new Departamento();
		DepartamentoRepository repository = new DepartamentoRepository(ENTITY_MANAGER);
		
		if (req.getParameter("id") != null)
			departamento.setId(Long.valueOf(req.getParameter("id")));

		departamento.setDescricao(req.getParameter("descricao"));

		String feedback = !NumberUtil.isNullOrZero(departamento.getId()) ? "alterado" : "incluído";
		repository.salvar(departamento);

		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
				"Departamento " + feedback + " com sucesso!"));

		return "mvc?logica=ListarDepartamento";
	}

}
