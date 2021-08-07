/* globals Chart:false, feather:false */
"use strict"

var currentChart = '';

const drawChart = (labels, data) => {
    if(currentChart instanceof Chart) {
        currentChart.destroy();
    }
    // Graphs
    let ctx = document.getElementById('myChart')
    // eslint-disable-next-line no-unused-vars
    currentChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: labels,
      datasets: [{
        data: data,
//        lineTension: 0,
//        backgroundColor: 'transparent',
//        borderColor: '#007bff',
//        borderWidth: 4,
//        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
    })
}

const drawTable = (labels, data) => {
    let contents = labels.map( (elem, index) =>
        `<tr><td>${elem}</td><td>${data[index]}</td></tr>`);
    $('#data-table tbody').html(contents);
}

const loadFredData = async () => {
    let url = new URL('/fred/query', document.baseURI);
    let params = {from:$('#fromDate').val(), to:$('#toDate').val()};
    url.search = new URLSearchParams(params).toString();

    let response = await fetch(url);
    let rawdata = await response.json();
    let filteredData = rawdata.filter(x => x.value != -999);
    let labels = filteredData.map(x => x.date)
    let data = filteredData.map(x => x.value)
    drawChart(labels,data);
    drawTable(labels,data);
};
