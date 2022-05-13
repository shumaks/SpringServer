<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Работники</title>
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
            <li class="nav-item">
                <a class="nav-link" href="employeesTable">Работники</a>
            </li>
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
                <form action="/changeThemeColor/employeesTable" method="post">
                    <input class="btn btn-primary" style="margin-left:10px;" type="submit" name="button1" value="Сменить цветовую тему" />
                </form>
            </li>
        </ul>
    </div>
</nav>
<div>
    <input type="text" id="input" onkeyup="search()" placeholder="Поиск по фамилии" size="215">
    <script>
        function search() {
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("input");
            filter = input.value.toUpperCase();
            table = document.getElementById("table");
            tr = table.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
    <table class="table table-dark" id="table">
        <thead class="thead-light">
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Должность</th>
            <th>Адрес</th>
            <th>Номер телефона</th>
        </tr>
        </thead>
        <tbody>
        <#list employees as employee>
            <tr>
                <td>${employee.surname}</td>
                <td>${employee.name}</td>
                <td>${employee.patr}</td>
                <td>${employee.position}</td>
                <td>${employee.address}</td>
                <td>${employee.phone}</td>
                <td>
                    <form action="/employeesTable/delete/${employee.id}" method="post">
                        <input class="btn btn-light" type="submit" value="Удалить">
                    </form>
                </td>
                <td>
                    <form action="/employeesTable/update/${employee.id}" method="post">
                        <input class="btn btn-light" type="submit" value="Редактировать">
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<a href="addEmployee" class="btn btn-light">Добавить</a>
</body>
</html>