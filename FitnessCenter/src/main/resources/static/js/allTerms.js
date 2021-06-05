$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms",
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

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortPriceAsc",
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

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortPriceDesc",
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

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortDateAsc",
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

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortDateDesc",
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