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
        <form method="get" action="/attendance" class="row row-cols-lg-auto g-3 align-items-center">
            <div class="col-12">
                <input type="text" name="filter" class="form-control" value="${filterStudentsByGroup!}" placeholder="Поиск">
            </div>

            <div class="col-12">
                <button type="submit" class="btn btn-primary">Найти</button>
            </div>
<#--            TODO: Потестить @RequestParam в контроллере-->
<#--            <select class="form-select form-select-lg mb-4" id="groupId" aria-label=".form-select-lg example">-->
<#--                <option type="submit">Выберите группу</option>-->
<#--                <#list groups as group>-->
<#--                    <option id="groupId" name="groupId" value="${filterStudentsByGroup!}" type="submit">${group.groupName}</option>-->
<#--                </#list>-->
<#--            </select>-->
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
                        <#list attend.mark as mark><td class="text-center">${mark}<#sep> </#list></td>
                        <td class="text-center"><a href="/attendance/${attend.id}">edit</a></td>
                    </tr>
                </#list>
                </tbody>
            </table>
<#--        <#else>-->
<#--            <table class="table table-hover">-->
<#--                <thead>-->
<#--                <tr>-->
<#--                    <th class="text-center" scope="col">№</th>-->
<#--                    <th class="text-center" scope="col">ФИО</th>-->
<#--                </tr>-->
<#--                </thead>-->
<#--                <tbody>-->
<#--                <tr>-->
<#--                    <td class="text-justify"></td>-->
<#--                    <td class="text-justify"></td>-->
<#--                    <td class="text-justify"></td>-->
<#--                </tr>-->
<#--                </tbody>-->
<#--            </table>-->
<#--        </#if>-->
    </div>
</@c.page>