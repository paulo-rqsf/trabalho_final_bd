
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html style="color: slategrey">
<head>
    <style type="text/css">
        td {
            padding: 0 25px;
        }
    </style>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>
<body >
    <div class = "container">
        <div class="row">
            <div >
                <form action="http://localhost:8080/api/order/makeOrder/1:2-3:4-5:6-12:2-13:5" method="POST" accept-charset="UTF-8">
                    <div class="form-group">
                        <hr color="silver">
                        <h2>myCart</h2>
                    </div>
                    <div class="form-group">
                        <label>Nome Completo: </label>
                        <input type="text" class="form-control" name="name" placeholder="Nome Completo" value="${user.name}" required>
                    </div>
                    <div class="form-group">
                        <hr color="silver">
                        <label>Endereço</label>
                        <div>
                            <hr color="silver">
                            <label>Rua: </label>
                            <input type="text" class="form-control" name="street" placeholder="Rua c/ número" value="${user.address.street}" required>
                            <br>
                            <label>Cep: </label>
                            <input type="text" class="form-control" placeholder="Cep" name="destinyCep" value="${user.address.cep}" required>
                            <br>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="district" placeholder="Bairro" value="${user.address.district}" required>
                                <input type="text" class="form-control" name="city" placeholder="Cidade" value="${user.address.city}" required>
                                <span class="input-group-text">UF</span>
                                <select class="form-select" name="state" value="${user.address.state.cod}" required>
                                    <option selected>Selecione</option>
                                    <c:forEach items="${stateList}" var="state">
                                        <option>${state.cod}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <hr color="silver">
                        </div>
                    </div>
                    <hr color="silver">
                    <div class=".bg-secondary.bg-gradient">
                        <table >

                            <tr class="border_bottom">
                                <td>Id#</td>
                                <td>Name</td>
                                <td>Description</td>
                                <td>Value</td>
                                <td>-- Add Products --</td>
                            </tr>
                            <c:forEach items="${vaccineList}" var="vaccine">
                                <tr class="border_bottom">
                                    <td>${vaccine.productId}</td>
                                    <td>${vaccine.productName}</td>
                                    <td>${vaccine.productDescription}</td>
                                    <td>R$ ${vaccine.productValue}</td>
                                    <td><img src="/img/${vaccine.productName}.png" width="100" height="100"/></td>
                                    <td><input name="addtocart${vaccine.productId}" type="button" value="Add to cart"></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="form-group">
                        <hr color="silver">
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
