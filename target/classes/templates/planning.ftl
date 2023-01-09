<#import "parts/common.ftl" as c>
<@c.page>
    <h3>Тематическое планирование</h3>

    <form method="get" action="/planning" class="row row-cols-lg-auto g-3 align-items-center">
<#--        <div class="col-12">-->
<#--            <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by theme name">-->
<#--        </div>-->

<#--        <div class="col-12">-->
<#--            <button type="submit" class="btn btn-primary">Search</button>-->
<#--        </div>-->

        <div class="col-12">
            <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Поиск">
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Найти</button>
        </div>
    </form>

    <div class="container">
        <div class="row">
            <div class="col">
                <div class="mt-3">
                    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Добавить тему
                    </a>
                </div>

                <div class="collapse" id="collapseExample">
                    <div class="col-12 mt-3">
                        <form method="post" class="row row-cols-lg-auto align-items-center">
                            <div class="mb-3">
                                <input type="text" name="themeName" placeholder="Наименование темы" class="form-control"/>
                            </div>
                            <div class="mb-3">
                                <input type="text" name="course" placeholder="Имя направления" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="totalHours" placeholder="Количество часов" class="form-control">
                            </div>

                            <input type="hidden" name="_csrf" value="${_csrf.token}" class="form-control"/>
                            <div class="mb-3">
                                <button type="submit" class="btn btn-primary">Добавить</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Имя направления</th>
            <th scope="col">Наименование темы</th>
            <th scope="col">Количество часов</th>
            <th scope="col">ФИО преподавателя</th>
        </tr>
        </thead>
        <tbody>
        <#list themes as theme>
            <tr>
                <td>${theme.course}</td>
                <td>${theme.themeName}</td>
                <td>${theme.totalHours}</td>
                <td>${theme.staff}</td>
                <#--                <td><#list user.roles as role>${role}<#sep>, </#list></td>-->
                <td><a href="/theme/${theme.course}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>