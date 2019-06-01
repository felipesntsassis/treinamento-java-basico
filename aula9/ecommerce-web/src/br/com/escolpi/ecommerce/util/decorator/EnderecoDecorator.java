package br.com.escolpi.ecommerce.util.decorator;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import br.com.escolpi.ecommerce.modelo.Endereco;
import br.com.escolpi.ecommerce.util.StringUtil;

public class EnderecoDecorator implements DisplaytagColumnDecorator {

	@Override
	public Object decorate(Object value, PageContext context, MediaTypeEnum media) throws DecoratorException {
		if (value == null)
			return null;

		Endereco endereco = (Endereco) value;
		StringBuilder template = new StringBuilder(endereco.getLogradouro())
			.append(", ").append(endereco.getNumero()).append(", ")
			.append(!StringUtil.isBlank(endereco.getComplemento()) ? endereco.getComplemento() + ", " : "")
			.append(endereco.getBairro()).append(", ")
			.append(endereco.getMunicipio() + "/" + endereco.getEstado().toString()).append(" - ")
			.append(StringUtil.formatarCEP(endereco.getCep()));

		return template.toString();
	}

}
