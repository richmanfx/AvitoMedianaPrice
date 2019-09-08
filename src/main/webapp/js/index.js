// Вывод заглушки ожидания при нажатии кнопки запуска скрапинга
$('#search_button').on('click', function () {

    let $preloader = $('#p_preloader');
    $preloader.find('.svg_anm');
    $preloader.delay(300).fadeIn('slow');

    checkProgress();        // TODO: Так и не придумал как выдавать из одного сервлета инфу через другой...

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
    $('#min_area').val(minArea);
    $('#max_area').val(maxArea);
    $('#metro').val(metro);

});

// Progressbar на время скрапинга
function checkProgress() {

    let progressBar = $('#progressbar');

    // Получить процент уже выполненной работы
    $.ajax({
        url: '/AvitoMedianaPrice/GetCompletedWork',
        type: 'GET',
        success: function(serverAnswer){
            progressBar.val(serverAnswer);

            // Если процент меньще 100, то через секунду снова запросить
            if (parseInt(serverAnswer) < 100) {
                setTimeout(checkProgress, 1000);    // 1000 миллисекунд
            }
        },
        error: function(){
            alert("Ошибка при ответе на AJAX GET запрос на получение процента уже выполненной работы.");
        }
    });

}
