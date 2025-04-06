<#import "parts/common.ftl" as c>

<@c.page>
    <div class="d-flex justify-content-center">
        <div class="card w-50 d-flex flex-column p-3">
            <h3 class="mt-2 text-center">Редактирование студента</h3>

            <form action="/students/update/${student.id}" method="post" class="form-group">
                <div class="row flex-nowrap">
                    <div class="card-body mt-1">
                        <div class="mb-3">
                            <label class="my-1 fs-5">Имя</label>
                            <input type="text" name="firstName" disabled value="${student.firstname}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Фамилия</label>
                            <input type="text" name="surname" disabled value="${student.surname}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Отчество</label>
                            <input type="text" name="fatherName" disabled value="${student.secondname}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Дата Рождения</label>
                            <input type="text" name="birthDate" disabled value="${student.dateOfBirth}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Номер Сертификата ПФДО</label>
                            <input type="text" name="certificateNumber" value="${student.certificateNumber}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">ФИО Родителя</label>
                            <input type="text" name="parent" value="${student.parent}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Класс</label>
                            <input type="text" name="studentClass" value="${student.studentClass}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Учебное заведение</label>
                            <input type="text" name="school" value="${student.school}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Номер телефона</label>
                            <input type="text" name="phoneNumber" value="${student.phoneNumber}" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Электронная почта</label>
                            <input type="text" name="email" value="${student.email}" class="form-control">
                        </div>
                    </div>


                    <div class="card-body mt-1 col-8">
                        <div class="mb-3">
                            <label class="my-1 fs-5">Направление</label>
                            <#list courses as course>
                                <div class="form-check">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        <input class="form-check-input" name="studentCourse" type="radio"
                                               value="${course.courseName}"
                                               ${(student.course?? && student.course.courseName == course.courseName)?string("checked", "")}
                                               id="flexCheckDefault">
                                        ${course.courseName}
                                    </label>
                                </div>
                            </#list>
                        </div>

                        <div class="mb-3">
                            <label class="my-1 fs-5">Группа</label>
                            <#list groups as group>
                                <div class="form-check">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        <input class="form-check-input" name="studentGroup" type="radio"
                                               value="${group.groupName}"
                                                ${(student.nameGroup?? && student.nameGroup.groupName == group.groupName)?string("checked", "")}
                                               id="flexCheckDefault">
                                        ${group.groupName}
                                    </label>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>

                <div class="row justify-content-center">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary w-100">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</@c.page>