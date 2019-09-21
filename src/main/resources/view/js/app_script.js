
var ctx = null;
var expensesData;
var revenuesData;
var chart;
var validatorValue;

$("document").ready(function () {

    $("#log_out_but").click(function () {
        $("form[name='logout_form']").submit();
    });

    ctx = document.getElementById("chart_canvas").getContext('2d');

    // $.ajax({
    //     url: "/getrevenues",
    //     success: function(response) {
    //         revenuesData = JSON.parse(response);
    //     }
    // }); 

    // $.ajax({
    //     url: "/getexpences",
    //     success: function(response) {
    //         expensesData = JSON.parse(response);
    //         updateChart(expensesData);
    //     }
    // });          

    var str = '[[5842.0,4301.0,933.08,2684.44],["#75d89e","#ea24a3","#c4d647","#fa06ec"],["samochod","dom","kino","jedzenie"]]';
    expensesData = JSON.parse(str);
    updateChart(expensesData);

    var str2 = '[[30000],["#75d89e"],["pko"]]';
    revenuesData = JSON.parse(str2);

    $("#chart_canvas").width($("#piechart_container").height() * 2);
    $("#chart_canvas").height($("#piechart_container").height() * 2);

    $(window).resize(function() {
        $("#chart_canvas").width($("#piechart_container").height() * 2);
        $("#chart_canvas").height($("#piechart_container").height() * 2);
    });

    $("#new_expense_form_value").keydown(function(e){
        let before = $("#new_expense_form_value").val();
        let after = before + e.key;
        let regex = new RegExp("^([0-9]+(\\.||,||((\\.[0-9]{1,2})||(,[0-9]{1,2})))?)?$");
        // alert(e.keyCode);
        if (e.keyCode < 48) {
        } else if (regex.test(after)) {
        } else {
            e.preventDefault();
        }
    });


    $("#new_expense_form_value").keyup(function(e){
        let after = $("#new_expense_form_value").val();
        let regex = new RegExp("^([0-9]+(.||,||((.[0-9]{1,2})||(,[0-9]{1,2})))?)?$");
        if (regex.test(after) || after==null) {
            // $("#new_expense_form_value").css("background-color", "limegreen");
        } else {
            // $("#new_expense_form_value").css("background-color", "orangered");
        }
    });

    $("#new_expense_category_selected").click(function() {
        var width = $(this).css("width");
        $("#new_expense_category_list").css("width", width);
        $(this).css({
            "border-bottom-left-radius": 0,
            "border-bottom-right-radius": 0,
            "background-color": "rgb(2, 114, 226)",
        });
        $("#new_expense_category_list").show();
    });
    
    $("#new_expense_category_list").mouseleave(function() {
        $("#new_expense_category_selected").css({
            "border-bottom-left-radius": "10px",
            "border-bottom-right-radius": "10px",
            "background-color": "dodgerblue",
        });
        $("#new_expense_category_list").hide();
    });

    $("#new_expense_category_list li").click(function() {
        let value = $(this).text();
        $("#new_expense_category_selected").text(value);
        $("#new_expense_category_selected").css({
            "border-bottom-left-radius": "10px",
            "border-bottom-right-radius": "10px",
            "background-color": "dodgerblue",
        });
        $("#new_expense_category_list").hide();
    });


    



});

function addActionsToFormElements() {
    $("#new_expense_category_selected").click(function() {
        var width = $(this).css("width");
        $("#new_expense_category_list").css("width", width);
        $(this).css({
            "border-bottom-left-radius": 0,
            "border-bottom-right-radius": 0,
            "background-color": "rgb(2, 114, 226)",
        });
        $("#new_expense_category_list").show();
    });
    
    $("#new_expense_category_list").mouseleave(function() {
        $("#new_expense_category_selected").css({
            "border-bottom-left-radius": "10px",
            "border-bottom-right-radius": "10px",
            "background-color": "dodgerblue",
        });
        $("#new_expense_category_list").hide();
    });

    $("#new_expense_category_list li").click(function() {
        let value = $(this).text();
        $("#new_expense_category_selected").text(value);
        $("#new_expense_category_selected").css({
            "border-bottom-left-radius": "10px",
            "border-bottom-right-radius": "10px",
            "background-color": "dodgerblue",
        });
        $("#new_expense_category_list").hide();
    });
}

