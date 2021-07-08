$(document).ready(function () {

    let trainerId = localStorage.getItem("id")
    let trainingId = localStorage.getItem("update_training");


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/TrainingTrainerForm/" + trainerId + "/" + trainingId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            document.getElementById("DESCRIPTION").defaultValue=response.description;
            document.getElementById("DURATION").defaultValue=response.duration;
            document.getElementById("NAME").defaultValue=response.name;
            document.getElementById("TYPE").defaultValue=response.type;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});