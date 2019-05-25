package br.com.escolpi.ecommerce.util.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class ActionDecorator implements DisplaytagColumnDecorator {

	@Override
	public Object decorate(Object value, PageContext context, MediaTypeEnum arg2) throws DecoratorException {
		Long codigo = Long.valueOf(value.toString());

		String template = ""
			+ "<div class=\"dropdown\">"
			+ 	"<button type=\"button\" class=\"btn btn-sm btn-outline-primary\" data-toggle=\"dropdown\" "
			+ 		"data-offset=\"-130,0\" aria-haspopup=\"true\" aria-expanded=\"false\">"
			+ 		"<i class=\"fa fa-bars\"></i>"
			+ 	"</button>"
			+ 	"<div class=\"dropdown-menu\">"
			+ 		"<a class=\"dropdown-item text-primary\" href=\"#\" onclick=\"editar(%s)\">"
			+ 			"<i class=\"fa fa-edit\"></i> Editar"
			+ 		"</a>"
			+ 		"<a class=\"dropdown-item text-danger\" href=\"#\" onclick=\"confirmaExclusao(%s)\">"
			+ 			"<i class=\"fa fa-trash\"></i> Excluir"
			+ 		"</a>"
			+	 "</div>"
			+ "</div>";

		return String.format(template, codigo, codigo);
	}

	
}
