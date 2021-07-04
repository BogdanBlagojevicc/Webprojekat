$(document).ready(function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/allApplicationsForTerms/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                let btn = "<button class='decline_apply' data-id=" + term.id + ">Decline</button>";
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

$(document).on('click', '.decline_apply', function (event) {
    event.preventDefault();

    let userId = localStorage.getItem("id");
    let applyId = this.dataset.id;
    console.log("id apply brisanja je", applyId);

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/applys/delete/" + userId + "/" + applyId,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste user moze decline!");

            window.location.href = "allTerms.html";
        },
        error: function (response) {
            alert("Greska!");
        }
    });
});