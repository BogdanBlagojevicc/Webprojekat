$(document).ready(function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/doneAndGradeTerms/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.duration + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});