$(document).ready(function () {

    let termId = localStorage.getItem("seeMoreterm");
    console.log(termId);
    let userId = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/" + termId + "/" + userId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr>";
                row += "<td>" + term.number_of_applications + "</td>";
                row += "<td>" + term.markDTO.mark + "</td>";
                row += "<td>" + term.typeDTO.duration + "</td>";
                row += "<td>" + term.trainerDTO.firstName + "</td>";
                let btn = "<button class='confirm_term' data-id=" + term.id + ">Confirm</button>";
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

$(document).on("click", ".confirm_term", function () {

    let termId = this.dataset.id;
    let userId = localStorage.getItem("id");
    console.log("Term", termId);
    console.log("User", userId);

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/terms/" + termId + "/" + userId,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS user", response);

            alert("Uspesna prijava za trening!");
            window.location.href = "allTerms.html";

        },
        error: function (response) {
            alert("Greska: nema slobodnih mesta ili ste se vec prijavili!!!");
            window.location.href = "allTerms.html";
        }
    });
});