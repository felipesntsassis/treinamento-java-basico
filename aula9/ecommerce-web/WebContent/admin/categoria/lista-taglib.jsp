<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<jsp:useBean id="dao" class="br.com.escolpi.ecommerce.jdbc.dao.CategoriaDao"/>
<c:set var="categorias" value="${dao.listar()}"/>

<c:import url="/cabecalho.jsp"/>

<div class="row">
	<div class="col-12">
		<h3>Cadastro de Categorias</h3>
		<nav class="mb-2">
			<ul class="nav justify-content-end">
				<li class="nav-item">
					<a class="btn btn-primary" href="editar-taglib.jsp">
						<i class="fa fa-plus-square"></i> Nova Categoria
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<div class="row">
	<div class="col-12">
		<div class="table-responsive-sm">
			<display:table class="table table-striped table-hover" list="${categorias}" pagesize="10">
				<display:column property="descricao" title="Descrição" class="col-11"/>
				<display:column property="id" title="Opções" headerClass="col-1 text-center" class="text-center" 
					decorator="br.com.escolpi.ecommerce.util.decorator.ActionDecorator"/>
			</display:table>

<!-- 			<table class="table table-striped table-hover"> -->
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th scope="col" class="col-10">Descrição</th> -->
<!-- 						<th scope="col" class="col-2 text-center">Opções</th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody> -->
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${!empty categorias}"> --%>
<%-- 							<c:forEach items="${categorias}" var="categoria" varStatus="index"> --%>
<!-- 								<tr> -->
<%-- 									<td>${categoria.descricao}</td> --%>
<!-- 									<td class="text-center"> -->
<!-- 										<div class="dropdown"> -->
<!-- 											<button type="button" class="btn btn-sm btn-outline-primary" data-toggle="dropdown"  -->
<!-- 												data-offset="-130,0" aria-haspopup="true" aria-expanded="false"> -->
<!-- 												<i class="fa fa-bars"></i> -->
<!-- 											</button> -->
<!-- 											<div class="dropdown-menu"> -->
<!-- 												<a class="dropdown-item text-primary"  -->
<%-- 													href="/ecommerce-web/admin/categoria/editar-taglib.jsp?id=${categoria.id}"> --%>
<!-- 													<i class="fa fa-edit"></i> Editar -->
<!-- 												</a> -->
<%-- 												<a class="dropdown-item text-danger" href="#" onclick="confirmaExclusao(${categoria.id})"> --%>
<!-- 													<i class="fa fa-trash"></i> Excluir -->
<!-- 												</a> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 									</td> -->
<!-- 								</tr> -->
<%-- 							</c:forEach> --%>
<%-- 						</c:when> --%>
<%-- 						<c:otherwise> --%>
<!-- 							<tr> -->
<!-- 								<td colspan="2"> -->
<!-- 									<em>Nenhuma Categoria foi encontrada.</em> -->
<!-- 								</td> -->
<!-- 							</tr> -->
<%-- 						</c:otherwise> --%>
<%-- 					</c:choose> --%>
<!-- 				</tbody> -->
<!-- 			</table> -->
		</div>
	</div>
</div>
<script>
	const editar = (id) => irPara('/ecommerce-web/admin/categoria/editar-taglib.jsp?id=' + id);

	const confirmaExclusao = (id) => {
		if (confirm('Deseja excluir esta Categoria?')) {
			irPara('/ecommerce-web/admin/categoria?id=' + id);
		}
	};
</script>

<c:import url="/rodape.jsp"/>
