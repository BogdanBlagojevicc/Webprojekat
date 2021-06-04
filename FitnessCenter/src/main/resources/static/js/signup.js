$(document).on("submit", "#addUser", function (event) {
    event.preventDefault();

    let firstName = $("#firstName").val();
    let lastName = $("#lastName").val();
    let username = $("#username").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let birth = $("#birth").val();
    let phoneNumber = $("#phoneNumber").val();
    let active = true;
    let role = "User";
    let averageGrade = 0;


    let newUser = {
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
        url: "http://localhost:8080/api/users",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newUser),
        success: function (response) {
            console.log(response);

            alert("Radnik " + response.id + " je uspešno kreiran!");
            window.location.href = "index.html";
        },
        error: function () {
            alert("Greška prilikom dodavanja zaposlenog!");
        }
    });
});