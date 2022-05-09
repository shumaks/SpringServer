<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Auto Table</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="chooseTable">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="clientsTable">Clients</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="employeesTable">Employees</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="autoTable">Auto</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="modeTable">Mode</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="salesTable">Sales</a>
            </li>
        </ul>
    </div>
</nav>
<div>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th>Model</th>
            <th>Sits</th>
            <th>Year</th>
            <th>Mode name</th>
            <th>Max speed</th>
            <th>Acceleration time</th>
            <th>Engine volume</th>
            <th>Gas mileage</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <#list autos as auto>
            <tr>
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
                        <input class="btn btn-secondary" type="submit" value="Delete">
                    </form>
                </td>
                <td>
                    <form action="/autoTable/update/${auto.id}" method="post">
                        <input class="btn btn-secondary" type="submit" value="Update">
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<a href="addAuto" class="btn btn-secondary">Add auto</a>
</body>
</html>