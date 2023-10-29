<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="color: slategrey">
<head>
    <title>Registrar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <style>
        .container {
            padding: 100px;
        }
        .padding-class {
            padding: 8px 0 8px 0;
        }
    </style>
</head>
<body >
<div class = "container" style="text-align-all: center">
    <div class="row">
        <div >
            <form action="/api/vacina/registrar" method="POST" accept-charset="UTF-8">
                <div class="form-group">
                    <hr color="silver">
                    <h2>Registrar Vacina</h2>
                </div>
                <div class="form-group">
                    <label class="padding-class">ID da Vacina:</label>
                    <input type="text" class="form-control" name="idVacina" placeholder="ID da Vacina" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Nome:</label>
                    <input type="text" class="form-control" name="nome" placeholder="Nome" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Descrição: </label>
                    <input type="text" class="form-control" name="descricao" placeholder="Descrição" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Lote:</label>
                    <input type="text" class="form-control" name="lote" placeholder="Lote" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Fabricante:</label>
                    <input type="text" class="form-control" name="fabricante" placeholder="Fabricante" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Data de Validade:</label>
                    <input type="date" class="form-control" name="dataValidade" placeholder="Data de Validade" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Quantidade de Doses:</label>
                    <select class="form-select" name="quantidadeDoses" value="quantidadeDoses" required>
                        <option selected>Selecione</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="vitalicio">Vitalício</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="padding-class">Intervalo entre Doses (em meses):</label>
                    <select class="form-select" name="intervaloDoses" value="intervaloDoses" required>
                        <option selected>Selecione</option>
                        <option value="1">1 mês</option>
                        <option value="2">2 meses</option>
                        <option value="3">3 meses</option>
                        <option value="4">4 meses</option>
                        <option value="4">6 meses</option>
                        <option value="4">12 meses</option>
                        <option value="outro">Outro</option>
                    </select>
                </div>

                <div class="form-group">
                    <hr color="silver">
                    <button type="submit" class="btn btn-success">Register</button>
                    <a href="/redirect?forward=areaAdmin.jsp" class="btn btn-danger">Voltar</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>