package br.com.escolpi.ecommerce.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao;
import br.com.escolpi.ecommerce.modelo.Categoria;

@WebServlet(name="categoriaServlet", urlPatterns = "/admin/categoria")
public class AdicionaCategoriaServlet extends HttpServlet {

	private static final long serialVersionUID = -6107830990774324951L;

	private CategoriaDao dao = new CategoriaDao();

	@Override
	public void init() throws ServletException {
		super.init();
		dao = new CategoriaDao();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String acao = "adicionada";

		Categoria categoria = new Categoria();
		String requestId = req.getParameter("id");

		if (requestId != null && requestId != "")
			categoria.setId(Long.valueOf(requestId));

		categoria.setDescricao(req.getParameter("descricao"));

		if (categoria.getId() != null && categoria.getId() > 0) {
			dao.alterar(categoria);
			acao = "alterada";
		} else {
			dao.adicionar(categoria);
		}

		feedback(acao, req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
		String idRequest = req.getParameter("id");
		
		if (idRequest == null || idRequest == "") {
			throw new IllegalArgumentException("Parâmetro ID é obrigatório");
		}

		dao.remover(Long.valueOf(idRequest));
		feedback("excluída", req, resp);
	}

	private void feedback(String acao, HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/categoria/feedback.jsp");
		req.setAttribute("acao", acao);
		dispatcher.forward(req, resp);
	}

}
