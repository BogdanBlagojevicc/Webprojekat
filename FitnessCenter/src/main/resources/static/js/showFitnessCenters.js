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
                btn = "<button class='delete' data-id=" + training.id + ">Delete</button>";
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

//izmena fitne centra
$(document).on('click', '.update', function () {

    let adminId = localStorage.getItem("id");
    let fcId = this.dataset.id;
    localStorage.setItem("updateFcID", fcId);

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

//brisanje Fitnes Centra
$(document).on('click', '.delete', function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");
    let fcId = this.dataset.id;
    console.log("id fc brisanja je", fcId);

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/fitnessCenters/delete/" + adminId + "/" + fcId,
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



