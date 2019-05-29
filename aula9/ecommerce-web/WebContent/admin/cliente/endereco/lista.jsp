<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:import url="/cabecalho.jsp"/>

<div class="row">
	<div class="col-12">
		<h3>Cadastro de Endereços</h3>
		<nav class="mb-2">
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<a class="btn btn-outline-secondary mr-2" href="/ecommerce-web/mvc?logica=ListarCliente" tabindex="">
						<i class="fa fa-reply"></i> Voltar
					</a>
				</li>
				<li class="nav-item">
					<a class="btn btn-primary" href="/ecommerce-web/mvc?logica=EditarEndereco&clienteId=${clienteId}">
						<i class="fa fa-plus-square"></i> Novo Endereço
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<div class="row">
	<div class="form-group col-12">
		<strong>Cliente: </strong> ${cliente.nome}
	</div>
	<div class="col-12">
		<div class="table-responsive-sm">
			<display:table list="${enderecos}" pagesize="10" requestURI="/ecommerce-web/mvc?logica=ListarEndereco">
				<display:column property="entity" title="Endereço" 
					decorator="br.com.escolpi.ecommerce.util.decorator.EnderecoDecorator"/>
				<display:column property="entity.enderecoPrincipal" headerClass="text-center" class="text-center" title="Endereço Principal"
					decorator="br.com.escolpi.ecommerce.util.decorator.CheckboxDecorator"/>
				<display:column property="menu" title="Opções" headerClass="text-center" class="text-center"
					decorator="br.com.escolpi.ecommerce.util.decorator.OptionMenuDecorator"/>
			</display:table>
		</div>
	</div>
</div>
<script>
	$(() => {
		// Evento para o botão "Editar"
		$('.btn-editar').on('click', (event) => {
			event.preventDefault();
			irPara('/ecommerce-web/mvc?logica=EditarEndereco&id=' + $(event.currentTarget).data('id')
					+ '&clienteId=${clienteId}');
		});
		

		// Evento para o botão "Excluir"
		$('.btn-excluir').on('click', (event) => {
			event.preventDefault();
			if (confirm('Deseja excluir este endereço?')) {
				irPara('/ecommerce-web/mvc?logica=ExcluirEndereco&id=' + $(event.currentTarget).data('id')
						+ '&clienteId=${clienteId}');
			}
		});
	});
</script>

<c:import url="/rodape.jsp"/>