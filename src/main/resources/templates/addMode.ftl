<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Add Mode</title>
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
        <legend>Add Mode</legend>
        <form name="mode" action="" method="POST">
            Name: <@spring.formInput "modeForm.name" "" "text"/>    <br/>
            Max speed: <@spring.formInput "modeForm.maxSpeed" "" "text"/>    <br/>
            Acceleration time: <@spring.formInput "modeForm.accelerationTime" "" "text"/>    <br/>
            Engine volume: <@spring.formInput "modeForm.engineVolume" "" "text"/>    <br/>
            Gas mileage: <@spring.formInput "modeForm.gasMileage" "" "text"/>    <br/>
            Price: <@spring.formInput "modeForm.price" "" "text"/>    <br/>
            <input class="btn btn-primary" type="submit" value="Add" />
        </form>
    </fieldset>
</div>


</body>

</html>