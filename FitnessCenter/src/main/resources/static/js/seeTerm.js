$(document).ready(function () {

    let termId = localStorage.getItem("seeMoreterm");
    console.log(termId);
    let userId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/" + termId + "/" + userId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.typeDTO.duration + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                let btn = "<button class='confirm_term' data-id=" + term.id + ">Confirm</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";

                $('#training').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});