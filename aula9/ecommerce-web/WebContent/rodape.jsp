<%@page import="java.util.Calendar"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ano" value="<%=Calendar.getInstance().get(Calendar.YEAR)%>"/>
		</div>
		<!-- /div.container -->
		<footer class="container-fluid pt-2">
			<p>&copy; ${ano} Desenvolvido por Felipe Assis</p>
		</footer>
	</body>

	<script src="/ecommerce-web/assets/js/common.js"></script>
</html>