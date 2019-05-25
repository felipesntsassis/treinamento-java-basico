<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="id" required="true" description="Informa o ID do campo HTML." %>
<%@ attribute name="name" required="true" description="Informa o nome do campo HTML." %>
<%@ attribute name="value" required="true" description="Informa o valor atual do campo." %>
<%@ attribute name="required" required="false"
	description="Informa se o preenchimento do campo é obrigatório." %>

<div class="input-group">
	<input type="text" name="${name}" id="${id}" class="form-control" 
		value="${value}" maxlength="100" ${required eq true ? 'required' : ''}>
	<div class="input-group-append">
		<div class="input-group-text">
			<i class="fa fa-calendar"></i>
		</div>
	</div>
</div>

<script>
	$(() => $('#${id}').datepicker({
		language: 'pt-BR'
	}));
</script>