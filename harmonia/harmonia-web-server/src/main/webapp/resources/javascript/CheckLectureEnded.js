$(document).ready(checkEnded);
var urls = {};

function setGetLectureUrl(getUrl) {
    urls.getLectureUrl = getUrl;
}

function checkEnded() {
	setInterval(function(){
		$.getJSON(urls.getLectureUrl, function(data) {
			if(data.ended) {
				window.location.replace("feedback");
			}
		});
	}, 5000);
}
