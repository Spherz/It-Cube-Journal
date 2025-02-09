$(document).ready(function () {

    $('#coursesDropDown').on('change',function () {
        let courseId = $(this).val();
        $.ajax({
            type: "GET",
            url: '/attendance/' + courseId,
            success: function (data) {
                let response = JSON.parse(data);
                let s = '';
                for(let i = 0; i < response.length; i++) {
                    s+= '<option value="' + response[i] + '" name="' + response[i] + '">' + response[i] + '</option>';
                }
                return $('#groupsDropDown').html(s);
            }
        });
    });

    // TODO: Немного переделать вывод обучающихся в таблице
    $('#groupsDropDown').on('change',function () {
        let groupId = $(this).val();
        console.log(groupId);
        let resultStr = '';
        $.ajax({
            type: "GET",
            url:'/attendance/students/' + groupId,
            success: function (data) {
                let students = JSON.parse(data);
                for(let i = 0; i < students.length; i++) {
                    resultStr += '<tr data-student-id="' + students[i].id + '">';
                    resultStr += '<td class="text-justify">' + students[i].surname + ' ' + students[i].firstname + '</td>';
                    resultStr += '<td class="attendance-cells" data-student-id="' + students[i].id + '"></td>';
                    resultStr += '</tr>';
                }
                $("#students").html(resultStr);
            }
        });
    });

    $('#groupsDropDown').on('change',function () {
        let groupName = $(this).val();
        let resultStr = '';
        $.ajax({
            type: "GET",
            url:'/attendance/students/dates/' + groupName,
            success: function (datesData) {
                let response = JSON.parse(datesData);
                resultStr+= '<th class="text-justify">' + 'ФИО' + '</th>'
                for(let i = 0; i < response.length; i++) {
                    resultStr+= '<th class="text-start" value="' + i + '">' + response[i] + '</th>';
                }
                $("#dates").html(resultStr);
            }
        });
    });
});
