$(document).on("submit", "#addFitnessCenter", function (event) {
    event.preventDefault();

    let name = $("#NAME").val();
    let address = $("#ADDRESS").val();
    let phoneNumber = $("#PHONE_NUMBER").val();
    let email = $("#EMAIL").val();
    let adminId = localStorage.getItem("id");


    let newFitnessCenter = {
        name,
        address,
        phoneNumber,
        email
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/fitnessCenters/" + adminId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newFitnessCenter),
        success: function (response) {
            console.log(response);

            alert("Fitnes centar je uspešno kreiran!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom dodavanja fitnes centra!");
        }
    });
});