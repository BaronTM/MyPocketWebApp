
$("document").ready(function () {

    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);


    $(window).resize(function(){
        drawChart();
    });
});


function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Task', 'Hours per Day'],
        ['Work',     11],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Eat',      2],
        ['Commute',  2],
        ['Watch TV', 2],
        ['Cos', 1],
        ['Cos innego', 1.5],
        ['Sleep',    7]
    ]);

    var options = {
        is3D: true,
        backgroundColor: { fill:'transparent' },
        legend: {
            position: 'labeled',
            textStyle: {
                fontSize: 15,
                bold: true
            },
        },
        pieSliceText: 'percentage',
        // sliceVisibilityThreshold: 0.05,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
}