$(document).ready(function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/doneNotGradeTerms/" + userid,
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
                let btn = "<button class='grade_term' data-id=" + term.id + ">Grade</button>";
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

$(document).on('click', '.grade_term', function () {

    let userId = localStorage.getItem("id");
    let termId = this.dataset.id;
    localStorage.setItem("applyToGrade", termId);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isUser/" + userId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste user moze grade!");
            window.location.href = "gradeTerm.html";
        },
        error: function (response) {
            alert("Vi niste user ne moze grade!");
            console.log("ERROR:\n", response);
        }
    });
});