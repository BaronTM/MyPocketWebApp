$("document").ready(function () {

    $("#log_but").click(function () {
        $(this).addClass("dissapear_button");
        $("#reg_but").css("display", "block");
        $("#reg_but").addClass("show_button");

        setTimeout(function () {
            $("#log_but").css("display", "none");
            $("#log_but").removeClass("dissapear_button");
            $("#reg_but").css("display", "block");
            $("#reg_but").removeClass("show_button");
        }, 700);

        $("#log_form").show(500);
        $("#register_form").hide(500);
    });

    $("#reg_but").click(function () {
        $(this).addClass("dissapear_button");
        $("#log_but").css("display", "block");
        $("#log_but").addClass("show_button");

        setTimeout(function () {
            $("#reg_but").css("display", "none");
            $("#reg_but").removeClass("dissapear_button");
            $("#log_but").css("display", "block");
            $("#log_but").removeClass("show_button");
        }, 700);


        $("#register_form").show(500);
        $("#log_form").hide(500);
    });




});