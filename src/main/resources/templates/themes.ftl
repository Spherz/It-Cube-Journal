<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>

    <form method="get" action="/themes" class="row row-cols-lg-auto g-3 align-items-center">
        <div class="col-12">
            <input type="text" name="filter" class="form-control" value="${filterStudentsByGroup!}" placeholder="Поиск по группе">
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Найти</button>
        </div>
    </form>

    <div class="btn-group p-2">
        <a class="btn btn-primary" href="/attendance">Посещаемость</a>
        <a class="btn btn-primary px-2" href="/themes">Тематическое планирование</a>
    </div>

    <div class="table-responsive-xl mt-5 overflow-scroll">
        <div class="row">
            <div class="col"></div>
            <div class="col text-uppercase fw-bold">Дата занятия</div>
        </div>
                <#if filterStudentsByGroup??>
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="text-center" scope="col">№</th>
                <th class="text-center" scope="col">Дата проведения занятия</th>
                <th class="text-center" scope="col">Количество часов</th>
                <th class="text-center" scope="col">Наименование темы, занятия</th>
            </tr>
            </thead>
            <tbody>
            <#list themesList as theme>
                <tr>
                    <td class="text-center">${theme.id}</td>
                    <td class="text-center">${theme.date}</td>
                    <td class="text-center">${theme.hoursCount}</td>
                    <td class="text-center">${theme.themeName}</td>
                    <td class="text-end"><a href="/attendance/${theme.id}">edit</a></td>
                </tr>
            </#list>
            </tbody>
        </table>
                <#else>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="text-center" scope="col">№</th>
                            <th class="text-center" scope="col">ФИО</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="text-justify"></td>
                            <td class="text-justify"></td>
                            <td class="text-justify"></td>
                        </tr>
                        </tbody>
                    </table>
                </#if>
    </div>
</@c.page>