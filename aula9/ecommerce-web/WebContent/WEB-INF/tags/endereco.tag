<%@ tag import="br.com.escolpi.ecommerce.enumerador.Estados"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="endereco" required="true" type="br.com.escolpi.ecommerce.modelo.Endereco" %>

<c:set var="estados" value="<%=Estados.getLista()%>"/>

<div class="row">
	<input type="hidden" id="hd-endereco-id" name="enderecoId" value="${endereco.id}">
	<div class="form-group col-12 col-lg-2">
		<label for="txt-cep" class="required">CEP:</label>
		<input type="text" name="cep" id="txt-cep" class="form-control" 
			value="${endereco.cep}" maxlength="9" required>
	</div>
	<div class="form-group col-12 col-lg-10">
		<label for="txt-endereco" class="required">Endereço:</label>
		<input type="text" name="endereco" id="txt-endereco" class="form-control campo-endereco" 
			value="${endereco.logradouro}" maxlength="100" required>
	</div>
	<div class="form-group col-12 col-lg-2">
		<label for="txt-numero">Número:</label>
		<input type="text" name="numero" id="txt-numero" class="form-control campo-endereco" 
			value="${endereco.numero}" maxlength="10">
	</div>
	<div class="form-group col-12 col-lg-5">
		<label for="txt-bairro" class="required">Bairro:</label>
		<input type="text" name="bairro" id="txt-bairro" class="form-control campo-endereco" 
			value="${endereco.bairro}" maxlength="120" required>
	</div>
	<div class="form-group col-12 col-lg-5">
		<label for="txt-complemento">Complemento:</label>
		<input type="text" name="complemento" id="txt-complemento" class="form-control campo-endereco" 
			value="${endereco.complemento}" maxlength="120">
	</div>
	<div class="form-group col-12 col-lg-2">
		<label for="combo-estado" class="required">Estado:</label>
		<select name="estado" id="combo-estado" class="form-control campo-endereco" required>
			<option value="" ${empty endereco.estado ? 'selected' : '' }>Selecione</option>
			<c:forEach items="${estados}" var="estado">
				<option value="${estado}" ${endereco.estado eq estado ? 'selected' : '' }>
					${estado.nome}
				</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group col-12 col-lg-10">
		<label for="txt-municipio" class="required">Município:</label>
		<input type="text" name="municipio" id="txt-municipio" class="form-control campo-endereco" 
			value="${endereco.municipio}" maxlength="120">
	</div>
</div>

<script>
	const limparCamposEndereco = () => {
		$('.campo-endereco').val('');
		$('#txt-cep').focus();
	};

	const bloquearCamposEndereco = (bloquear) => {
		if (bloquear) {
			$('.campo-endereco input').val('Pesquisando Endereço...');
		}

		$('.campo-endereco input').prop('disabled', bloquear);
		$('#combo-estado').prop('disabled', bloquear);
	};

	const pesquisarEnderecoPorCep = (cep) => {
		cep = cep.replace(/[^0-9]+/g, '');
		const cepValido = new RegExp(/\d+/g);
		
		if (cep.length === 8 && cepValido.test(cep)) {
			$.ajax({
				url: 'https://viacep.com.br/ws/' + cep + '/json/',
				method: 'GET',
				dataType: 'json',
				beforeSend: () => {
					limparCamposEndereco();
					bloquearCamposEndereco(true);
				},
				success: (data) => {
					bloquearCamposEndereco(false);
					$('#txt-endereco').val(data.logradouro);
					$('#txt-numero').val('');
					$('#txt-bairro').val(data.bairro);
					$('#txt-complemento').val('');
					$('#combo-estado').val(data.uf);
					$('#txt-municipio').val(data.localidade);
				},
				failure: () => {
					bloquearCamposEndereco(false);
					console.error("Ocorreu um erro ao pesquisar por CEP.");
				}
			});
		} else {
			limparCamposEndereco();
		}
	}

	$(() => {
		// Evento input para o campo CEP
		$('#txt-cep').mask('00000-000');
		$('#txt-cep').on('input', (event) => pesquisarEnderecoPorCep(event.currentTarget.value));
	});
</script>