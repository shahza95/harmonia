$(document).ready(renderChart);

var happy = 0;
var sad = 0;
var confused = 0;


function setMoodDtoList(list) {
	console.log(list.moodDtoList[0].emoji);
	
}

function renderChart() {
  	var data = {
    	labels: [
	        'Happy \uD83D\uDE42',
	        'Sad \uD83D\uDE41',
	        'Confused \uD83D\uDE15'
	    ],
	    datasets: [{
	        data: [10, 20, 30],
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
}