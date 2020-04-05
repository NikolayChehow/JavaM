function getUserTable() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/admin/allUsers",
        success: function (data) {
            $("#usersTable").append(
                "<tbody id=\'tableBody1\'>" +
                "</tbody>"
            );
            for (let i in data) {
                $("#tableBody1").append(
                    "<tr>" +
                    "<td>" + data[i].id + "</td>" +
                    "<td>" + data[i].firstName + "</td>" +
                    "<td>" + data[i].lastName + "</td>" +
                    "<td>" + data[i].email + "</td>" +
                    "<td>" + data[i].role + "</td>" +
                    "<td>" + data[i].state + "</td>" +

                    "<td><button type=\'button\' class=\'btn btn-primary\' data-toggle=\'modal\'" +
                    "data-target=\'#myModal\' data-ID=" + '"' + data[i].id + '"' +
                    "data-email=" + '"' + data[i].email + '"' + "data-lastName=" + '"' + data[i].lastName + '"' +
                    "data-firstName=" + '"' + data[i].firstName + '"' + ">Edit</button></td>" +


                    "<td id=\"deleteUs\">" +
                    "<input type=\"checkbox\" name=\"delete\" value=" + '"' + data[i].id + '"' + ">" +
                    "</td>" +
                    "</tr>"
                )
            }
            $('.table').after(

                "<div className=\"button-delete\" id=\"button-delete\">" +
                "<input type=\"submit\" name=\"delete\" value=\"Delete selected users\">" +
                "</div>"

            )
        }
    });
}

getUserTable();


$('#myModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);
    const idEdit = button.data('id');
    const emailEdit = button.data('email');
    const firstNameEdit = button.data('firstname');
    const lastNameEdit = button.data('lastname');
    const modal = $(this);
    modal.find('#idEdit').val(idEdit);
    modal.find('#emailEdit').val(emailEdit);
    modal.find('#firstNameEdit').val(firstNameEdit);
    modal.find('#lastNameEdit').val(lastNameEdit);
});

function closeEditModal() {
    //
    // $('#myModal').on('hidden.bs.modal', function () {
    //     $("#tableBody1").empty();
    //     $("#button-delete").empty();
    //     // do something…
    //     getUserTable();
    // });
    //
    //
    // // $("#myModal").close();
    // // $("#myModal").style.display = 'none';
    // // $("#myModal").style.display = '';

    $("#myModal").modal('toggle');
    $("#tableBody1").empty();
    $("#button-delete").empty();
    getUserTable();
}

function showTab() {
    $("#tableBody1").empty();
    $("#button-delete").empty();
        $("#table").tab('show');
       getUserTable();
}

$('#deleteForm').submit(function (e) { // Устанавливаем событие отправки для формы с id=form
    e.preventDefault();
    const form_data = $(this).serialize(); // Собираем все данные из формы
    $.ajax({
        type: "Post", // Метод отправки
        url: "/admin/deleteUsers/", // Путь до php файла отправителя
        data: form_data,
        success: function (data) {
            showTab();
        }
    });
});

function addUser() {

    let jey = {
        id: $("#id").val(),
        email: $("#email").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        password: $("#password").val(),
        role: $("#role").val(),
        state: $("#state").val(),
    };

    let myJson = JSON.stringify(jey);
    $.ajax({
        type: "PUT",
        url: "/admin/addUser",
        dataType: 'json',
        data: myJson,
        contentType: 'application/json',
        success: function (data) {
        }

    });
    setTimeout(() => { showTab(); }, 200);

}

function sendEditForm() {
    let jey = {
        id: $("#idEdit").val(),
        email: $("#emailEdit").val(),
        firstName: $("#firstNameEdit").val(),
        lastName: $("#lastNameEdit").val(),
        password: $("#passwordEdit").val(),
        role: $("#roleEdit").val(),
        state: $("#stateEdit").val(),
    };
    let myJson = JSON.stringify(jey);
    $.ajax({
        type: "POST",
        url: "/admin/updateUsers",
        dataType: 'json',
        data: myJson,
        contentType: 'application/json',

        success: function() {
                // if (data != "") {
                    alert("пользователь изменен");
                // } else {
            // closeEditModal().delay(100);

        }
    });
    setTimeout(() => { closeEditModal(); }, 200);

}
