$(document).on("submit", "#updateHall", function (event) {
    event.preventDefault();

    let capacity = $("#CAPACITY").val();
    let mark = $("#MARK").val();

    let adminId = localStorage.getItem("id");
    let hallId = localStorage.getItem("updateHallId")

    console.log("ID Hall JE:  ", hallId);

    let newHall = {
        capacity,
        mark
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/halls/" + adminId + "/" + hallId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newHall),
        success: function (response) {
            console.log(response);

            alert("Hall je uspešno update!");
            window.location.href = "SeeMoreHall.html";
        },
        error: function () {
            alert("Greška prilikom update hall!");
        }
    });
});