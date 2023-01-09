<#import "parts/common.ftl" as c>


<@c.page>
    <form method="get" action="/main" class="row row-cols-lg-auto g-3 align-items-center">
        <div class="col-12">
            <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <div class="mt-3">
        <a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add new Message
        </a>
    </div>

    <div class="collapse" id="collapseExample">
        <div class="col-12 mt-3">
            <form method="post" enctype="multipart/form-data" class="row row-cols-lg-auto align-items-center">
                <div class="mb-3">
                    <input type="text" name="text" placeholder="Введите сообщение" class="form-control"/>
                </div>
                <div class="mb-3">
                    <input type="text" name="tag" placeholder="Тэг" class="form-control">
                </div>
                <div class="mb-0">
                    <input type="file" name="file" class="form-control" id="formFile">
                    <label for="formFile" class="form-label">Выберите файл</label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" class="form-control"/>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <#list messages as message>
            <div class="col">
                <div class="card my-3">
                    <#if message.filename??>
                        <img class=card-img-top" src="/img/${message.filename}" alt="">
                    </#if>

                    <div class="m-2">
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                    </div>

                    <div class="card-footer text-muted">
                        ${message.authorName}
                    </div>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>