<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Добавление автомобиля</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-dark">
<#if errorMessage??>
    <div style="color:red;font-style:italic;">
        ${errorMessage}
    </div>
</#if>

<div>
    <fieldset style="color:#ffffff" >
        <legend>Добавление автомобиля</legend>
        <form class="custom-file-input" name="auto" action="" method="POST" enctype="multipart/form-data">
            Изображение: <@spring.formInput "uploadForm.fileData" "" "file"/>    <br/>
            Модель: <@spring.formInput "autoForm.model" "" "text"/>    <br/>
            Количество мест: <@spring.formInput "autoForm.sits" "" "text"/>    <br/>
            Год выпуска: <@spring.formInput "autoForm.modelYear" "" "text"/>    <br/>
            <div class="input-group mb-3">
            Модификация: <select class="custom-select" name="selectedMode" required>
                <#list mode as selectedMode>
                    <option value="${selectedMode.id}">${selectedMode.name}</option>
                </#list>
            </select> <br/>
        </div>
            <input class="btn btn-primary" type="submit" value="Добавить" />
        </form>
    </fieldset>
</div>


</body>

</html>