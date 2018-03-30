$(document).ready(checkEnabled);
var urls = {};

function setGetLectureUrl(getUrl) {
    urls.getLectureUrl = getUrl;
}

function checkEnabled() {
	setInterval(function(){
			$.getJSON(urls.getLectureUrl, function(data) {
				if(data.commentsEnabled) {
					$('.btn').prop('disabled', false);
					$('#Comment').prop('disabled', false);
				} else {
					$('.btn').prop('disabled', true);
					$('#Comment').prop('disabled', true);
				}
			});
	}, 5000);
}
