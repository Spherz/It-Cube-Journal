<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">It-Cube Journal</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/">Главная</a>
                </li>
                <#if isTeacher || isMethodist || isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/students">Списки студентов</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/planning">Тематическое планирование</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/groups">Списки групп</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/staff">Персонал</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/attendance">Посещаемость</a>
                    </li>
                </#if>

                <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/user">Список пользователей</a>
                </li>
                </#if>
            </ul>
        </div>
            <div class="navbar-text me-3">${name}</div>
            <div class="navbar-text me-3">${teacherName}</div>
        <@l.logout />
    </div>
</nav>