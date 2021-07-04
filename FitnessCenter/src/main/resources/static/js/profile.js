$(document).ready(function () {

    let userId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/profile/" + userId,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            let row = "<tr>";
            row += "<td>" + response.birth + "</td>";
            row += "<td>" + response.email + "</td>";
            row += "<td>" + response.firstName + "</td>";
            row += "<td>" + response.lastName + "</td>";
            row += "<td>" + response.password + "</td>";
            row += "<td>" + response.phoneNumber + "</td>";
            row += "<td>" + response.username + "</td>";
            let btn = "<button class='change_data' data-id=" + response.id + ">Change</button>";
            row += "<td>" + btn + "</td>";
            row += "</tr>";

            $('#term').append(row);

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '.change_data', function () {

    let userId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isUser/" + userId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste user moze promena!");
            window.location.href = "changeProfile.html";
        },
        error: function (response) {
            alert("Vi niste user ne moze promena!");
            console.log("ERROR:\n", response);
        }
    });
});