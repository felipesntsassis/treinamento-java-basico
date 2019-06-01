package br.com.escolpi.ecommerce.servlet.logic.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logica {

	String executa(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
