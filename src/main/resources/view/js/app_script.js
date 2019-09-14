
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
                    color: '#FFF',
                    anchor: 'end',
                    align: 'start',
                    offset: -50,
                    borderWidth: 5,
                    borderColor: '#03a678',
                    borderRadius: 20,
                    backgroundColor: '#03a678',
                    formatter: function(value, context) {
                        if (context.active) {
                            return context.chart.data.labels[context.dataIndex] + " " + value + ' zł';
                        }
                        return value + ' zł';
                    },
                    opacity: 0.7,
                    padding: {
                        top: 4,
                        bottom: 4,
                        right: 10,
                        left: 10,
                    },
                    font: {
                        weight: 'bold',
                    },
                },
            },
            tooltips: {
                callbacks: {
                  label: function(tooltipItem, chartData) {
                    return chartData.labels[tooltipItem.index].toUpperCase();
                  }
                }
              }
        },
    
    });
}

