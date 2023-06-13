$(document).ready(function(){
    getStudents();
});

function getStudents(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:9999/students',
        success: function(studentArray) {
            var studentsDiv = $('tbody#allStudents');

            if(studentArray.length == 0){
                var notify = $('h6#noStudents');
                notify.empty();
                notify.append("There are currently no students in the database!");
            }

            $.each(studentArray, function(index, student) {  
                var studentInfo = '<tr>';
                studentInfo += '<th scope="row">' + student.id + '</th>';
                studentInfo += '<td>' + student.name + '</td>';
                studentInfo += '<td>' + student.age + '</td>';
                studentInfo += '<td>' + student.mobile + '</td>';
                studentInfo += '<td>' + student.address + '</td>';
                studentInfo += '</tr>';
                studentsDiv.append(studentInfo);
            })
        },
        error: function() {
            //alert("something failed in getStudents()");
            var notify = $('h6#noStudents');
            notify.empty();
            notify.append("There are currently no students in the database!");
        }
    });
}

$(document).ready(function(){
    $('#addStudent').on("click", function(event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'http://localhost:9999/student',
            data: JSON.stringify({
                name: $('#inputName').val(),
                age: $('#inputAge').val(),
                mobile: $('#inputMobile').val(),
                address: $('#inputAddress').val()
            }),
            contentType: 'application/json',
            accept: 'application/json',
            success: function () {
                $('#errorMessages').empty();
                $('#inputName').val('');
                $('#inputAge').val('');
                $('#inputMobile').val('');
                $('#inputAddress').val('');
                var notify = $('h6#notifyUser');
                notify.append('Success! A new Student was created.');
            },
            error: function (event) {
                var err = JSON.parse(event.responseText);
                var notify = $('h6#errorMessages');
                notify.empty();
                $.each(err.detailsList, function(index, error) {  
                    var errorDetails = "";
                    errorDetails += error + "<br/>";
                    notify.append(errorDetails);
                });
                //var notify = $('h6#errorMessages');
               // notify.empty();
                //notify.append("There's been an error! Please try again with the correct inputs.");
            }
        })      
    });
});

function editStudent(boolVal){
    if(boolVal == false){
        id = $('#findEditStudent').val();
        $.ajax({
            type: 'GET',
            url: 'http://localhost:9999/students/' + $('#findEditStudent').val(),
            success: function(student, status) { 
                $('#studentFound').append('Student found!');
                $('#editId').val(student.id);
                $('#editName').val(student.name);
                $('#editAge').val(student.age);
                $('#editMobile').val(student.mobile);
                $('#editAddress').val(student.address);
            },
            error: function (event) {
                var err = JSON.parse(event.responseText);
                var notify = $('#errorMessages');
                notify.empty();
                notify.append(err.details);
            }
        })
    } else {
        $.ajax({
            type: 'PUT',
            url: 'http://localhost:9999/students/' + $('#editId').val(),
            data: JSON.stringify({
                name: $('#editName').val(),
                age: $('#editAge').val(),
                mobile: $('#editMobile').val(),
                address: $('#editAddress').val()
            }),
            contentType: 'application/json',
            accept: 'application/json',
            success: function () {
                $('#errorMessages').empty();
                var notify = $('h6#notifyUser');
                notify.append('Success! The student was edited.');
            },
            error: function (event) {
                var err = JSON.parse(event.responseText);
                var notify = $('h6#errorMessages');
                notify.empty();
                $.each(err.detailsList, function(index, error) {  
                    var errorDetails = "";
                    errorDetails += error + "<br/>";
                    notify.append(errorDetails);
                });
                //var notify = $('h6#errorMessages');
               // notify.empty();
                //notify.append("There's been an error! Please try again with the correct inputs.");
            }
        })
    }   
}

function deleteStudent(boolVal){
    if(boolVal == false){
        id = $('#findRemoveStudent').val();
        $.ajax({
            type: 'GET',
            url: 'http://localhost:9999/students/' + $('#findRemoveStudent').val(),
            success: function(student, status) {
                $('#studentFound').append('Student found!');
                var studentDiv = $('div#displayStudent');

                var studentInfo = '<b>ID:</b> ' + student.id + '<br>';
                studentInfo += '<b>Name:</b> ' + student.name + '<br>';
                studentInfo += '<b>Age:</b> ' + student.age + '<br>';
                studentInfo += '<b>Mobile:</b> ' + student.mobile + '<br>';
                studentInfo += '<b>Address:</b> ' + student.address + '<br>';

                studentDiv.append(studentInfo);

                $('#confirmDelete').html('<button type="button" class="btn btn-danger" style="font-size: larger; font-weight: bold; border-color: rgb(192, 93, 106); background-color: rgb(192, 93, 106);" onclick="removeStudent('+ student.id +')" id="confirmDeleteBtn">Delete this user</button>');
            },
            error: function (event) {
                var err = JSON.parse(event.responseText);
                var notify = $('#errorMessages');
                notify.empty();
                notify.append(err.details);
            }
        })
    }
}

function removeStudent(id){
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:9999/students/' + id,
        success: function() {
            $('#errorMessages').empty();
            $('div#displayStudent').empty();
            $('#confirmDelete').empty();
            $('#studentFound').empty();
            var notify = $('h6#notifyUser');
            notify.append('Success! The student was removed.');
        },
        error: function (event) {
            var err = JSON.parse(event.responseText);
            var notify = $('#errorMessages');
            notify.empty();
            notify.append(err.details);
        }
    });
}

function searchStudent(){
    window.location.replace("single_student_page.html?id="+$('#findStudent').val());
}

function findStudentById(id){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:9999/students/' + id,
        success: function(student, status) {
            $('#studentFound').append('Student found!');
            var studentDiv = $('tbody#singleStudent');

            var studentInfo = '<tr>';
            studentInfo += '<th scope="row">' + student.id + '</th>';
            studentInfo += '<td>' + student.name + '</td>';
            studentInfo += '<td>' + student.age + '</td>';
            studentInfo += '<td>' + student.mobile + '</td>';
            studentInfo += '<td>' + student.address + '</td>';
            studentInfo += '</tr>';

            studentDiv.append(studentInfo);
        },
        error: function (event) {
            var err = JSON.parse(event.responseText);
            var notify = $('#errorMessages');
            notify.empty();
            notify.append(err.details);
        }
    })
}
