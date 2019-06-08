<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:import url="/cabecalho.jsp"/>

<div class="row">
	<div class="col-12">
		<h3>Cadastro de Departamentos</h3>
		<nav class="mb-2">
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<a class="btn btn-primary" href="/ecommerce-web/mvc?logica=EditarDepartamento">
						<i class="fa fa-plus-square"></i> Novo Departamento
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<div class="row">
	<div class="col-12">
		<div class="table-responsive-sm">
			<display:table list="${departamentos}" pagesize="10" requestURI="/ecommerce-web/mvc?logica=ListarDepartamento">
				<display:column property="entity.descricao" title="Descrição" class="col-11"/>
				<display:column property="menu" title="Opções" headerClass="col-1 text-center" class="text-center" 
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
			irPara('/ecommerce-web/mvc?logica=EditarDepartamento&id=' + $(event.currentTarget).data('id'));
		});

		// Evento para o botão "Excluir"
		$('.btn-excluir').on('click', (event) => {
			event.preventDefault();
			if (confirm('Deseja excluir esta departamento?')) {
				irPara('/ecommerce-web/mvc?logica=ExcluirDepartamento&id=' + $(event.currentTarget).data('id'));
			}
		});
	});
</script>

<c:import url="/rodape.jsp"/>
