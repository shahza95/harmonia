$(document).ready(checkEnabled);
var urls = {};

function setGetLectureUrl(getUrl) {
    urls.getLectureUrl = getUrl;
}

// poll every 5s
function checkEnabled() {
	enableDisable();
	setInterval(function(){
		enableDisable();
	}, 5000);
}

// if disabled, ensure input and button are disabled, else enabled
function enableDisable() {
	$.getJSON(urls.getLectureUrl, function(data) {
		if(data.moodEnabled) {
			$('.btn').prop('disabled', false);
			$('#Mood').prop('disabled', false);
		} else {
			$('.btn').prop('disabled', true);
			$('#Mood').prop('disabled', true);
		}
	});
}
