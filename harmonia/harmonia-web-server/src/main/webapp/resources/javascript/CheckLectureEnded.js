$(document).ready(startChecking);
var urls = {};

function setGetLectureUrl(getUrl) {
    urls.getLectureUrl = getUrl;
}

function startChecking() {
	checkEnded();
	setInterval(function(){
		checkEnded();
	}, 5000);
}

function checkEnded() {
	$.getJSON(urls.getLectureUrl, function(data) {
		if(data.ended) {
			if(data.feedbackEnabled) {
				window.location.replace("feedback");
			} else {
				window.location.pathname = "/home";
			}
		}
	});
}