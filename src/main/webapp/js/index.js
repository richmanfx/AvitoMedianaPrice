// Вывод заглушки ожидания при нажатии кнопки запуска скрапинга
$('#search_button').on('click', function () {

    let $preloader = $('#p_preloader');
    $preloader.find('.svg_anm');
    $preloader.delay(300).fadeIn('slow');

});

// Извлечение данных из хранилища и ввод их в поля
$(document).ready(function() {

    // Получить данные из хранилища
    let minArea = localStorage.getItem('minArea');
    let maxArea = localStorage.getItem('maxArea');
    let metro = localStorage.getItem('metro');

    // Освободить хранилище
    localStorage.removeItem('minArea');
    localStorage.removeItem('maxArea');
    localStorage.removeItem('metro');

    // Занести значения в поля
    $('#min_area').val(minArea)
    $('#max_area').val(maxArea)
    $('#metro').val(metro)

});