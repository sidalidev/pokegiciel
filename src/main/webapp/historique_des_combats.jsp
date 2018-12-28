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

    <title>Combats: historique</title>

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
        <a class="nav-link" href="${contextPath}/ligues">Ligues</a>
    </li>
    <li>
        <a class="nav-link" href="${contextPath}/combat/history">Historique</a>
    </li>
    <li>
        <a class="nav-link" href="${contextPath}/ajouter_un_personage">Ajouter un personage</a>
    </li>
    <li>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input style="cursor: pointer;" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <a class="nav-link" onclick="document.forms['logoutForm'].submit()">Deconnexion</a>
        </form>
    </li>
</ul>
<section>
    <h2 class="title">Combats: historique</h2>
    <div class="content">
        <ul>
            <c:forEach items="${combats}" var="combat">
                <li>
                    <strong>${combat.opponentOne} - ${combat.opponentTwo} </strong><br>
                    Gagnant: ${combat.winner}
                </li>
            </c:forEach>
        </ul>
    </div>
</section>
</body>
