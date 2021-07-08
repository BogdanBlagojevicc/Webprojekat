$(document).on("submit", "#createTermTrainer", function (event) {
    event.preventDefault();

    let price = $("#PRICE").val();
    let start = $("#START").val();

    let trainerId = localStorage.getItem("id");

    let replacedStart = start.replace("T", " ");
    start = replacedStart;

    let newTerm = {
        price,
        start
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/terms/trainer/createTerm/" + trainerId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTerm),
        success: function (response) {
            console.log(response);

            alert("Term je uspešno create!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom create term!");
        }
    });
});