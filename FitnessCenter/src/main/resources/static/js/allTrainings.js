$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trainings",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let training of response) {
                let row = "<tr>";
                row += "<td>" + training.description + "</td>";
                row += "<td>" + training.duration + "</td>";
                row += "<td>" + training.name + "</td>";
                row += "<td>" + training.type + "</td>";
                row += "<td>" + training.trainer.firstName + "</td>";
                row += "</tr>";

                $('#training').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on("submit", "#training_name", function (event) {
    event.preventDefault();

    let name = $("#name").val();
    let userId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trainings/name/" + name + "/" + userId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS naziv", response);
            $('#training').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.description + "</td>";
                row += "<td>" + term.duration + "</td>";
                row += "<td>" + term.name + "</td>";
                row += "<td>" + term.type + "</td>";
                row += "<td>" + term.trainer.firstName + "</td>";

                row += "</tr>";

                $('#training').append(row);
            }

            alert("Uspesna pretraga po nazivu!");

        },
        error: function () {
            alert("Greška prilikom pregrage po nazivu!");
        }
    });
});

$(document).on("submit", "#training_type", function (event) {
    event.preventDefault();

    let type = $("#type").val();
    let userId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trainings/type/" + type + "/" + userId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS type", response);
            $('#training').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.description + "</td>";
                row += "<td>" + term.duration + "</td>";
                row += "<td>" + term.name + "</td>";
                row += "<td>" + term.type + "</td>";
                row += "<td>" + term.trainer.firstName + "</td>";

                row += "</tr>";

                $('#training').append(row);
            }

            alert("Uspesna pretraga po type!");

        },
        error: function () {
            alert("Greška prilikom pretrage po type!");
        }
    });
});

$(document).on("submit", "#training_description", function (event) {
    event.preventDefault();

    let description = $("#description").val();
    let userId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/trainings/description/" + description + "/" + userId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS description", response);
            $('#training').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.description + "</td>";
                row += "<td>" + term.duration + "</td>";
                row += "<td>" + term.name + "</td>";
                row += "<td>" + term.type + "</td>";
                row += "<td>" + term.trainer.firstName + "</td>";

                row += "</tr>";

                $('#training').append(row);
            }

            alert("Uspesna pretraga po description!");

        },
        error: function () {
            alert("Greška prilikom pregrage po description!");
        }
    });
});

$(document).on("submit", "#for_everyone", function (event) {
    event.preventDefault();

    let userid = localStorage.getItem("id");
    console.log(userid);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/" + userid,
        // dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS user", response);

            // localStorage.setItem("id", response);
            // console.log(localStorage.getItem("id"));

            alert("Vi ste user moze!");
            window.location.href = "allTerms.html";

        },
        error: function () {
            alert("Greška niste user!");
        }
    });
});

$(document).on("submit", "#admin_only", function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/" + adminId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS admin", response);

            localStorage.setItem("id", response);
            console.log(localStorage.getItem("id"));

            alert("Vi ste admin moze!");
            window.location.href = "addFitnessCenter.html";

        },
        error: function () {
            alert("Greška niste admin!");
        }
    });
});

$(document).on("submit", "#admin_only2", function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/" + adminId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS admin", response);

            localStorage.setItem("id", response);
            console.log(localStorage.getItem("id"));

            alert("Vi ste admin moze!");
            window.location.href = "showFitnessCenters.html";

        },
        error: function () {
            alert("Greška niste admin!");
        }
    });
});

$(document).on("submit", "#admin_only3", function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/" + adminId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS admin", response);

            localStorage.setItem("id", response);
            console.log(localStorage.getItem("id"));

            alert("Vi ste admin moze!");
            window.location.href = "requests.html";

        },
        error: function () {
            alert("Greška niste admin!");
        }
    });
});

$(document).on("submit", "#admin_only4", function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/" + adminId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS admin", response);

            localStorage.setItem("id", response);
            console.log(localStorage.getItem("id"));

            alert("Vi ste admin moze!");
            window.location.href = "registerTrainer.html";

        },
        error: function () {
            alert("Greška niste admin!");
        }
    });
});

$(document).on("submit", "#admin_only5", function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/" + adminId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS admin", response);

            localStorage.setItem("id", response);
            console.log(localStorage.getItem("id"));

            alert("Vi ste admin moze!");
            window.location.href = "showTrainers.html";

        },
        error: function () {
            alert("Greška niste admin!");
        }
    });
});

$(document).on("submit", "#user_only", function (event) {
    event.preventDefault();

    let userid = localStorage.getItem("id");
    console.log(userid);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isUser/" + userid,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS user", response);

            alert("Vi ste user moze!");
            window.location.href = "profile.html";

        },
        error: function () {
            alert("Greška niste user!");
        }
    });
});

$(document).on("submit", "#user_only2", function (event) {
    event.preventDefault();

    let userid = localStorage.getItem("id");
    console.log(userid);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isUser/" + userid,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS user", response);

            alert("Vi ste user moze!");
            window.location.href = "allDoneTerms.html";

        },
        error: function () {
            alert("Greška niste user!");
        }
    });
});

$(document).on("submit", "#user_only3", function (event) {
    event.preventDefault();

    let userid = localStorage.getItem("id");
    console.log(userid);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isUser/" + userid,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS user", response);

            alert("Vi ste user moze!");
            window.location.href = "allDoneNotGrade.html";

        },
        error: function () {
            alert("Greška niste user!");
        }
    });
});


