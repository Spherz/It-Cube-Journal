<#import "parts/common.ftl" as c>

<@c.page>
    <div class="d-flex justify-content-center">
        <div class="card w-50 d-flex">
            <h3 class="mt-2 text-center">Редактирование персонала</h3>

            <div class="card-body mt-1">
                <form action="/staff/update/${staff.id}" method="post" class="form-group">
                    <div class="mb-3">
                        <label class="my-1 fs-5">Имя</label>
                        <input type="text" name="firstName" disabled value="${staff.firstname}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Фамилия</label>
                        <input type="text" name="surname" disabled value="${staff.surname}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Отчество</label>
                        <input type="text" name="fatherName" disabled value="${staff.secondname}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Дата Рождения</label>
                        <input type="text" name="birthDate" disabled value="${staff.birthDate}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Образование</label>
                        <input type="text" name="education" value="${staff.education}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Номер диплома</label>
                        <input type="text" name="diplomaNumber" value="${staff.diplomaNumber}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Квалификация</label>
                        <input type="text" name="qualification" value="${staff.qualification}" class="form-control">
                    </div>

                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary w-100">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>