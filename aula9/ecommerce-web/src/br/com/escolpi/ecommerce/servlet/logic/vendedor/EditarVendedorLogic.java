package br.com.escolpi.ecommerce.servlet.logic.vendedor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jpa.repository.departamento.DepartamentoRepository;
import br.com.escolpi.ecommerce.jpa.repository.vendedor.VendedorRepository;
import br.com.escolpi.ecommerce.modelo.Departamento;
import br.com.escolpi.ecommerce.modelo.Vendedor;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.OptionList;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EditarVendedorLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Vendedor vendedor = new Vendedor();
		VendedorRepository repository = new VendedorRepository(ENTITY_MANAGER);
		DepartamentoRepository departamentoRepository = new DepartamentoRepository(ENTITY_MANAGER);
		List<Departamento> departamentos = departamentoRepository.listarTodos();
		List<OptionList> comboDepartamentos = new ArrayList<>();
		departamentos.stream()
			.sorted((d1, d2) -> d1.getDescricao().compareTo(d2.getDescricao()))
			.forEach(departamento -> comboDepartamentos.add(
					new OptionList(departamento.getId(), departamento.getDescricao())));
		

		if (!StringUtil.isBlank(req.getParameter("id")))
			vendedor = repository.obterPorId(Long.valueOf(req.getParameter("id")));

		req.setAttribute("vendedor", vendedor);
		req.setAttribute("departamentos", comboDepartamentos);

		return "admin/vendedor/editar.jsp";
	}

}
