$(document).ready(function () {

    let userId = localStorage.getItem("id")

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/profile/" + userId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            document.getElementById("firstName").defaultValue=response.firstName;
            document.getElementById("lastName").defaultValue=response.lastName;
            document.getElementById("email").defaultValue=response.email;
            document.getElementById("password").defaultValue=response.password;
            document.getElementById("phoneNumber").defaultValue=response.phoneNumber;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});