function updateChart(dataArray) {

    var defaultLegendClickCallback = Chart.defaults.global.legend.onClick;

    chart = new Chart(ctx, {
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
        config: {
            cutoutPercentage: 1,
            circumference: 3,
        },
        options: {
            responsive: true,
            responsiveAnimationDuration: 100,
            maintainAspectRatio: true,
            // aspectRatio: 1,
            legend: {
                position: 'right',
                align: 'end',
                labels: {
                    boxWidth: 30,
                    fontSize: 14,
                    padding: 20,
                    fontStyle: 'italic',
                    usePointStyle : true,
                },
                onHover: function(event, legendItem) {
                    document.getElementById("chart_canvas").style.cursor = 'pointer';
                },
                onLeave: function(event, legendItem) {
                    document.getElementById("chart_canvas").style.cursor = '';
                },
            },
            hover: {
                onHover: function(e, el) {
                  $("#chart_canvas").css("cursor", el[0] ? "pointer" : "default");
                }
            },
            onClick: (evt, item) => {
                let activePoints = chart.getElementsAtEvent(evt);
                if (activePoints.length > 0) {
                    let index = activePoints[0]._index;
                    let label = activePoints[0]._chart.data.labels[index];
                    let value = activePoints[0]._chart.data.datasets[0].data[index];
                    
                    // place for adding new expecse form 

                    // alert(label + ":  " + value);
                    // alert(Object.keys(activePoints[0]));
                }
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
                doughnutlabel: {
                    labels: [
                      {
                        text: function(chart) {
                            let sum = revenuesData[0].reduce((a, b) => a + b, 0);
                            let res = Math.round((sum) * 100) / 100;
                            return res + " zł";
                        },
                        font: {
                          size: '50'
                        },
                        color: "#00BB14",
                      },
                      {
                        text: function(chart) {
                            let dataset = chart.data.datasets[0];
                            let sum = dataset["_meta"][0]['total'];
                            let res = Math.round((sum) * 100) / 100;
                            return res + " zł";
                        },
                        font: {
                          size: '50'
                        },
                        color: '#BB1414'
                      },
                      {
                        text: function(chart) {
                            let dataset = chart.data.datasets[0];
                            let sumExpences = dataset["_meta"][0]['total'];
                            let sumRevenues = revenuesData[0].reduce((a, b) => a + b, 0);
                            let res = Math.round((sumRevenues - sumExpences) * 100) / 100;
                            return res + " zł";
                        },
                        font: {
                          size: '60'
                        },
                        color: '#1414BB'
                      }
                    ]
                },
            },
            tooltips: {
                callbacks: {
                    title: function(tooltipItems, chartData) {
                        let str = chartData.labels[tooltipItems[0].index].toUpperCase();
                        return str;
                    },
                    label: function(tooltipItem, chartData) {
                        return chartData.datasets[0].data[tooltipItem.index] + " zł";
                    },
                    footer: function(tooltipItems, chartData) {
                        let dataset = chartData.datasets[0];
                        let value = dataset.data[tooltipItems[0].index];
                        let percent = Math.round((value / dataset["_meta"][0]['total']) * 100)
                        return "      (" + percent + ' %)';
                    }
                },
                titleFontSize: 20,
                titleFontColor: '#b7d8ed',
                titleMarginBottom: 10,
                bodyFontSize: 15,
                bodyFontStyle: 'italic',
                footerFontStyle: 'italic',
                caretSize: 10,
                displayColors: true,
                backgroundColor: 'rgba(0, 0, 0, 0.7)',
              },
        },
    });


}

