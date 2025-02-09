<#import "parts/common.ftl" as c>

<@c.page>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>День недели</th>
            <th>Время начала</th>
            <th>Время окончания</th>
            <th>Даты занятий</th>
        </tr>
        </thead>
        <tbody>
        <#list schedules as schedule>
            <tr>
                <td>${schedule.dayOfWeek}</td>
                <td>${schedule.startTime}</td>
                <td>${schedule.endTime}</td>
                <td>
                    <#list schedule.attendanceDates as date>
                        <div>${date.lessonDate}</div>
                    </#list>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

</@c.page>