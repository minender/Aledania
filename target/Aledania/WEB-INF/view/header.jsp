<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aledania</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/img/bluemarine_favicon.ico" type="image/vnd.microsoft.icon" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" >
        <script src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.slim.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/desplegar.js"></script>
        <c:choose>
          <c:when test='${!perfilMenu.equals("active")}'>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/mathjax-MathJax-v2.3-248-g60e0a8c/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ClickOnAlias.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/limpiar.js"></script>
          </c:when>
        </c:choose>
        <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-responsive.css" >--%>
</head>
