<#import "parts/common.ftl" as c>

<@c.page>
    <div class="d-flex justify-content-center">
        <div class="card w-50 d-flex">
            <h3 class="mt-2 text-center">Редактирование пользователя</h3>

            <div class="card-body mt-1">
                <form action="/user" method="post" class="form-group">
                    <div class="mb-3">
                        <label class="my-1 fs-5">Имя пользователя</label>
                        <input type="text" name="username" value="${user.username}" class="form-control">
                        <#list roles as role>
                            <div>
                                <label><input class="form-check-input" type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                            </div>
                        </#list>
                    </div>

                    <input type="hidden" value="${user.id}" name="userId">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary w-100">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>
