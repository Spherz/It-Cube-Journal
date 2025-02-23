<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <div class="row">
        <h3 class="text-center">Направления</h3>

        <div class="col">
            <div class="mt-3">
                <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Добавление курса
                </a>
            </div>

            <div class="card col-6">
                <div class="card-body">
                    <div class="collapse" id="collapseExample">
                        <div class="mt-3">
                            <form method="post" action="/courses/create" class="row align-items-center">
                                <div class="col-6 mb-3">
                                    <input type="text" name="courseName" placeholder="Введите наименование направления" class="form-control"/>
                                </div>

                                <div class="mb-3">
                                    <button type="submit" class="btn btn-primary">Добавить</button>
                                </div>

                                <input type="hidden" name="_csrf" value="${_csrf.token}" class="form-control"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Наименование направления</th>
        </tr>
        </thead>
        <tbody>
        <#list courses as course>
            <tr>
                <td>${course.id}</td>
                <td>${course.courseName}</td>
                <td><a href="/staff/${course.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>