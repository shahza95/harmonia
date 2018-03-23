$(document).ready(updateComments);
var urls = {};

function setGetUrl(getUrl) {
	console.log("URL: " + getUrl);
    urls.getUrl = getUrl;
}

function updateComments() {
	setInterval(function(){
			$.getJSON(urls.getUrl, function(data) {
				$("#content").empty();
			    $.each(data.commentDtoList, function(index, element) {
					$("#content").append(element.message + ' <br />');
				});
			});
	}, 5000);
}
