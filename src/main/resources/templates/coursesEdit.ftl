<#import "parts/common.ftl" as c>
<@c.page>
    <div class="d-flex justify-content-center">
        <div class="card w-50 d-flex">
            <h3 class="mt-2 text-center">Редактирование направления</h3>

            <form action="/courses/update/${course.id}" method="post" class="form-group">
                <div class="card-body mt-1">
                    <div class="mb-3">
                        <label class="my-1 fs-5">Имя направления</label>
                        <input type="text" name="courseName" value="${course.courseName}" class="form-control">
                    </div>
                </div>


                <div class="card-body mt-1">
                    <div class="mb-3">
                        <label class="my-1 fs-5">Группы</label>
                        <#list groups as group>
                            <div class="form-check">
                                <label class="form-check-label" for="flexCheckDefault">
                                    <input class="form-check-input" name="courseGroup" type="checkbox"
                                           value="${group.groupName}"
                                           ${course.groups?seq_contains(group)?string("checked", "")}
                                           id="flexCheckDefault">

                                        ${group.groupName}
                                </label>
                            </div>
                        </#list>
                    </div>

                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary w-100">Сохранить</button>
                </div>
            </form>


        </div>
    </div>
</@c.page>