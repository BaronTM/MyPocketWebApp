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

        $("#log_form_div").show(500);
        $("#register_form_div").hide(500);

        $("#registration_login_input").val("");
        $("#registration_password_input").val("");
        $("#registration_password_confirmation_input").val("");

        validPasswords();

        $("#information").html("");
        $("#information").hide();
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


        $("#register_form_div").show(500);
        $("#log_form_div").hide(500);

        $("#information").html("");
        $("#information").hide();
    });

    $("#logging_button").click(function () {
        $("#log_form").submit();
    });

    $("#log_out_but").click(function () {
        $("form[name='logout_form']").submit();
    });

    $("#registration_button").click(function () {
        var conf = $("#registration_password_confirmation_input").val();
        var pass = $("#registration_password_input").val();
        var log = $("#registration_login_input").val();
        if (conf.length >= 8 && conf == pass && log.length >= 4) {
            $("#registration_form").submit();
        }
    });

    $("#registration_login_input").focusout(function () {
        if ($("#registration_login_input").val().length >= 4) {
            $.ajax({
                url: "/isusernameenabled?username=" + $("#registration_login_input").val(),
                success: function (response) {
                    if (response == "exists") {
                        $("#information").text("Użytkownik o takim loginie już istnieje.");
                        $("#information").show();
                        $("#registration_login_input").css("background-color", "rgba(255, 30, 30, 0.1)");
                    } else {
                        $("#information").text("");
                        $("#information").hide();
                        $("#registration_login_input").css("background-color", "rgba(30, 255, 30, 0.1)");
                    }
                }
            });
        } else if ($("#registration_login_input").val().length < 4 && $("#registration_login_input").val().length > 0){
            $("#information").html("Login musi zawierać <br/>co najmniej 4 znaki.");
            $("#information").show();
            $("#registration_login_input").css("background-color", "rgba(255, 30, 30, 0.1)");
        } else {
            $("#information").html("");
            $("#information").hide();
            $("#registration_login_input").css("background-color", "white");
        }
    });

    $("#registration_password_input").focusout(validPasswords);

    $("#registration_password_confirmation_input").focusout(validPasswords);

});

function validPasswords() {
    var pass = $("#registration_password_input").val();
    var conf = $("#registration_password_confirmation_input").val();

    if (pass.length < 8 && pass.length > 0) {
        $("#information").html("Hasło musi zawierać <br/>co najmniej 8 znaków.");
        $("#information").show();
        $("#registration_password_input").css("background-color", "rgba(255, 30, 30, 0.1)");
    } else if (conf.length > 0 && pass != conf ) {
        $("#information").text("Hasła się nie pokrywają.");
        $("#information").show();
        $("#registration_password_confirmation_input").css("background-color", "rgba(255, 30, 30, 0.1)");
    } else if ($("#registration_login_input").val().length < 4 && $("#registration_login_input").val().length > 0){
        $("#information").html("Login musi zawierać <br/>co najmniej 4 znaki.");
        $("#information").show();
        $("#registration_login_input").css("background-color", "rgba(255, 30, 30, 0.1)");
    } else if (conf.length >= 8 && pass == conf){
        $("#information").text("");
        $("#information").hide();
        $("#registration_password_confirmation_input").css("background-color", "rgba(30, 255, 30, 0.1)");
        $("#registration_password_input").css("background-color", "rgba(30, 255, 30, 0.1)");
    } else if (pass.length > 8) {
        $("#information").text("");
        $("#information").hide();
        $("#registration_password_input").css("background-color", "rgba(30, 255, 30, 0.1)");
    } else {
        $("#information").text("");
        $("#information").hide();
        $("#registration_password_confirmation_input").css("background-color", "white");
        $("#registration_password_input").css("background-color", "white");
        $("#registration_login_input").css("background-color", "white");
    }
}