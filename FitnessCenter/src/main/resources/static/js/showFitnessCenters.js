$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnessCenters",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let training of response) {
                let row = "<tr>";
                row += "<td>" + training.address + "</td>";
                row += "<td>" + training.email + "</td>";
                row += "<td>" + training.name + "</td>";
                row += "<td>" + training.phoneNumber + "</td>";
                row += "</tr>";

                $('#training').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});