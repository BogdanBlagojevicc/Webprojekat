$(document).ready(function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '#btnSortByPriceAsc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortPriceAsc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Sort Asc", response);
            $('#term').find('tbody').children( 'tr:not(:first)' ).remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "</tr>";

                 $('#term').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '#btnSortByPriceDesc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortPriceDesc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Sort Desc", response);
            $('#term').find('tbody').children( 'tr:not(:first)' ).remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "</tr>";

                $('#term').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '#btnSortByDateAsc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortDateAsc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Date Asc", response);
            $('#term').find('tbody').children( 'tr:not(:first)' ).remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '#btnSortByDateDesc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortDateDesc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Date Desc", response);
            $('#term').find('tbody').children( 'tr:not(:first)' ).remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on("submit", "#training_price", function (event) {
    event.preventDefault();

    let price = $("#price").val();
    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/price/" + price + "/" +  userid,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS price", response);
            $('#term').find('tbody').children( 'tr:not(:first)' ).remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";

                row += "</tr>";

                $('#term').append(row);
            }

            alert("Uspesna pretraga po price!");

        },
        error: function () {
            alert("Greška prilikom pregrage po price!");
        }
    });
});

$(document).on("submit", "#training_date", function (event) {
    event.preventDefault();

    let start = $("#start").val();
    let userid = localStorage.getItem("id");
    console.log("start", start);
    console.log("userId", userid);

    let newData = {
        start
    }

    console.log("json ", JSON.stringify(newData));

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/date?date=" + start + "&id="  + userid,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS date", response);
            $('#term').find('tbody').children( 'tr:not(:first)' ).remove();

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";

                row += "</tr>";

                $('#term').append(row);
            }

            alert("Uspesna pretraga po date!");

        },
        error: function (response) {
            alert("Greška prilikom pregrage po date!");
        }
    });
});