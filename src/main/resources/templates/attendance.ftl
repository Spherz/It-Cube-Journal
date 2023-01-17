<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <h2>Посещаемость</h2>

    <div class="container">
        <h4 class="mt-3">Посещаемость группы:</h4>
        <form method="get" action="/attendance" class="row row-cols-lg-auto g-3 align-items-center">

<#--            <div class="col-12">-->
<#--                <input type="text" name="filter" class="form-control" value="${filterStudents!}" placeholder="Поиск">-->
<#--            </div>-->

<#--            <div class="col-12">-->
<#--                <button type="submit" class="btn btn-primary">Найти</button>-->
<#--            </div>-->
                <div class="col-12">
                    <input type="text" name="filter" class="form-control" value="${filterStudentsByGroup!}" placeholder="Поиск">
                </div>

                <div class="col-12">
                    <button type="submit" class="btn btn-primary">Найти</button>
                </div>
<#--                <select class="form-select form-select-lg mb-4" aria-label=".form-select-lg example">-->
<#--                    <option>Выберите группу</option>-->
<#--                    <#list groups as group>-->
<#--                        <option value="${filterStudents!}">${group.groupName}</option>-->
<#--                    </#list>-->
<#--                </select>-->
        </form>
    </div>

    <div class="table-responsive-xl mt-5 overflow-scroll">
        <div class="row">
            <div class="col"></div>
            <div class="col text-uppercase fw-bold">Дата занятия</div>
        </div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="text-center" scope="col">№</th>
                <th class="text-start" scope="col">ФИО</th>
                <#list dates as date>
                    <th class="text-start" scope="col">${date.lessonDate}</th>
                </#list>
            </tr>
            </thead>
            <tbody>
            <#--    TODO: Попробовать сделать посещаемость через JavaScript        -->
            <#--    TODO: Вывод студентов по группам        -->
                <#list attendance as attend>
                    <tr>
                        <td class="text-center">${attend.id}</td>
                        <td class="text-justify">${attend.students}</td>
                        <td class="text-center">
                            <#list attend.mark as mark>${mark}<#sep>,</#list>
                        </td>
                        <#--                <td><#list user.roles as role>${role}<#sep>, </#list></td>-->
<#--                        <td class="text-center"><a href="/student/${attend.id}">edit</a></td>-->
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>


</@c.page>