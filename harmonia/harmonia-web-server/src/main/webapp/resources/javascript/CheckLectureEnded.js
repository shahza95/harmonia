$(document).ready(startChecking);
var urls = {};

function setGetLectureUrl(getUrl) {
    urls.getLectureUrl = getUrl;
}

// poll every 5s
function startChecking() {
	checkEnded();
	setInterval(function(){
		checkEnded();
	}, 5000);
}

// if ended, forward to end of lecture feedback page, else homepage
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