$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/trainersApplyForRegistration",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let requests of response) {
                let row = "<tr>";
                row += "<td>" + requests.firstName + "</td>";
                row += "<td>" + requests.lastName + "</td>";
                row += "<td>" + requests.username + "</td>";
                row += "<td>" + requests.email + "</td>";
                row += "<td>" + requests.password + "</td>";
                row += "<td>" + requests.birth + "</td>";
                row += "<td>" + requests.phoneNumber + "</td>";
                let btn = "<button class='btnSeeMore' data-id=" + requests.id + ">Accept</button>";
                row += "<td>" + btn + "</td>";
                btn = "<button class='btnDelete' data-id=" + requests.id + ">Decline</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";

                $('#requests').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

