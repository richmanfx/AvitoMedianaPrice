<!doctype html>
<html lang="ru">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Медианная цена</title>
    <link rel="stylesheet" type="text/css" href="css/real.css">
</head>

<body>
<h1>Медианная цена</h1>

<fieldset>
    <legend><strong>Исходные данные</strong></legend>
    Вид объект: <span class="param-value">{{ data.objectType }}</span><br>
    Площадь: от <span class="param-value">{{ data.minArea }}</span> м<sup>2</sup>
             до <span class="param-value">{{ data.maxArea }}</span> м<sup>2</sup><br>
    Станции метро: <span class="param-value">{{ data.metro }}</span>
</fieldset>
<br>

<fieldset>
    <legend><strong>Результат</strong></legend>
    Медианная цена: <span class="result-value">{{ data.medianPrice }}</span> рублей за м<sup>2</sup> в месяц
</fieldset>

</body>
</html>