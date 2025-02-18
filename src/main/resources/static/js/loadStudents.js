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
                let response = JSON.parse(data);
                console.log(response);
                for(let i = 0; i < response.length; i++) {
                    resultStr+= '<tr><td id="studentsList" class="text-justify" value="' + response[i].id + '">' + response[i] + '</td><td>' + '</td></tr>';
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
