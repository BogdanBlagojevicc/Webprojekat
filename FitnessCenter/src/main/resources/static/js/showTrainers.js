$(document).ready(function () {

    let adminId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/trainers/" + adminId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let trainer of response) {
                let row = "<tr>";
                row += "<td>" + trainer.active + "</td>";
                row += "<td>" + trainer.averageGrade + "</td>";
                row += "<td>" + trainer.birth + "</td>";
                row += "<td>" + trainer.email + "</td>";
                row += "<td>" + trainer.firstName + "</td>";
                row += "<td>" + trainer.lastName + "</td>";
                row += "<td>" + trainer.password + "</td>";
                row += "<td>" + trainer.phoneNumber + "</td>";
                row += "<td>" + trainer.role + "</td>";
                row += "<td>" + trainer.username + "</td>";
                let btn = "<button class='deleteTrainer' data-id=" + trainer.id + ">Delete</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";

                $('#training').append(row);
            }
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

//brisanje trenera
$(document).on('click', '.deleteTrainer', function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");
    let trainerId = this.dataset.id;
    console.log("id trener brisanja je", trainerId);

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/users/delete/" + adminId + "/" + trainerId,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste admin moze delete!");

            window.location.href = "allTrainings.html";
        },
        error: function (response) {
            alert("Greska!");
        }
    });
});


