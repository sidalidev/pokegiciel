<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Home">
    <meta name="author" content="">

    <title>Accueil</title>

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
                <a class="nav-link" href="${contextPath}/accueil">Personages<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/ligues">Ligues</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/combat/history">Historique</a>
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
    <h2>Accueil</h2>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>Bienvenue <em class="text-info">${pageContext.request.userPrincipal.name}</em></h2>
    </c:if>
    <h3>Points: ${points}</h3>
    <div class="container">
        <h4>Ajouter un personnage</h4>
        <form method="post" action="/caracters/post?${_csrf.parameterName}=${_csrf.token}">
            <input class="form-control" type="text" required name="name" placeholder="Nom du personnage">
            <input class="form-control" type="number" required name="points" placeholder="Points attribues">
            <button type="submit" class="btn btn-success">Ajouter</button>
        </form>
    </div>

    <div class="container">
        <h4>Liste des personnages</h4>
        <ul class="list-group">
            <c:forEach items="${caracters}" var="caracter">
                <li class="list-group-item">${caracter.name}
                    <em>Points: ${caracter.points}</em>
                    <a class="btn btn-info" href="/caracters/edit?caracterId=${caracter.id}">Modifier</a>
                </li>
            </c:forEach>
        </ul>
    </div>

</div>
</body>
</html>
