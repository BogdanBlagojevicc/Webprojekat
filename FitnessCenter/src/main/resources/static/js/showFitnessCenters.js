$(document).ready(function () {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/fitnessCenters",
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let training of response) {
                let row = "<tr>";
                row += "<td>" + training.address + "</td>";
                row += "<td>" + training.email + "</td>";
                row += "<td>" + training.name + "</td>";
                row += "<td>" + training.phoneNumber + "</td>";
                let btn = "<button class='update' data-id=" + training.id + ">Update</button>";
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

$(document).on('click', '.update', function () {

    let adminId = localStorage.getItem("id");
    let fcId = this.dataset.id;
    localStorage.setItem("updateFcID", fcId)
    console.log("ID FITNESS CENTRA u nebitnoj metodi je:  ", localStorage.getItem("updateFcID"));

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/update/" + adminId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste admin moze update!");
            window.location.href = "updateFitnessCenter.html";
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});



