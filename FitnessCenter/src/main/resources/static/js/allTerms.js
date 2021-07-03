$(document).ready(function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
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

$(document).on('click', '.see_more_term', function () {

    let userId = localStorage.getItem("id");
    let termId = this.dataset.id;
    localStorage.setItem("seeMoreterm", termId);

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isUser/" + userId,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS:\n", response);

            alert("Vi ste user moze see more!");
            window.location.href = "SeeMoreTerm.html";
        },
        error: function (response) {
            alert("Vi niste user ne moze see more!");
            console.log("ERROR:\n", response);
        }
    });
});

$(document).on('click', '#btnSortByPriceAsc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortPriceAsc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Sort Asc", response);
            $('#term').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
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

$(document).on('click', '#btnSortByPriceDesc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortPriceDesc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Sort Desc", response);
            $('#term').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
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

$(document).on('click', '#btnSortByDateAsc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortDateAsc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Date Asc", response);
            $('#term').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
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

$(document).on('click', '#btnSortByDateDesc', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/sortDateDesc/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS Date Desc", response);
            $('#term').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
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

$(document).on("submit", "#training_price", function (event) {
    event.preventDefault();

    let price = $("#price").val();
    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/price/" + price + "/" + userid,
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS price", response);
            $('#term').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

            alert("Uspesna pretraga po price!");

        },
        error: function () {
            alert("Greška prilikom pregrage po price!");
        }
    });
});

$(document).on("submit", "#training_date", function (event) {
    event.preventDefault();

    let start = $("#start").val();
    let userid = localStorage.getItem("id");
    console.log("start", start);
    console.log("userId", userid);

    let newData = {
        start
    }

    console.log("json ", JSON.stringify(newData));

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/date?date=" + start + "&id=" + userid,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS date", response);
            $('#term').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

            alert("Uspesna pretraga po date!");

        },
        error: function (response) {
            alert("Greška prilikom pregrage po date!");
        }
    });
});

$(document).on("submit", "#find", function (event) {
    event.preventDefault();

    let name = $("#name").val();
    let type = $("#type").val();
    let description = $("#description").val();
    let price = $("#price").val();
    let start = $("#start").val();
    let userid = localStorage.getItem("id");
    console.log("name", name);
    console.log("type", type);
    console.log("description", description);
    console.log("price", price);
    console.log("start", start);
    console.log("userId", userid);
    let replacedStart = start.replace("T", " ");
    start = replacedStart;
    console.log("izmena start: ", start);
    if(price == ""){
        price = 1000000.00;
    }
    console.log("price: " , price);

    let newData = {
        name,
        type,
        description,
        price,
        start
    }

    console.log("json ", JSON.stringify(newData));

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/terms/date?date=" + replacedStart + "&id=" + userid + "&name=" + name
            + "&type=" + type + "&description=" + description + "&price=" + price,
        contentType: "application/json",
        success: function (response) {
            console.log("SUCCESS find", response);
            $('#term').find('tbody').children('tr:not(:first)').remove();

            for (let term of response) {
                let row = "<tr id='newTable'>";
                row += "<td>" + term.price + "</td>";
                row += "<td>" + term.start + "</td>";
                row += "<td>" + term.typeDTO.type + "</td>";
                row += "<td>" + term.typeDTO.name + "</td>";
                row += "<td>" + term.typeDTO.description + "</td>";
                let btn = "<button class='see_more_term' data-id=" + term.id + ">See more</button>";
                row += "<td>" + btn + "</td>";
                row += "</tr>";


                $('#term').append(row);
            }

            alert("Uspesan find!");

        },
        error: function (response) {
            alert("Greška prilikom find!");
        }
    });
});

$(document).on('click', '#showComingTerms', function () {

    let userid = localStorage.getItem("id");

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/users/isUser/" + userid,
        dataType: "json",
        success: function (response) {
            console.log("SUCCESS", response);

            alert("Vi ste user moze!");
            window.location.href = "SeeAllApplications.html";

        },
        error: function (response) {
            console.log("ERROR:\n", response);
        }
    });
});