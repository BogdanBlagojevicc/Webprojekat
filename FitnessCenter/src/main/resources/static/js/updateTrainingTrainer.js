$(document).on("submit", "#updateTrainingTrainer", function (event) {
    event.preventDefault();

    let description = $("#DESCRIPTION").val();
    let duration = $("#DURATION").val();
    let name = $("#NAME").val();
    let type = $("#TYPE").val();

    let trainerId = localStorage.getItem("id");
    let trainingId = localStorage.getItem("update_training");


    let newTraining = {
        description,
        duration,
        name,
        type
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/trainings/trainer/updateTraining/" + trainerId + "/" + trainingId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newTraining),
        success: function (response) {
            console.log(response);

            alert("Training je uspešno update!");
            window.location.href = "allTrainings.html";
        },
        error: function () {
            alert("Greška prilikom update training!");
        }
    });
});
