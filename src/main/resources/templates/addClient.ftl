<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Add Client</title>
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
        <legend>Add Client</legend>
        <form name="client" action="" method="POST">
            Surname: <@spring.formInput "clientForm.surname" "" "text"/>    <br/>
            Name: <@spring.formInput "clientForm.name" "" "text"/>    <br/>
            Patronymic: <@spring.formInput "clientForm.patr" "" "text"/>    <br/>
            Phone: <@spring.formInput "clientForm.phone" "" "text"/>    <br/>
            <input class="btn btn-primary" type="submit" value="Add" />
        </form>
    </fieldset>
</div>


</body>

</html>