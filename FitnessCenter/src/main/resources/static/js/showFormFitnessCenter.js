$(document).ready(function () {

    let fcId = localStorage.getItem("updateFcID")

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnessCenters/" + fcId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            document.getElementById("NAME").defaultValue=response.name;
            document.getElementById("ADDRESS").defaultValue=response.address;
            document.getElementById("PHONE_NUMBER").defaultValue=response.phoneNumber;
            document.getElementById("EMAIL").defaultValue=response.email;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});