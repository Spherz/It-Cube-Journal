<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <h2>Посещаемость</h2>

    <h3>Посещаемость группы:</h3>

    <form method="get" action="/attendance" class="row row-cols-lg-auto g-3 align-items-center">
        <div class="col-12">
            <input type="text" name="filter" class="form-control" value="${filterStudents!}" placeholder="Поиск группы">
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <div class="table-responsive-xl">
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="text-center" scope="col">№</th>
                <th class="text-center" scope="col">ФИО</th>
                <th class="text-center" scope="col">${dates[1].date}</th>
            </tr>
            </thead>
            <tbody>
            <#list attendance as studentAttendance>
                <tr>
                    <td class="text-center">${studentAttendance.id}</td>
                    <td class="text-justify">${studentAttendance.students}</td>
                    <td class="text-center">${studentAttendance.mark}</td>
                    <#--                <td><#list user.roles as role>${role}<#sep>, </#list></td>-->
                    <td class="text-center"><a href="/student/${studentAttendance.id}">edit</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>


</@c.page>