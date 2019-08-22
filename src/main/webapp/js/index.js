// Вывод заглушки ожидания при нажатии кнопки запуска скрапинга
$('#search_button').on('click', function () {

    let $preloader = $('#p_preloader');
    $preloader.find('.svg_anm');
    $preloader.delay(300).fadeIn('slow');

});
