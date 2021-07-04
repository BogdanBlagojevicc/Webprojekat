$(document).on("submit", "#changeUser", function (event) {
    event.preventDefault();

    let firstName = $("#firstName").val();
    let lastName = $("#lastName").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let phoneNumber = $("#phoneNumber").val();
    let userId = localStorage.getItem("id");


    let changeUser = {
        firstName,
        lastName,
        email,
        password,
        phoneNumber
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/users/change/" + userId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(changeUser),
        success: function (response) {
            console.log(response);

            alert("Uspesna promena!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom promene podataka!");
        }
    });
});