$(document).ready(function () {

    let hallId = localStorage.getItem("updateHallId")

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/halls/" + hallId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            document.getElementById("CAPACITY").defaultValue=response.capacity;
            document.getElementById("MARK").defaultValue=response.mark;

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});