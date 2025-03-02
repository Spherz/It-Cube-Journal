<#import "parts/common.ftl" as c>

<@c.page>
    <div class="d-flex justify-content-center">
        <div class="card w-50 d-flex">
            <h3 class="mt-2 text-center">Редактирование студента</h3>

            <div class="card-body mt-1">
                <form action="/students/update/${student.id}" method="post" class="form-group">
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

                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary w-100">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>