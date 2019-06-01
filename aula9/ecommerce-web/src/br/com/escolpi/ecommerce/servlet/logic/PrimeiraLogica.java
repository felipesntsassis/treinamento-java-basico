package br.com.escolpi.ecommerce.servlet.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escolpi.ecommerce.servlet.logic.impl.Logica;

public class PrimeiraLogica implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("Executando a lógica e redirecionando...");

		return "primeira-logica.jsp";
	}

}
