$(document).ready(function () {

    let trainerId = localStorage.getItem("id")
    let termId = localStorage.getItem("updateTerm_Trainer")

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/TermTrainerForm/" + trainerId + "/" + termId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            let replacedStart = response.start.replace(" ", "T");
            response.start = replacedStart;

            document.getElementById("PRICE").defaultValue=response.price;
            document.getElementById("START").defaultValue=response.start;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).ready(function () {

    let trainerId = localStorage.getItem("id")
    let termId = localStorage.getItem("updateTerm_Trainer")

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/TermTrainerForm3/" + trainerId + "/" + termId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            document.getElementById("HALL").defaultValue=response.mark;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).ready(function () {

    let trainerId = localStorage.getItem("id")
    let termId = localStorage.getItem("updateTerm_Trainer")

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/TermTrainerForm2/" + trainerId + "/" + termId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            document.getElementById("TRAINING").defaultValue=response.type;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});