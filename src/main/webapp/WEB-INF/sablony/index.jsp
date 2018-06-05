<%@page contentType="text/html;charset=UTF-8"
        language="java" %>
<%@taglib prefix="jstl"
          uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<head>
    <meta charset="UTF-8">
    <title>Terminaler</title>
</head>
<body>
    <form id="form" method="post">
        <div>Vyberte příkaz:
            <select name="command">
                <option value="${commandLinuxLst.get(0)}">Vytvoř adresář</option>
                <option value="${commandLinuxLst.get(1)}">Smaž adresář</option>
                <option value="${commandLinuxLst.get(2)}">Ping</option>
            </select>
        </div>

        <div>
            Napište paramatry: <input name="parameter" value="${commandForm.parameter}">
            <spring:errors path="commandForm.parameter"></spring:errors>
        </div>

        <div>
            <input type="submit" value="Run"/>
        </div>
    </form>
</body>
</html>