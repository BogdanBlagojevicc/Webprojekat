$(document).on("submit", "#updateTermTrainer", function (event) {
    event.preventDefault();

    let price = $("#PRICE").val();
    let start = $("#START").val();

    let trainerId = localStorage.getItem("id");
    let termId = localStorage.getItem("trainerUpdateTerm")

    let replacedStart = start.replace("T", " ");
    start = replacedStart;

    let newTerm = {
        price,
        start
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/terms/trainer/" + trainerId + "/" + termId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTerm),
        success: function (response) {
            console.log(response);

            alert("Term je uspešno update!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom update term!");
        }
    });
});