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

        <form method="get" action="/attendance">
            Направление:
            <select name="courseId" class="form-select form-select-lg mb-4" onchange="this.form.submit()">
                <option value="">Выберите направление</option>
                <#list courses as course>
                    <option value="${course.id}" <#if selectedCourseId?? && selectedCourseId == course.id>selected</#if>>
                        ${course.courseName}
                    </option>
                </#list>
            </select>

            <#if groups??>
                Группа:
                <select name="groupId" class="form-select form-select-lg mb-4" onchange="this.form.submit()">
                    <option value="">Выберите группу</option>
                    <#list groups as group>
                        <option value="${group.id}" <#if selectedGroupId?? && selectedGroupId == group.id>selected</#if>>
                            ${group.groupName}
                        </option>
                    </#list>
                </select>
            </#if>

            <input type="hidden" id="_csrf" value="${_csrf.token}" />
            <input type="hidden" id="_csrf_header" value="${(_csrf_header.headerName)!}" />
        </form>

        <div class="btn-group p-2">
            <a class="btn btn-primary" href="/attendance">Посещаемость</a>
            <a class="btn btn-primary px-2" href="/themes">Тематическое планирование</a>
        </div>
    </div>

    <div class="table-responsive-xl mt-5 overflow-scroll">
        <table class="table table-hover" id="studentsAttendance">
            <thead>
            <tr>
                <th scope="col" class="text-start">ФИО</th>
                <#if dates??>
                    <#list dates as date>
                        <th scope="col" class="text-start">${date}</th>
                    </#list>
                <#else>
                    <th class="text-start">Дата занятия</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if students??>
                <#list students as student>
                    <tr>
                        <td>${student.surname} ${student.firstname} ${student.secondname}</td>
                        <#list dates as date>
                            <td class="attendance-cells text-center"
                                data-student-id="${student.id}"
                                data-date="${date}">
                                <#if attendanceMap[(student.id + "-" + date)]??>
                                    ${attendanceMap[(student.id + "-" + date)]}
                                </#if>
                            </td>
                        </#list>
                    </tr>
                </#list>
            <#else>
                <tr></tr>
            </#if>
            </tbody>
        </table>
    </div>
    <script src="../static/js/loadStudents.js"></script>

</@c.page>