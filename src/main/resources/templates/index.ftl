<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Car showroom</title>
    <meta name="description" content="Автосалон">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<#if errorMessage??>
    <div style="color:red;font-style:italic;text-align:center">
        ${errorMessage}
    </div>
</#if>

<div>
    <fieldset>
        <div style="text-align: center;">
        <legend>Авторизация</legend>
        <form name="user" action="" method="POST">
            Логин: <@spring.formInput "userForm.login" "" "text" />  <br/>
            <br/>
            Пароль: <@spring.formInput "userForm.password" "" "password"/> <br/>
            <br/>
            <input class="btn btn-primary" type="submit" value="Войти" />
        </form>
        <p> </p>
        <a class="btn btn-primary" href="/registration">Регистрация</a>
        </div>
    </fieldset>
</div>
</body>
</html>