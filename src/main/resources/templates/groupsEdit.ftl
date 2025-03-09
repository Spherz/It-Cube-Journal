<#import "parts/common.ftl" as c>
<@c.page>
    <div class="d-flex justify-content-center">
        <div class="card w-50 d-flex">
            <h3 class="mt-2 text-center">Редактирование группы</h3>

            <div class="card-body mt-1">
                <form action="/groups/update/${groups.id}" method="post" class="form-group">
                    <div class="mb-3">
                        <label class="my-1 fs-5">Имя группы</label>
                        <input type="text" name="groupName" readonly value="${groups.groupName}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Название программы</label>
                        <input type="text" name="programName" readonly value="${groups.programName}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Количество часов</label>
                        <input type="text" name="hours" readonly value="${groups.hours}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Преподаватель</label>
                        <input type="text" name="teacher" value="${groups.teacher}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Номер приказа</label>
                        <input type="text" name="decreeNumber" value="${groups.decreeNumber}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Дата приказа</label>
                        <input type="text" name="decreeDate" value="${groups.decreeDate}" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label class="my-1 fs-5">Форма обучения</label>
                        <input type="text" name="educationForm" value="${groups.educationForm}" class="form-control">
                    </div>

                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary w-100">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>