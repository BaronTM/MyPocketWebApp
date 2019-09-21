
var ctx = null;
var expensesData;
var revenuesData;
var chart;

$("document").ready(function () {

    $("#form_container").hide();

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


    addValidatorToMoneyValue("#new_expense_form_value");
    addActionsToFormElements("#new_expense_categoty");
    addActionsToFormElements("#new_expense_account");
    
    $("#add_new_expense_button").click(function() {
        $("#new_expense_form").submit();
        $("#form_container").hide(200);
    });

    $("#new_expense_form_container img").click(function() {
        $("#form_container").hide(200);
    });


    // account changing action

    $("#account_button").click(function() {
        var width = $(this).css("width");
        $("#user_accounts_list_container").css("width", width);
        $("#user_accounts_list_container").show(200);
    });

    $("#user_accounts_list_container").mouseleave(function() {
        $(this).hide(200);
    });

    $("#user_accounts_list_container li").click(function() {
        let name = $(this).text();
        $("#account_button").text(name);
        $("#user_accounts_list_container").hide(200);
    });
});


function showNewExpenseForm(labelsArr, selectedLab) {
    $("#new_expense_category_selected").text(selectedLab);
    let optionListHtml = "";
    for (var v in labelsArr) {
        optionListHtml += "<li>" + labelsArr[v]  +"</li>";
    }
    $("#new_expense_categoty .option_list").html(optionListHtml);
    addActionsToFormElements("#new_expense_categoty");
    $("#form_container").show(200);
};

function addValidatorToMoneyValue(selector) {
    let input = $(selector);

    input.keydown(function(e){
        let before = input.val();
        let after = before + e.key;
        let regex = new RegExp("^([0-9]+(\\.||,||((\\.[0-9]{1,2})||(,[0-9]{1,2})))?)?$");
        // alert(e.keyCode);
        if (e.keyCode < 48) {
        } else if (regex.test(after)) {
        } else {
            e.preventDefault();
        }
    });


    input.keyup(function(e){
        let after = input.val();
        let regex = new RegExp("^([0-9]+(.||,||((.[0-9]{1,2})||(,[0-9]{1,2})))?)?$");
        if (regex.test(after) || after==null) {
        } else {
        }
    });
};

function addActionsToFormElements(selector) {
    let mainDiv = $(selector);
    let selectedItem = $(selector + " .selected_item");
    let optionList = $(selector + " .option_list");
    let listItems = $(selector + " .option_list li");
    selectedItem.click(function() {
        var width = $(this).css("width");
        optionList.css("width", width);
        $(this).css({
            "border-bottom-left-radius": 0,
            "border-bottom-right-radius": 0,
            "background-color": "rgb(2, 114, 226)",
        });
        optionList.show();
    });
    
    optionList.mouseleave(function() {
        selectedItem.css({
            "border-bottom-left-radius": "10px",
            "border-bottom-right-radius": "10px",
            "background-color": "dodgerblue",
        });
        optionList.hide();
    });

    listItems.click(function() {
        let value = $(this).text();
        selectedItem.text(value);
        selectedItem.css({
            "border-bottom-left-radius": "10px",
            "border-bottom-right-radius": "10px",
            "background-color": "dodgerblue",
        });
        optionList.hide();
    });
};

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
                    showNewExpenseForm(activePoints[0]._chart.data.labels, label);
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


};

