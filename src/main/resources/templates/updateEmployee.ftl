<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Update employee</title>
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
        <legend>Update employee info</legend>
        <form name="employee" action="" method="POST">
            Surname: <@spring.formInput "employeeForm.surname" "" "text"/>    <br/>
            Name: <@spring.formInput "employeeForm.name" "" "text"/>    <br/>
            Patronymic: <@spring.formInput "employeeForm.patr" "" "text"/>    <br/>
            Position: <@spring.formInput "employeeForm.position" "" "text"/>    <br/>
            Address: <@spring.formInput "employeeForm.address" "" "text"/>    <br/>
            Phone: <@spring.formInput "employeeForm.phone" "" "text"/>    <br/>
            <input class="btn btn-primary" type="submit" value="Update" />
        </form>
    </fieldset>
</div>
</body>
</html>