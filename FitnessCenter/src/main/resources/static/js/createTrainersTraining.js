$(document).on("submit", "#createTrainingTrainer", function (event) {
    event.preventDefault();

    let description = $("#DESCRIPTION").val();
    let duration = $("#DURATION").val();
    let name = $("#NAME").val();
    let type = $("#TYPE").val();


    let trainerId = localStorage.getItem("id");


    let newTraining = {
        description,
        duration,
        name,
        type
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/trainings/trainer/createTraining/" + trainerId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTraining),
        success: function (response) {
            console.log(response);

            alert("Training je uspešno create!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom create training!");
        }
    });
});
