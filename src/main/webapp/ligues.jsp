<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Ligues</title>

    <link href="${contextPath}/resources/style/bulma.min.css" rel="stylesheet">

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
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/accueil">Personages</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${contextPath}/ligues">Ligues<span class="sr-only">(current)</span></a>
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
    <h2>Ligues (votre ligue: <em>${userLeague.name}</em>)</h2>
    <div>
        <h4>Ajouter une ligue</h4>
        <form method="post" action="${contextPath}/ligues/post?${_csrf.parameterName}=${_csrf.token}">
            <input name="name" class="form-control" placeholder="Nom de la ligue">
            <button class="btn btn-success" type="submit">Ajouter</button>
        </form>
    </div>

    <div>
        <h4>Liste des ligues</h4>
        <ul class="list-group">
            <c:forEach items="${ligues}" var="league">
                <li class="list-group-item">
                    <form id="selectLeague${league.id}" method="post"
                          action="${contextPath}/ligues/put?${_csrf.parameterName}=${_csrf.token}&id=${league.id}">
                        <a class="btn btn-info"
                           onclick="document.forms['selectLeague${league.id}'].submit()"
                           style="color: white">${league.name}</a>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>


    <div>
        <h4>Liste des Personages de la ligue <em>${userLeague.name}</em>:</h4>
        <ul class="list-group">
            <c:forEach items="${caracters}" var="caracter">
                <li class="list-group-item">
                        ${caracter.name}
                    <a href="/combat?opponentId=${caracter.id}" class="btn btn-danger">Combattre</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div>
        <h4>Liste des Dresseurs de la ligue <em>${userLeague.name}</em>:</h4>
        <ul class="list-group">
            <c:forEach items="${leagueUsers}" var="user">
                <li class="list-group-item">
                        ${user.username}
                </li>
            </c:forEach>
        </ul>
    </div>


</div>
</body>
</html>
