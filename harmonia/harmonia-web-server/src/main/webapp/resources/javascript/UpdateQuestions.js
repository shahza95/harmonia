// as soon as page loads, poll for new questions
$(document).ready(updateQuestions);
var urls = {};

function setGetQuestionsUrl(getUrl) {
    urls.getQuestionsUrl = getUrl;
}

function updateQuestions() {
	setInterval(function(){
			$.getJSON(urls.getQuestionsUrl, function(data) {
				$("#questions").load(location.href+" #questions>*","");
			});
	}, 5000);
}
