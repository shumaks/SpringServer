<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Редактирование клиента</title>
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
        <legend>Редактирование клиента</legend>
        <form name="client" action="" method="POST">
            Фамилия: <@spring.formInput "clientForm.surname" "" "text"/>    <br/>
            Имя: <@spring.formInput "clientForm.name" "" "text"/>    <br/>
            Отчество: <@spring.formInput "clientForm.patr" "" "text"/>    <br/>
            Номер телефона: <@spring.formInput "clientForm.phone" "" "text"/>    <br/>
            <input class="btn btn-primary" type="submit" value="Сохранить" />
        </form>
    </fieldset>
</div>
</body>
</html>