<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Home">
    <meta name="author" content="">

    <title>Edition d'un personnage</title>

    <link href="${contextPath}/resources/css/bulma.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Pokegiciel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="${contextPath}/home">Personages<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/leagues">Ligues</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/fight/history">Historique</a>
            </li>
            <li class="nav-item">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input style="cursor: pointer;" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <a class="nav-link" onclick="document.forms['logoutForm'].submit()">Deconnexion</a>
                </form>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <h2>Edition d'un personage</h2>
    <form method="post" modelAttribute="caracterForm"
          action="/caracter/put?${_csrf.parameterName}=${_csrf.token}">
        <input class="form-control" value="${caracter.id}" type="hidden" name="id" placeholder="Nom du personnage">
        <input class="form-control" type="text" required value="${caracter.name}" name="name"
               placeholder="Nom du personnage">
        <input class="form-control" type="number" required value="${caracter.points}" name="points"
               placeholder="Points attribues">
        <button type="submit" class="btn btn-info">Modifier</button>
    </form>
    <form method="post" modelAttribute="caracterForm"
          action="/caracter/delete?${_csrf.parameterName}=${_csrf.token}">
        <input value="${caracter.id}" type="hidden" name="id">
        <button type="submit" class="btn btn-danger">Supprimer</button>
    </form>


</div>
</body>
</html>
