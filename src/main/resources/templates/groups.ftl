<#import "parts/common.ftl" as c>
<@c.page>
    Groups page

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Наименование группы</th>
            <th scope="col">Название программы</th>
            <th scope="col">Количество часов</th>
            <th scope="col">Преподаватель</th>
            <th scope="col">Decree number</th>
            <th scope="col">Decree Date</th>
            <th scope="col">Форма обучения</th>
        </tr>
        </thead>
        <tbody>
        <#list groups as group>
            <tr>
                <td>${group.id}</td>
                <td>${group.groupName}</td>
                <td>${group.programName}</td>
                <td>${group.hours}</td>
                <td>${group.teacher}</td>
                <td>${group.decreeNumber}</td>
                <td>${group.decreeDate}</td>
                <td>${group.educationForm}</td>
                <#--                <td><#list user.roles as role>${role}<#sep>, </#list></td>-->
                <td><a href="/groups/${group.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>