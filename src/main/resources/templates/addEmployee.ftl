<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Добавление работника</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<div>
    <fieldset>
        <legend>Добавление работника</legend>
        <form name="employee" action="" method="POST">
            Фамилия: <@spring.formInput "employeeForm.surname" "" "text"/>    <br/>
            Имя: <@spring.formInput "employeeForm.name" "" "text"/>    <br/>
            Отчество: <@spring.formInput "employeeForm.patr" "" "text"/>    <br/>
            Должность: <@spring.formInput "employeeForm.position" "" "text"/>    <br/>
            Адрес: <@spring.formInput "employeeForm.address" "" "text"/>    <br/>
            Номер телефона: <@spring.formInput "employeeForm.phone" "" "text"/>    <br/>
            <input class="btn btn-primary" type="submit" value="Добавить" />
        </form>
    </fieldset>
</div>


</body>

</html>