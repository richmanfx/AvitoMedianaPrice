// Занесение параметров предыдущего поиска в хранилище
$('#new_search_button').on('click', function () {

    // Кнопка
    let button = document.getElementById('new_search_button');

    // Извлечь информацию из "data-*" полей у кнопки
    let minArea = button.dataset.minArea;
    let maxArea = button.dataset.maxArea;
    let metro = button.dataset.metro;

    // В хранилище
    localStorage.setItem('minArea', minArea);
    localStorage.setItem('maxArea', maxArea);
    localStorage.setItem('metro', metro);

});