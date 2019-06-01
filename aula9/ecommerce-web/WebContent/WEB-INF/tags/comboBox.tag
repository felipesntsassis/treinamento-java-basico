<%@ tag description="Implementa um campo Combox HTML dinâmica que lista opções a partir de um datasource (OptionList)" 
	language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="id" required="true" description="Informa o ID do campo Combobox." %>
<%@ attribute name="name" required="true" description="Informa o nome do campo Combobox." %>
<%@ attribute name="entity" required="true" description="Entidade ao qual o valor da Combobox é referenciada." type="java.lang.Object" %>
<%@ attribute name="items" required="true" description="Lista de opções da Combobox." type="java.util.Collection" %>
<%@ attribute name="cssClass" required="false" description="Classe CSS customizada para o Combobox." %>
<%@ attribute name="required" required="false" description="Informa se o preenchimento do campo é obrigatório." %>

<select id="${id}" name="${name}" class="form-control ${cssClass}" ${required eq true ? 'required' : ''}>
	<option ${empty entity.id ? 'selected' : ''}>Selecione</option>
	<c:forEach items="${items}" var="option">
		<option value="${option.value}" 
			${entity.id == option.value ? 'selected': ''}>
			${option.label}
		</option>
	</c:forEach>
</select>
