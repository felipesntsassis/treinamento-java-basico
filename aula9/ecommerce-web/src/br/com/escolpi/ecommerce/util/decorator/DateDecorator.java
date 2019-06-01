package br.com.escolpi.ecommerce.util.decorator;

import java.util.Calendar;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import br.com.escolpi.ecommerce.util.DateUtil;

public class DateDecorator implements DisplaytagColumnDecorator {

	@Override
	public Object decorate(Object value, PageContext context, MediaTypeEnum arg2) throws DecoratorException {
		if (value == null)
			return "";

		return DateUtil.parseToString((Calendar) value, "dd/MM/yyyy");
	}

}
