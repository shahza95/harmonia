$(document).ready(checkEnded);
var urls = {};

function setGetLectureUrl(getUrl) {
    urls.getLectureUrl = getUrl;
}

function checkEnded() {
	setInterval(function(){
		$.getJSON(urls.getLectureUrl, function(data) {
		console.log(data)
			if(data.ended) {
				if(data.feedbackEnabled) {
					window.location.replace("feedback");
				} else {
					window.location.pathname = "/student/lecture/join";
				}
			}
		});
	}, 5000);
}
