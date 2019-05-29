package br.com.escolpi.ecommerce.servlet.logic.endereco;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.enumerador.Estados;
import br.com.escolpi.ecommerce.enumerador.TipoFeedback;
import br.com.escolpi.ecommerce.jdbc.dao.EnderecoDao;
import br.com.escolpi.ecommerce.modelo.Endereco;
import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;
import br.com.escolpi.ecommerce.util.Feedback;
import br.com.escolpi.ecommerce.util.NumberUtil;
import br.com.escolpi.ecommerce.util.StringUtil;

public class SalvarEnderecoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Endereco endereco = new Endereco();
		EnderecoDao dao = new EnderecoDao();

		if (!StringUtil.isBlank(req.getParameter("enderecoId")))
			endereco.setId(Long.valueOf(req.getParameter("enderecoId")));

		endereco.setClienteId(Long.valueOf(req.getParameter("clienteId")));
		endereco.setCep(StringUtil.removerCaracteresEspeciais(req.getParameter("cep")));
		endereco.setLogradouro(req.getParameter("endereco"));
		endereco.setNumero(req.getParameter("numero"));
		endereco.setBairro(req.getParameter("bairro"));
		endereco.setComplemento(req.getParameter("complemento"));
		endereco.setEstado(Estados.obterPorSigla(req.getParameter("estado")));
		endereco.setMunicipio(req.getParameter("municipio"));
		endereco.setEnderecoPrincipal(req.getParameter("enderecoPrincipal") != null ? true : false);
		String feedback = "incluído";

		if (endereco.isEnderecoPrincipal()) {
			Endereco principal = dao.obterEnderecoPrincipal(endereco.getClienteId());

			if (principal != null && !NumberUtil.isNullOrZero(principal.getId())) {
				principal.setEnderecoPrincipal(false);
				dao.alterar(principal);
			}
		}

		if (!NumberUtil.isNullOrZero(endereco.getId())) {
			feedback = "alterado";
			dao.alterar(endereco);
		} else {
			dao.adicionar(endereco);
		}

		req.setAttribute("feedback", new Feedback(TipoFeedback.SUCESSO, "Endereço " + feedback + " com sucesso!"));

		return "mvc?logica=ListarEndereco&clienteId=" + endereco.getClienteId();
	}

}
