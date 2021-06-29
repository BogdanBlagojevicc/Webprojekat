$(document).on("submit", "#addHall", function (event) {
    event.preventDefault();

    let capacity = $("#CAPACITY").val();
    let mark = $("#MARK").val();
    let adminId = localStorage.getItem("id");
    let fcId = localStorage.getItem("addHall")

    let newHall = {
        capacity,
        mark
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/halls/" + adminId + "/" + fcId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newHall),
        success: function (response) {
            console.log(response);

            alert("Hall je uspešno kreiran!");
            window.location.href = "showFitnessCenters.html";
        },
        error: function () {
            alert("Greška prilikom dodavanja hall!");
        }
    });
});