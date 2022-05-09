<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Добавление модификации</title>
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
        <legend>Добавление модификации</legend>
        <form name="mode" action="" method="POST">
            Название: <@spring.formInput "modeForm.name" "" "text"/>    <br/>
            Максимальная скорость: <@spring.formInput "modeForm.maxSpeed" "" "text"/>  км/ч   <br/>
            Разгон до 100 км/ч: <@spring.formInput "modeForm.accelerationTime" "" "text"/> секунд    <br/>
            Объем двигателя: <@spring.formInput "modeForm.engineVolume" "" "text"/> литров  <br/>
            Расход топлива: <@spring.formInput "modeForm.gasMileage" "" "text"/> литров на 100 км   <br/>
            Цена: <@spring.formInput "modeForm.price" "" "text"/> рублей   <br/>
            <input class="btn btn-primary" type="submit" value="Добавить" />
        </form>
    </fieldset>
</div>


</body>

</html>