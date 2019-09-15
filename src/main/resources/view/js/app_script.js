
var ctx = null;

$("document").ready(function () {

    $("#log_out_but").click(function () {
        $("form[name='logout_form']").submit();
    });

    ctx = document.getElementById("chart_canvas").getContext('2d');

    // $.ajax({
    //     url: "/getwallet",
    //     success: function(response) {
    //         var jsonData = JSON.parse(response);
    //         updateChart(jsonData);
    //     }
    // });    

    var str = '[[5842.0,4301.0,933.08,2684.44],["#75d89e","#ea24a3","#c4d647","#fa06ec"],["samochod","dom","kino","jedzenie"]]';
    var jsonData = JSON.parse(str);
    updateChart(jsonData);

    $("#chart_canvas").width($("#piechart_container").height() * 2);
    $("#chart_canvas").height($("#piechart_container").height() * 2);

    $(window).resize(function() {
        $("#chart_canvas").width($("#piechart_container").height() * 2);
        $("#chart_canvas").height($("#piechart_container").height() * 2);
    });



});

function updateChart(dataArray) {
    var chart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            datasets: [
                {
                    data: dataArray[0],
                    backgroundColor: dataArray[1],
                }
            ],
            labels: dataArray[2],
        },
        options: {
            responsive: true,
            responsiveAnimationDuration: 1000,
            maintainAspectRatio: true,
            // aspectRatio: 1,
            legend: {
                position: 'right',

            },
            layout: {
                padding: {
                    left: 100,
                    right: 100,
                    top: 30,
                    bottom: 30
                }
            },
            plugins: {
                datalabels: {
                    color: '#000',
                    anchor: 'end',
                    align: 'start',
                    offset: -30,
                    borderWidth: 5,
                    borderColor: function(context) {
                        return context.dataset.backgroundColor;
                    },
                    borderRadius: 20,
                    backgroundColor: '#FFF',
                    formatter: function(value, context) {
                        var dataset = context.chart.data.datasets[0];
                        var percent = Math.round((value / dataset["_meta"][0]['total']) * 100)
                        return percent + ' %';
                    },
                    opacity: 1,
                    padding: {
                        top: 8,
                        bottom: 8,
                        right: 20,
                        left: 20,
                    },
                    font: {
                        weight: 'bold',
                    },
                },
            },
            tooltips: {
                callbacks: {
                    beforeLabel: function(tooltipItem, chartData) {
                        return chartData.labels[tooltipItem.index].toUpperCase();
                    },
                    label: function(tooltipItem, chartData) {
                        return chartData.labels[tooltipItem.index].toUpperCase();
                    },
                    afterLabel: function(tooltipItem, chartData) {
                        return "after";
                    },
                }
              },
        },
    
    });
}

