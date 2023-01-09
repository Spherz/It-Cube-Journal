<#import "parts/common.ftl" as c>

<@c.page>
    <h4>Добавление темы</h4>

    <form action="/planning" method="get">
        <div class="form-group row">
            <label class="col-sm-2 form-label"> Тема: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="Название темы занятия"/>
            </div>
        </div>

        <br>
        <div class="form-group row">
            <label class="col-sm-2 form-label"> Направление: </label>
            <div class="col-sm-6">
                <input type="text" name="password" class="form-control" placeholder="Имя направления"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 form-label"> Количество часов: </label>
            <div class="col-sm-6">
                <input type="text" name="password" class="form-control" placeholder="Количество часов"/>
            </div>
        </div>
    </form>





</@c.page>