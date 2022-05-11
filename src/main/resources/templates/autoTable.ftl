<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Автомобили</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        figure img {
            width: 50%;
            height: 50%;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home">Главная</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
        </ul>
    </div>
</nav>
<div>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Изображение</th>
            <th>Модель</th>
            <th>Количество мест</th>
            <th>Год выпуска</th>
            <th>Модификация</th>
            <th>Максимальная скорость, км/ч</th>
            <th>Разгон до 100 км/ч, сек</th>
            <th>Объем двигателя, л</th>
            <th>Расход топлива, литров на 100 км</th>
            <th>Цена, руб.</th>
        </tr>
        </thead>
        <tbody>
        <#list autos as auto>
            <tr>
                <td>
                    <#list images as image>
                        <#if image.id == auto.id>
                            <figure>
                                <img src="${image.path}" alt="-"/>
                            </figure>
                        </#if>
                    </#list>
                </td>
                <td>${auto.model}</td>
                <td>${auto.sits}</td>
                <td>${auto.modelYear}</td>
                <td>${auto.mode.name}</td>
                <td>${auto.mode.maxSpeed}</td>
                <td>${auto.mode.accelerationTime}</td>
                <td>${auto.mode.engineVolume}</td>
                <td>${auto.mode.gasMileage}</td>
                <td>${auto.mode.price}</td>
                <td>
                    <form action="/autoTable/delete/${auto.id}" method="post">
                        <input class="btn btn-secondary" type="submit" value="Удалить">
                    </form>
                </td>
                <td>
                    <form action="/autoTable/update/${auto.id}" method="post">
                        <input class="btn btn-secondary" type="submit" value="Редактировать">
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<a href="addAuto" class="btn btn-secondary">Добавить</a>
</body>
</html>