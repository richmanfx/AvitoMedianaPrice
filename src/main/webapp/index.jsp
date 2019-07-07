<!doctype html>
<html lang="ru">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <title>Avito Price</title>
    <link rel="stylesheet" type="text/css" href="css/real.css">
</head>

<body>
    <h1>Параметры объекта</h1>

    <form method="post" action="Scraping">
        <fieldset>
            <legend>Параметры объекта для поиска</legend>
            <p>
                <label for="object_type">Вид объекта: </label>
                <select required autofocus name="object_type" id="object_type">
                    <option value="Гостиница">Гостиница</option>
                    <option value="Офисное помещение">Офисное помещение</option>
                    <option value="Помещение общественного питания">Помещение общественного питания</option>
                    <option value="Помещение свободного назначения" selected>Помещение свободного назначения</option>
                    <option value="Производственное помещение">Производственное помещение</option>
                    <option value="Складское помещение">Складское помещение</option>
                    <option value="Торговое помещение">Торговое помещение</option>
                </select>
            </p>

            <p><label for="min_area">Минимальная площадь, м<sup>2</sup>: </label> <input type="text" name="min_area" id="min_area"></p>
            <p><label for="max_area">Максимальная площадь, м<sup>2</sup>: </label> <input type="text" name="max_area" id="max_area"></p>
        </fieldset>
        <p><input type="submit" value="Поиск"></p>
    </form>

</body>

</html>
