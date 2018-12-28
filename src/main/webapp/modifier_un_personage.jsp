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

    <title>Modifier un personage</title>

    <link href="${contextPath}/resources/style/bulma.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<ul>
    <li>
        <a class="nav-link" href="${contextPath}/accueil">Personages</a>
    </li>
    <li>
        <a class="nav-link" href="${contextPath}/ajouter_un_personage">Ajouter un personage</a>
    </li>
    <li>
        <a class="nav-link" href="${contextPath}/ligues">Ligues</a>
    </li>
    <li>
        <a class="nav-link" href="${contextPath}/ajouter_une_ligue">Ajouter une ligue</a>
    </li>
    <li>
        <a class="nav-link" href="${contextPath}/combat/history">Historique</a>
    </li>
    <li>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input style="cursor: pointer;" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <a class="nav-link" onclick="document.forms['logoutForm'].submit()">Deconnexion</a>
        </form>
    </li>
</ul>
<section>
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


</section>
</body>
</html>
