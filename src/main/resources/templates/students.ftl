<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <h3>Students page</h3>

    <form method="get" action="/students" class="row row-cols-lg-auto g-3 align-items-center">
        <div class="col-12">
            <input type="text" name="filter" class="form-control" value="${filterStudents!}" placeholder="Поиск">
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
                        Добавить студента
                    </a>
                </div>

                <div class="collapse" id="collapseExample">
                    <div class="col-12 mt-3">
                        <form method="post" class="row row-cols-lg-auto align-items-center">
                            <div class="mb-3">
                                <input type="text" name="surname" placeholder="Введите фамилию обучающегося" class="form-control"/>
                            </div>
                            <div class="mb-3">
                                <input type="text" name="firstname" placeholder="Введите имя обучающегося" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="secondname" placeholder="Введите отчество обучающегося" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="dateOfBirth" placeholder="Дату рождения" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="certificateNumber" placeholder="№ сертификата ПФДО" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="studentClass" placeholder="Класс" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="parent" placeholder="ФИО Родителя" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="school" placeholder="Школа" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="phoneNumber" placeholder="Номер телефона" class="form-control">
                            </div>

                            <div class="mb-3">
                                <input type="text" name="email" placeholder="Электронная почта" class="form-control">
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

    <@p.pager url page filterStudents />

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Имя</th>
            <th scope="col">Отчество</th>
            <th scope="col">Группа</th>
            <th scope="col">Преподаватель</th>
            <th scope="col">Дата рождения</th>
            <th scope="col">№ сертификата ПФДО</th>
            <th scope="col">Класс</th>
            <th scope="col">ФИО Родителя</th>
            <th scope="col">Школа</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Электронная почта</th>
        </tr>
        </thead>
        <tbody>
        <#list page.content as student>
            <tr>
                <td>${student.id}</td>
                <td>${student.surname}</td>
                <td>${student.firstname}</td>
                <td>${student.secondname}</td>
                <td>${student.nameGroup}</td>
                <td>${student.staff}</td>
                <td>${student.getFormattedBirthday()}</td>
                <td>${student.certificateNumber}</td>
                <td>${student.studentClass}</td>
                <td>${student.parent}</td>
                <td>${student.school}</td>
                <td>${student.phoneNumber}</td>
                <td>${student.email}</td>
                <td><a href="/students/${student.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
    <@p.pager url page filterStudents/>
</@c.page>