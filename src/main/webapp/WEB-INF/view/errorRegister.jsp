<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Ops!</h1>
                <div class="error-details">
                    Usuário já cadastrado!
                </div>
                <br>
                <div class="error-actions">
                    <a href="http://www.localhost:8080/register" class="btn btn-primary btn-lg"><span></span>
                        Tentar novamente </a>
                </div>
                <div class="error-actions">
                    <a href="http://www.localhost:8080/login" class="btn btn-green btn-lg"><span></span>
                        Tentar login </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
