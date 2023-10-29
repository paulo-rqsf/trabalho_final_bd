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
            padding: 120px;
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
            <form action="/api/user/register" method="POST" accept-charset="UTF-8">
                <div class="form-group">
                    <hr color="silver">
                    <h2>Registrar Morador</h2>
                </div>
                <div class="form-group">
                    <label class="padding-class">Nome Completo:</label>
                    <input type="text" class="form-control" name="nome" placeholder="Nome Completo" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">E-mail:</label>
                    <input type="text" class="form-control" name="email" placeholder="E-mail" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Senha: </label>
                    <input type="password" class="form-control" name="senha" placeholder="Senha" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">CPF:</label>
                    <input type="text" class="form-control" name="cpf" placeholder="CPF" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Numero do Sus:</label>
                    <input type="text" class="form-control" name="numeroSus" placeholder="Numero do Sus" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Nome Social:</label>
                    <input type="text" class="form-control" name="nomeSocial" placeholder="Nome Social" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Data de Nascimento:</label>
                    <input type="date" class="form-control" name="dataNascimento" placeholder="Data de Nascimento" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Sexo:</label>
                    <select class="form-select" name="sexo" value="sexo" required>
                        <option selected>Selecione</option>
                        <c:forEach items="${sexList}" var="sexo">
                            <option>${sexo}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="padding-class">Nome da mãe:</label>
                    <input type="text" class="form-control" name="nomeMae" placeholder="Nome da Mãe" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Telefone:</label>
                    <input type="text" class="form-control" name="telefone" placeholder="Telefone" required>
                </div>
                <div class="form-group">
                    <label class="padding-class">Estado Civil:</label>
                    <select class="form-select" name="estadoCivil" value="estadoCivil" required>
                        <option selected>Selecione</option>
                        <c:forEach items="${estadoCivilList}" var="estadoCivil">
                            <option>${estadoCivil}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="padding-class">Escolaridade:</label>
                    <select class="form-select" name="escolaridade" value="escolaridade" required>
                        <option selected>Selecione</option>
                        <c:forEach items="${escolaridadeList}" var="escolaridade">
                            <option>${escolaridade}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="padding-class">Etnia:</label>
                    <select class="form-select" name="etnia" value="etnia" required>
                        <option selected>Selecione</option>
                        <c:forEach items="${etniaList}" var="etnia">
                            <option>${etnia}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label class="padding-class">Possui Plano de Saúde?:</label>
                    <select class="form-select" name="temPlano" id="temPlano">
                        <option selected>Selecione</option>
                        <option value="sim">Sim</option>
                        <option value="nao">Não</option>
                    </select>
                </div>
                <div class="form-group">
                    <hr color="silver">
                    <h3>Endereço</h3>
                    <div class="form-group">
                        <label class="padding-class">Cep:</label>
                        <input type="text" class="form-control" name="cep" placeholder="Cep" required>
                    </div>
                    <div class="form-group">
                        <label class="padding-class">Logradouro:</label>
                        <input type="text" class="form-control" name="logradouro" placeholder="Logradouro" required>
                    </div>
                    <div class="form-group">
                        <label class="padding-class">Número:</label>
                        <input type="text" class="form-control" name="numero" placeholder="Numero" required>
                    </div>
                    <div class="form-group">
                        <label class="padding-class">Bairro:</label>
                        <input type="text" class="form-control" name="bairro" placeholder="Bairro" required>
                    </div>
                    <div class="form-group">
                        <label class="padding-class">Cidade:</label>
                        <input type="text" class="form-control" name="cidade" placeholder="Cidade" required>
                    </div>
                        <label class="padding-class">UF:</label>
                        <select class="form-select" name="uf" value="uf" required>
                            <option selected>Selecione</option>
                            <c:forEach items="${stateList}" var="state">
                                <option>${state}</option>
                            </c:forEach>
                        </select>
                    <div class="form-group">
                        <label class="padding-class">Complemento:</label>
                        <input type="text" class="form-control" name="complemento" placeholder="Complemento" required>
                    </div>
                </div>
                <div class="form-group">
                    <hr color="silver">
                    <button type="submit" class="btn btn-success">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>