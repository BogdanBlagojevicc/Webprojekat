$(document).on("submit", "#gradeTerm", function (event) {
    event.preventDefault();

    let grade = $("#GRADE").val();

    let userId = localStorage.getItem("id");
    let termId = localStorage.getItem("applyToGrade")

    console.log("ID term JE:  ", termId);

    let newApply = {
        grade
    }

    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/api/applys/grade/" + userId + "/" + termId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(newApply),
        success: function (response) {
            console.log(response);

            alert("Apply je uspešno grade!");
            window.location.href = "AllTrainings.html";
        },
        error: function () {
            alert("Greška prilikom grade apply!");
        }
    });
});