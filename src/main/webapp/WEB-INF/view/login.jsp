
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="color: slategrey">
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>
<body >
    <div class = "container" style="text-align-all: center">
        <div class="row">
            <div >
                <form action="/api/user/login" method="POST" accept-charset="ISO-8859-1">
                    <div class="form-group">
                        <hr color="silver">
                        <h2>Login</h2>
                    </div>
                    <div class="form-group">
                        <label>Email ou CPF: </label>
                        <input type="text" class="form-control" name="username" placeholder="Email ou CPF" required>
                    </div>
                    <div class="form-group">
                        <label>Senha: </label>
                        <input type="password" class="form-control" name="senha" placeholder="Senha" required>
                    </div>
                    <div class="form-group">
                        <hr color="silver">
                        <button type="submit" class="btn btn-success">Login</button>
                    </div>
                </form>
                <form action="/register" method="POST" accept-charset="ISO-8859-1">
                    <button type="submit" class="btn btn-light">Register</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
