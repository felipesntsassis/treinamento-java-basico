package br.com.escolpi.ecommerce.util.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import br.com.escolpi.ecommerce.util.NumberUtil;

public class CurrencyDecorator implements DisplaytagColumnDecorator {

	@Override
	public Object decorate(Object value, PageContext context, MediaTypeEnum mediaType) throws DecoratorException {
		if (value == null)
			return "";

		Double valor = (Double) value;

		return NumberUtil.formatarMoeda(valor, true);
	}

}
