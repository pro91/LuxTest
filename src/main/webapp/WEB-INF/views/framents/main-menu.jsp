<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: white">

    <h1 style="display: inline-block; position: relative; left: 5%">
    <a class="navbar-bran mr-2" href="/"><img src="/statics/images/lux3.png" width="150" height="70" alt="" ></a></h1>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav nav nav-pills nav-fill">
            <li class="nav-item active mx-1">
                <a class="navbar-brand" href="/">

                </a>
            </li>
            <sec:authorize access="isAuthenticated()">
                <%-- Sekcje główne menu dostępne tylko dla zalogowanych użytkowników --%>
            </sec:authorize>
        </ul>
    </div>
    <sec:authorize access="!isAuthenticated()">
        <div style="margin-right: 20px"> Masz już konto? <strong></strong></div>
        <form class="form-inline mr-2 mt-3" method="get" action="/login">
            <button class="btn btn-outline-primary" type="submit">Zaloguj</button>
        </form>
        <form class="form-inline mt-3" method="get" action="/register">
            <button class="btn btn-outline-success" type="submit">Zarejestruj</button>
        </form>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div style="margin-right: 20px"> Witaj, <strong>${pageContext.request.userPrincipal.principal.username}</strong></div>
        <form class="form-inline mt-3" method="post" action="/logout">
            <button class="btn btn-outline-primary" type="submit">Wyloguj</button>
            <sec:csrfInput/>
        </form>
    </sec:authorize>
</nav>