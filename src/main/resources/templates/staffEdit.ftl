<#import "parts/common.ftl" as c>

<@c.page>
    <div class="d-flex justify-content-center">
        <div class="card w-75 d-flex flex-column p-3">
            <h3 class="mt-2 text-center">Редактирование персонала</h3>

            <form action="/staff/update/${staff.id}" method="post" class="form-group">
                <div class="row flex-nowrap">
                    <div class="card-body mt-1">
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
                    </div>

                    <div class="card-body mt-1 col-8">
                        <div class="mb-3">
                            <label class="my-1 fs-5">Группы</label>
                            <#list groups as group>
                                <div class="form-check">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        <input class="form-check-input" name="staffGroup" type="checkbox"
                                               value="${group.groupName}"
                                                ${staff.teacherGroups?seq_contains(group)?string("checked", "")}
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
                    <button type="submit" class="btn btn-primary col-4">Сохранить</button>
                </div>
            </form>
            <div class="card-body mt-1">
            </div>
        </div>
    </div>
</@c.page>