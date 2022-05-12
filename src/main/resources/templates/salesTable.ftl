<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Заказы</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home">Главная</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="clientsTable">Клиенты</a>
            </li>
            <#if userForm.accessRights == 'director'>
                <li class="nav-item">
                    <a class="nav-link" href="employeesTable">Работники</a>
                </li>
            </#if>
            <li class="nav-item">
                <a class="nav-link" href="autoTable">Автомобили</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="autoTable">Модификации</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="salesTable">Заказы</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-primary" href="/">Выйти</a>
            </li>
        </ul>
    </div>
</nav>
<div>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Дата</th>
            <th>Работник</th>
            <th>Клиент</th>
            <th>Автомобиль</th>
        </tr>
        </thead>
        <tbody>
        <#list sales as sale>
            <tr>
                <td>${sale.date}</td>
                <td>${sale.employee.surname}</td>
                <td>${sale.client.surname}</td>
                <td>${sale.auto.model}</td>
                <td>
                    <form action="/salesTable/delete/${sale.id}" method="post">
                        <input class="btn btn-secondary" type="submit" value="Удалить">
                    </form>
                </td>
                <td>
                    <form action="/salesTable/update/${sale.id}" method="post">
                        <input class="btn btn-secondary" type="submit" value="Редактировать">
                    </form>
                </td>
                <td>
                    <form action="/salesTable/createPdf/${sale.id}" method="get">
                        <input class="btn btn-secondary" type="submit" value="Сохранить в PDF">
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<a href="addSale" class="btn btn-secondary">Добавить</a>
</body>
</html>