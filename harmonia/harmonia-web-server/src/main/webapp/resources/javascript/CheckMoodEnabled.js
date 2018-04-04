$(document).ready(checkEnabled);
var urls = {};

function setGetLectureUrl(getUrl) {
    urls.getLectureUrl = getUrl;
}

function checkEnabled() {
	enableDisable();
	setInterval(function(){
		enableDisable();
	}, 5000);
}

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
