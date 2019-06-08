package br.com.escolpi.ecommerce.servlet.logic.departamento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jpa.repository.departamento.DepartamentoRepository;
import br.com.escolpi.ecommerce.modelo.Departamento;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EditarDepartamentoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Departamento departamento = new Departamento();
		DepartamentoRepository repository = new DepartamentoRepository(ENTITY_MANAGER);
		
		if (!StringUtil.isBlank(req.getParameter("id")))
			departamento = repository.obterPorId(Long.valueOf(req.getParameter("id")));
		
		req.setAttribute("departamento", departamento);

		return "admin/departamento/editar.jsp";
	}

}
