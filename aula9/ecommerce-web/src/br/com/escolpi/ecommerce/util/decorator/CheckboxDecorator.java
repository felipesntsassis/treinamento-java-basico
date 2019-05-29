package br.com.escolpi.ecommerce.util.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class CheckboxDecorator implements DisplaytagColumnDecorator {

	@Override
	public Object decorate(Object value, PageContext context, MediaTypeEnum media) throws DecoratorException {
		
		boolean checkbox = (boolean) value;
		String template = "<i class=\"fa fa-lg %s\">";

		return String.format(template, checkbox ? "fa-check text-success" : "fa-times text-danger");
	}

}
