package br.com.escolpi.ecommerce.util.decorator;

import java.util.Set;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import br.com.escolpi.ecommerce.util.OptionMenu;

public class OptionMenuDecorator implements DisplaytagColumnDecorator {
	
	private String template;

	@SuppressWarnings("unchecked")
	@Override
	public Object decorate(Object value, PageContext context, MediaTypeEnum mediaType) throws DecoratorException {
		Set<OptionMenu> menu = (Set<OptionMenu>) value;

		template = ""
			+ "<div class=\"dropdown\">"
			+ 	"<button type=\"button\" class=\"btn btn-sm btn-outline-primary\" data-toggle=\"dropdown\" "
			+ 		"data-offset=\"-130,0\" aria-haspopup=\"true\" aria-expanded=\"false\">"
			+ 		"<i class=\"fa fa-bars\"></i>"
			+ 	"</button>"
			+ 	"<div class=\"dropdown-menu\">";
		menu.stream()
			.sorted((m1, m2) -> m1.getLabel().compareToIgnoreCase(m2.getLabel()))
			.forEach(option -> {
				template += ""
					+ "<a class=\"dropdown-item btn-" + option.getAction() + " " + option.getCssColor() 
					+	"\" href=\"#\" data-id=\"" + option.getId() + "\">"
					+ 	"<i class=\"fa " + option.getIcon() + "\"></i> " + option.getLabel()
					+ "</a>";
			});
		template += "</div>"
			+ "</div>";

		return template;
	}

}
