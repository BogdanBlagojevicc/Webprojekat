$(document).on("submit", "#registerTrainer", function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");

    let firstName = $("#firstName").val();
    let lastName = $("#lastName").val();
    let username = $("#username").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let birth = $("#birth").val();
    let phoneNumber = $("#phoneNumber").val();
    let active = true;
    let role = "Trainer";
    let averageGrade = 0;


    let newTrainer = {
        firstName,
        lastName,
        username,
        email,
        password,
        birth,
        phoneNumber,
        active,
        role,
        averageGrade
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/users/" + adminId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTrainer),
        success: function (response) {
            console.log(response);

            alert("Trener je uspešno kreiran!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom dodavanja zaposlenog!");
        }
    });
});