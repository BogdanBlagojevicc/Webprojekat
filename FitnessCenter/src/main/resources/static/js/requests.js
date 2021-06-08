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
                let btn = "<button class='btnAccept' data-id=" + requests.id + ">Accept</button>";
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

$(document).on("click", ".btnAccept", function () {

    let trainerId = this.dataset.id;
    let adminId = localStorage.getItem("id");
    console.log("Trener", trainerId);
    console.log("Admin", adminId);

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/users/" + trainerId + "/" + adminId,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS admin", response);

            alert("Uspesna aktivacija trenera!");
            window.location.href = "allTrainings.html";

        },
        error: function (response) {
            alert("Greška pri promeni aktivnosti!");
        }
    });
});

