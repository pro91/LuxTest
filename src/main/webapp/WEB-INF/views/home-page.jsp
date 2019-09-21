<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css">
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/webjars/popper.js/1.14.1/umd/popper.js"></script>
</head>
<body background="/statics/images/people-beach.jpg";>
<jsp:include page="framents/main-menu.jsp"/>
<div class="container">
    <div class="row" style="margin-top: 40px">
        <div class="col-1"></div>
        <div class="col-10" style="padding-bottom: 20px"><h2>Rejestracja</h2></div>
        <div class="col-1"></div>
    </div>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-6">
            <form enctype="multipart/form-data" method="post" action="/register">
                <div class="form-group">
                    <label for="username">Nazwa użytkownika</label>
                    <input type="text" required name="username" id="username" class="form-control"
                           placeholder="Podaj nazwę użytkownika"/>
                </div>
                <div class="form-group">
                    <label for="firstName">Imię</label>
                    <input type="text" required name="firstName" id="firstName" class="form-control"
                           placeholder="Podaj imię"/>
                </div>
                <div class="form-group">
                    <label for="lastName">Nazwisko</label>
                    <input type="text" required name="lastName" id="lastName" class="form-control"
                           placeholder="Podaj nazwisko"/>
                </div>

                <div class="form-group">
                    <label for="password">Hasło</label>
                    <input type="password" required name="password" id="password"
                           class="form-control" placeholder="Podaj hasło"/>
                </div>
                    <p>
                        Add photo: <input type="file" name="file"/>
                    </p>


<%--                <div class="form-group">--%>
<%--                    <label for="lastName">Płeć</label>--%>
<%--                    &lt;%&ndash;                    <input type="text" required name="plec" id="plec" class="form-control"/>&ndash;%&gt;--%>
<%--                    <br/>--%>
<%--                    <input type="checkbox"/> K--%>
<%--                    <input type="checkbox"/> M--%>
<%--                    <br/>--%>

<%--                </div>--%>
                <button class="btn btn-primary" type="submit">Zarejestruj</button>
                <button class="btn btn-secondary" type="reset">Wyczyść dane</button>
                <sec:csrfInput/>
            </form>
        </div>
        <div class="col-5"></div>
    </div>
</div>
</body>
<footer>
    <p>
    <div style="margin: 15px; text-align: center; bottom: 0px; position: static">
    <span style="margin: 10px">
    <a href="/regulamin" class="k21 no">Regulamin</a>
    </span>
        <span style="margin: 10px">
    <a href="/polityka-prywatnosci" class="k21 no">Polityka prywatności</a>
    </span>
        <span style="margin: 10px">
    <a href="/pomoc" class="k21 no">Kontakt i pomoc</a>
    </span>
        <span style="margin: 10px">
        <a href="/onas" class="k21 no">O Nas </a>
    </span>
    </div>
    </p>
    <p>
    <div style="color: dimgrey; bottom: 0px; position: static; margin: 15px 0; font-size: 14px; text-align: center" id="footer-copyright"> Copyright © 2019 Luxury <span class="hide"> ▼</span></div>
    </p>
</footer>
</html>