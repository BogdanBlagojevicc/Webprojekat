$(document).on("submit", "#updateFitnessCenter", function (event) {
    event.preventDefault();

    let name = $("#NAME").val();
    let address = $("#ADDRESS").val();
    let phoneNumber = $("#PHONE_NUMBER").val();
    let email = $("#EMAIL").val();

    let adminId = localStorage.getItem("id");
    let fitnessCenterId = localStorage.getItem("updateFcID")

    console.log("ID FITNESS CENTRA JE:  ", fitnessCenterId);

    let newFitnessCenter = {
        name,
        address,
        phoneNumber,
        email
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/fitnessCenters/" + adminId + "/" + fitnessCenterId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newFitnessCenter),
        success: function (response) {
            console.log(response);

            alert("Fitnes centar je uspešno update!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom update fitnes centra!");
        }
    });
});