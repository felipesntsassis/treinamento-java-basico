package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.VendedorDao;
import br.com.escolpi.ecommerce.modelo.Vendedor;

@WebServlet("/admin/vendedor")
public class AdicionaVendedorServlet extends HttpServlet {

	private static final long serialVersionUID = 4355861423557205824L;

	private VendedorDao dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new VendedorDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Vendedor vendedor = new Vendedor();

		if (req.getParameter("id") != null)
			vendedor.setId(Long.valueOf(req.getParameter("id")));

		vendedor.setNome(req.getParameter("nome"));
		vendedor.setEmail(req.getParameter("email"));
		vendedor.setDepartamento(req.getParameter("departamento"));

		try {
			Double percentual = Double.valueOf(req.getParameter("percComissao").replace(",", "."));
			vendedor.setPercentualComissao(percentual);
		} catch (NumberFormatException e) {
			System.out.println("Erro ao converter para double!");
			return;
		}

		String acao = "incluído";

		if (vendedor.getId() != null && vendedor.getId() > 0) {
			dao.alterar(vendedor);
			acao = "alterado";
		} else {
			dao.adicionar(vendedor);
		}

		feedback(acao, req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		if (req.getParameter("id") == null)
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		
		dao.remover(Long.valueOf(req.getParameter("id")));
		feedback("excluído", req, resp);
	}

	private void feedback(String acao, HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/vendedor/feedback.jsp");
		req.setAttribute("acao", acao);
		dispatcher.forward(req, resp);
	}
}
