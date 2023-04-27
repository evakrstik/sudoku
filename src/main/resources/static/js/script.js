function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (timer++ > 100000) {
            timer = duration;
        }
    }, 1000);
}

function onGameLoad() {
    var duration = 0;
    var display = document.querySelector('#timer');
    startTimer(duration, display);
}

function switchTheme() {
    this.document.body.classList.toggle('dark-mode');
}