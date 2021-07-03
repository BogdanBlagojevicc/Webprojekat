$(document).ready(function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/allApplicationsForTerms/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                let btn = "<button class='decline_term' data-id=" + term.id + ">Decline</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});