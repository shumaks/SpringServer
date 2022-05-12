<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Добавление заказа</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body style="background-color:grey;">
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<div>
    <fieldset>
        <legend>Добавление заказа</legend>
        <form name="auto" action="" method="POST">
            <div class="input-group mb-3">
            Работник: <select class="custom-select" name="selectedEmployee" required>
                <#list employees as selectedEmployee>
                    <option value="${selectedEmployee.id}">${selectedEmployee.surname}</option>
                </#list>
            </select> <br/>
            </div>
            <div class="input-group mb-3">
            Клиент: <select class="custom-select" name="selectedClient" required>
                <#list clients as selectedClient>
                    <option value="${selectedClient.id}">${selectedClient.surname}</option>
                </#list>
            </select> <br/>
            </div>
            <div class="input-group mb-3">
            Автомобиль: <select class="custom-select" name="selectedAuto" required>
                <#list autos as selectedAuto>
                    <option value="${selectedAuto.id}">${selectedAuto.model}</option>
                </#list>
            </select> <br/>
            </div>
            <input class="btn btn-primary" type="submit" value="Добавить" />
        </form>
    </fieldset>
</div>


</body>

</html>