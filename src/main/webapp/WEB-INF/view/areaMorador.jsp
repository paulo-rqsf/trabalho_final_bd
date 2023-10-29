<!DOCTYPE html>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Bem vindo!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url('../../img/Cidadao.jpg');
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
    <h1>Área do Morador</h1>
    <p>Bem vindo! Escolha uma opção:</p>

    <a href="/api/registros-vacinacao/list" class="btn btn-primary">Ver Registros de Vacinação</a>
    <a href="/api/user/loggout" class="btn btn-danger">Sair</a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
