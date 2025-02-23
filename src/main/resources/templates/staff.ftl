<#import "parts/common.ftl" as c>
<@c.page>
    <h3>Staff</h3>

    <div class="container">
        <div class="row">
            <div class="col">
                <div class="mt-3">
                    <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Добавить сотрудника
                    </a>
                </div>

                <div class="collapse" id="collapseExample">
                    <div class="col-12 mt-3">
                        <form method="post" class="row row-cols-lg-auto align-items-center">
                            <div class="mb-3">
                                <input type="text" name="surname" placeholder="Введите фамилию сотрудника" class="form-control"/>
                            </div>
                            <div class="mb-3">
                                <input type="text" name="firstname" placeholder="Введите имя сотрудника" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="secondname" placeholder="Введите отчество сотрудника" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="dateOfBirth" placeholder="Дату рождения" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="education" placeholder="Уровень образования" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="diplomaNumber" placeholder="Номер диплома" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="qualification" placeholder="Уровень квалификации" class="form-control">
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
            <th scope="col">№</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Имя</th>
            <th scope="col">Отчество</th>
            <th scope="col">Дата рождения</th>
            <th scope="col">Образование</th>
            <th scope="col">Номер диплома</th>
            <th scope="col">Уровень квалификации</th>
        </tr>
        </thead>
        <tbody>
        <#list employees as staff>
            <tr>
                <td>${staff.id}</td>
                <td>${staff.surname}</td>
                <td>${staff.firstname}</td>
                <td>${staff.secondname}</td>
                <td>${staff.birthDate}</td>
                <td>${staff.education}</td>
                <td>${staff.diplomaNumber}</td>
                <td>${staff.qualification}</td>
                <td><a href="/staff/${staff.id}">Редактировать</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>