<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-dark">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
                <a class="nav-link" href="modeTable">Модификации</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="salesTable">Заказы</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-primary" href="/">Выйти</a>
            </li>
            <li class="nav-item">
                <form action="/changeThemeColor/home" method="post">
                    <input class="btn btn-primary" style="margin-left:10px;" type="submit" name="button1" value="Сменить цветовую тему" />
                </form>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>
