$(document).ready(function () {

    let fcId = localStorage.getItem("seeMore");
    console.log(fcId);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/halls/" + fcId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let hall of response) {
                let row = "<tr>";
                row += "<td>" + hall.capacity + "</td>";
                row += "<td>" + hall.mark + "</td>";
                let btn = "<button class='update_hall' data-id=" + hall.id + ">Update</button>";
                row += "<td>" + btn + "</td>";
                btn = "<button class='delete_hall' data-id=" + hall.id + ">Delete</button>";
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

//izmena sale
$(document).on('click', '.update_hall', function () {

    let adminId = localStorage.getItem("id");
    let hallId = this.dataset.id;
    localStorage.setItem("updateHallId", hallId);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/update/" + adminId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste admin moze update!");
            window.location.href = "updateHall.html";
        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});

//brisanje sale
$(document).on('click', '.delete_hall', function (event) {
    event.preventDefault();

    let adminId = localStorage.getItem("id");
    let hallId = this.dataset.id;
    console.log("id hall brisanja je", hallId);

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/halls/delete/" + adminId + "/" + hallId,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste admin moze delete!");

            window.location.href = "showFitnessCenters.html";
        },
        error: function (response) {
            alert("Greska!");
        }
    });
});

