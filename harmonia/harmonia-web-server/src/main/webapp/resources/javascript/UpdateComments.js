$(document).ready(updateComments);
var urls = {};

function setGetCommentsUrl(getUrl) {
    urls.getCommentsUrl = getUrl;
}

function updateComments() {
	setInterval(function(){
			$.getJSON(urls.getCommentsUrl, function(data) {
				$("#content").empty();
			    $.each(data.commentDtoList, function(index, element) {
					$("#content").append(element.message + ' <br />');
				});
			});
	}, 5000);
}
