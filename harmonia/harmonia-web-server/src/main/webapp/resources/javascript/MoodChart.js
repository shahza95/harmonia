$(document).ready(renderChart);

var urls = {};
var confused = 0;
var anxious = 0;
var frustrated = 0;
var curious = 0;
var contempt = 0;
var eureka = 0;

function setGetUrl(getUrl) {
    urls.getUrl = getUrl;
}

function setMoods(map) {
	confused = map.CONFUSED;
	anxious = map.ANXIOUS;
	frustrated = map.FRUSTRATED;
	curious = map.CURIOUS;
	contempt = map.CONTEMPT;
	eureka = map.EUREKA;
}

function renderChart() {
  	var data = {
    	labels: [
	        'Confused \uD83D\uDE15',
	        'Anxious \uD83D\uDE30',
	        'Frustrated \uD83D\uDE23',
	        'Curious \uD83E\uDD14',
	        'Contempt \uD83D\uDE42',
	        'Eureka \uD83E\uDD29'
	    ],
	    datasets: [{
	        data: [confused, anxious, frustrated, curious, contempt, eureka],
          	backgroundColor: ['rgb(153, 102, 255)', 'rgb(0, 102, 255)', 'rgb(255, 51, 0)', 'rgb(255, 153, 0)', 'rgb(51, 204, 51)', 'rgb(255, 255, 0)'],
          	hoverBorderColor: ['rgb(0,0,0)', 'rgb(0,0,0)', 'rgb(0,0,0)']
	    }]
	};

	var options = {
		animation: {
			animateRotate: false
		}
	}

	var context = document.getElementById('moodPieChart').getContext('2d');
	var myPieChart = new Chart(context,{
	    type: 'pie',
	    data: data,
	    options: options
	});
	
	updateChart(myPieChart);
}

function updateChart(myPieChart) {
	setInterval(function(){
			$.getJSON(urls.getUrl, function(data) {
				setMoods(data);
				myPieChart.data.datasets[0].data = [confused, anxious, frustrated, curious, contempt, eureka];
				myPieChart.update();
			});
	}, 5000);
}