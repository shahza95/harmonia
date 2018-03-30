$(document).ready(renderChart);

var urls = {};
var happy = 0;
var sad = 0;
var confused = 0;

function setGetUrl(getUrl) {
    urls.getUrl = getUrl;
}

function setMoods(map) {
	happy = map.HAPPY;
	sad = map.SAD;
	confused = map.CONFUSED;
}

function renderChart() {
  	var data = {
    	labels: [
	        'Happy \uD83D\uDE42',
	        'Sad \uD83D\uDE41',
	        'Confused \uD83D\uDE15'
	    ],
	    datasets: [{
	        data: [happy, sad, confused],
          	backgroundColor: ['rgb(200, 120, 50)', 'rgb(0, 80, 150)', 'rgb(140, 100, 200)'],
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
				myPieChart.data.datasets[0].data = [happy, sad, confused];
				myPieChart.update();
			});
	}, 5000);
}