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
                let btn = "<button class='btnSeeMore' data-id=" + training.id + ">See More</button>";
                row += "<td>" + btn + "</td>";
                btn = "<button class='btnDelete' data-id=" + training.id + ">Delete</button>";
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