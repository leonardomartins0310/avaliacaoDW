<%-- 
    Document   : produto2
    Created on : 30/05/2018, 09:56:00
    Author     : Jaque
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/FormularioServlet">
            <input type="text" name="idTipoProduto"/>
            <input type="text" name="tipoProduto"/>
            <input type="submit" name="ok"/>
        </form>
    </body>
</html>
