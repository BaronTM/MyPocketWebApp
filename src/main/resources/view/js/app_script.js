
$("document").ready(function () {

    $("#log_out_but").click(function () {
        $("form[name='logout_form']").submit();
    });

    var ctx = document.getElementById("chart_canvas").getContext('2d');
    var data;

    $.ajax({
        url: "/getwallet",
        success: function(response) {
            data = JSON.parse(response);
        }
    });
    alert("chyba sie udalo");


    var chart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            datasets: [
                {
                    data: [300.36, 100.72, 725.22, 550.11],
                    backgroundColor: colorsTable
                }
            ],
            labels: labels
        },
        options: {
            responsive: true,
            aspectRatio: 1,
            legend: {
                position: 'right',

            },
            plugins: {
                datalabels: {
                    color: '#FFF',
                    anchor: 'end',
                    align: 'start',
                    offset: -30,
                    borderWidth: 5,
                    borderColor: '#FFF',
                    borderRadius: 50,
                    backgroundColor: '#888888',
                    font: {
                        weight: 'bold',
                        size: '30'
                    }
                }
            }
        }
    });

   
    $("#piechart").width($("#piechart_container").height());
    $("#piechart").height($("#piechart_container").height());

    $(window).resize(function() {
        $("#piechart").width($("#piechart_container").height());
        $("#piechart").height($("#piechart_container").height());
    });



});

