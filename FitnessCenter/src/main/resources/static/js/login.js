$(document).on("submit", "#oneUser", function (event) {
    event.preventDefault();

    let username = $("#USERNAME").val();
    let password = $("#PASSWORD").val();

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/" + username + "/" + password,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS login", response);

            localStorage.setItem("id", response);
            console.log(localStorage.getItem("id"));

            alert("Uspesno logovanje!");
            window.location.href = "allTrainings.html";

        },
        error: function () {
            alert("Greška prilikom logovanja!");
        }
    });
});