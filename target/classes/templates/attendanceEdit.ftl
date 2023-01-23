<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <h2>Посещаемость</h2>
    <form action="/attendance" method="post" class="form-horizontal">
        <p>${attendance.students}</p>

        <select class="form-select form-select-lg mb-4 form-control" aria-label=".form-select-lg example">
            <option class="form-control">Выберите дату</option>
            <#list dates as date>
                <option class="form-control" name="date" value="${date.id}">${date.lessonDate}</option>
            </#list>
        </select>
        <#list marks as mark>
            <div>
                <label class="form-check-label"><input class="form-check-input" type="checkbox" name="${mark}" ${attendance.mark?seq_contains(mark)?string("checked", "")}>${mark}</label>
            </div>
        </#list>
        <input type="hidden" value="${attendance.id}" name="attendanceId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary mt-3" type="submit">Сохранить</button>
    </form>
</@c.page>