<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Add Auto</title>
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
        <legend>Add Auto</legend>
        <form name="auto" action="" method="POST">
            Model: <@spring.formInput "autoForm.model" "" "text"/>    <br/>
            Sits: <@spring.formInput "autoForm.sits" "" "text"/>    <br/>
            Year: <@spring.formInput "autoForm.modelYear" "" "text"/>    <br/>
            <div class="input-group mb-3">
            Mode: <select class="custom-select" name="selectedMode" required>
                <#list mode as selectedMode>
                    <option value="${selectedMode.id}">${selectedMode.name}</option>
                </#list>
            </select> <br/>
        </div>
            <input class="btn btn-primary" type="submit" value="Add" />
        </form>
    </fieldset>
</div>


</body>

</html>