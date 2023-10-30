<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Registro de Vacinação</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('../../img/RegistroVac.jpg');
            background-size: cover;
            background-repeat: no-repeat;
        }

        .container {
            text-align: center;
            padding: 150px;
        }

        .btn {
            margin: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Registro de Vacinação</h1>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">CPF</th>
                <th scope="col">Numero Sus</th>
                <th scope="col">Id Vacina</th>
                <th scope="col">Data Administração</th>
                <th scope="col">Doses Tomadas</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <c:forEach items="${registrosList}" var="reg">
                    <th scope="row">${reg.idRegistro}</th>
                    <td>${reg.cpf}</td>
                    <td>${numeroSus}</td>
                    <td>${reg.idVacina}</td>
                    <td>${reg.dataAdministracao}</td>
                    <td>${reg.dosesTomadas}</td>
                </c:forEach>
            </tr>
        </tbody>
    </table>

    <a href="/redirectToArea" class="btn btn-danger">Voltar</a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
