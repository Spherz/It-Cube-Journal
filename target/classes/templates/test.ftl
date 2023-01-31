<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>

    <script>
        $(document).ready(function () {
            console.log("Hello Java");
        });
    </script>
    <h2>Посещаемость</h2>

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

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="text-center" scope="col">№</th>
                <th class="text-start" scope="col">ФИО</th>
                <th class="text-start" scope="col">Дата</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text-center"></td>
                    <td class="text-justify"></td>
                </tr>
            </tbody>
        </table>
    </div>
</@c.page>