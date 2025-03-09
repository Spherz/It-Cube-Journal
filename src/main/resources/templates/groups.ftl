<#import "parts/common.ftl" as c>
<@c.page>

    <h3 class="text-center">Группы</h3>

    <div class="row">
        <div class="col">
            <div class="mt-3">
                <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Создать группу
                </a>
            </div>

            <div class="collapse" id="collapseExample">
                <div class="col-12 mt-3">
                    <form method="post" action="groups/create" class="row row-cols-lg-auto align-items-center">
                        <div class="mb-3">
                            <input type="text" name="groupName" placeholder="Введите название группы" class="form-control"/>
                        </div>
                        <div class="mb-3">
                            <input type="text" name="programName" placeholder="Программа обучения" class="form-control">
                        </div>

                        <div class="mb-3">
                            <input type="text" name="hours" placeholder="Количество часов" class="form-control">
                        </div>

                        <div class="mb-3">
                            <input type="text" name="teacher" placeholder="Преподаватель" class="form-control">
                        </div>

                        <div class="mb-3">
                            <input type="text" name="decreeNumber" placeholder="Номер приказа" class="form-control">
                        </div>

                        <div class="mb-3">
                            <input type="text" name="decreeDate" placeholder="Дата приказа" class="form-control">
                        </div>

                        <div class="mb-3">
                            <input type="text" name="educationForm" placeholder="Форма обучения" class="form-control">
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

    <table class="table table-bordered table-hover mt-3">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Наименование группы</th>
            <th scope="col">Название программы</th>
            <th scope="col">Количество часов</th>
            <th scope="col">Преподаватель</th>
            <th scope="col">Номер приказа</th>
            <th scope="col">Дата приказа</th>
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
                <td><a href="/groups/${group.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>