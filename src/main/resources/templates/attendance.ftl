<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <h2>Посещаемость</h2>
<#--    TODO: Сделать дропдаун со списком групп и фильтровать таким образом таблицу
        TODO: Сделать ajax-запрос к сущности AttendanceDates для отображения списка дат
        TODO: Сделать таблицу, в которой можно редактировать ячейки, которые могут отправить ajax-запрос (для отметки был/не был на занятии)
        TODO: Модифицировать Тематическое планнирование
-->

    <div class="container">
        <h4 class="mt-3">Посещаемость группы:</h4>
        <form>
            Направление: <select class="form-select form-select-lg mb-4" id="coursesDropDown" aria-label=".form-select-lg example">
                <option>Выберите направление</option>
                <#list courses as course>
                    <option value="${course.id}">${course.courseName}</option>
                </#list>
            </select>
            Группа: <select class="form-select form-select-lg mb-4" id="groupsDropDown" aria-label=".form-select-lg example"></select>
            <input type="hidden" name="_csrf" value="${_csrf.token}" class="form-control"/>
        </form>

        <div class="btn-group p-2">
            <a class="btn btn-primary" href="/attendance">Посещаемость</a>
            <a class="btn btn-primary px-2" href="/themes">Тематическое планирование</a>
        </div>
    </div>

    <div class="table-responsive-xl mt-5 overflow-scroll">
        <div class="row">
            <div class="col"></div>
            <div class="col text-uppercase fw-bold">Дата занятия</div>
        </div>

        <#--        <#if filterStudentsByGroup??>-->
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="text-start" scope="col">ФИО</th>
                <#list dates as date>
                    <th class="text-start" scope="col">${date.lessonDate}</th> <#-- TODO: попробовать подгружать даты через ajax-запрос
                    TODO: Даты подгружаются в заивисимости от того, какие группы выбрали в выпадющем списке
                    -->
                </#list>
            </tr>
            </thead>
            <tbody id="students">
            <#list students as student>
            <tr>
                    <td>${student.surname}</td>
<#--                    <td><#list student.mark as mark>${mark.dates.lessonDate}<#sep> </#list></td>-->
                    <td><#list student.mark as mark>${mark.mark}<#sep> </#list></td>
            </tr>
            </#list>
            <#--    TODO: Попробовать сделать посещаемость через JavaScript        -->
            <#--    TODO: Вывод студентов по группам        -->
            </tbody>
        </table>
    </div>
    <script>
        $(document).ready(function () {
            $('#coursesDropDown').on('change',function () {
                let courseId = $(this).val();
                $.ajax({
                    type: "GET",
                    url: '/attendance/' + courseId,
                    success: function (data) {
                        let response = JSON.parse(data);
                        let s = '';
                        console.log(response[0].title);
                        console.log(response);
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
                            resultStr+= '<tr><td class="text-justify" value="' + i + '">' + response[i] + '</td></tr>';
                        }
                        $("#students").html(resultStr);
                    }
                });
            });
        });
    </script>
</@c.page>