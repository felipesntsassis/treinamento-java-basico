package br.com.escolpi.ecommerce.servlet.logic.departamento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jpa.repository.departamento.DepartamentoRepository;
import br.com.escolpi.ecommerce.modelo.Departamento;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;

public class ExcluirDepartamentoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		
		DepartamentoRepository repository = new DepartamentoRepository(ENTITY_MANAGER);
		Departamento departamento = repository.obterPorId(Long.valueOf(req.getParameter("id")));
		
		// TODO: Descomentar esta lógica após implementar o mapeamento do Vendedor.
//		if (repository.departamentoTemVendedores(departamento.getId())) {
//			req.setAttribute("feedback", new Feedback(TipoFeedback.ALERTA, 
//					"Este departamento tem vendedores vinculados e não pode ser excluído!"));
//		} else {
			repository.excluir(departamento);
			req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, 
					"Departamento excluído com sucesso!"));
//		}

		return "mvc?logica=ListarDepartamento";
	}

}
