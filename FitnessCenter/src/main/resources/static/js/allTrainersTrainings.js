$(document).ready(function () {


    console.log("USAO");
    let trainerid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/trainers/" + trainerid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.duration + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='update_trainers_term' data-id=" + term.id + ">Update</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '.update_trainers_term', function () {

    let trainerId = localStorage.getItem("id");
    let termId = this.dataset.id;
    localStorage.setItem("trainerUpdateTerm", termId);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isTrainer/" + trainerId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste trainer moze update!");
            window.location.href = "updateTermTrainer.html";
        },
        error: function (response) {
            alert("Vi niste trainer ne moze update!");
        }
    });
});