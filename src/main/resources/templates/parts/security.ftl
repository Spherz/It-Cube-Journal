<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        teacherName = user.getStaff()
        isAdmin = user.isAdmin()
        isTeacher = user.isTeacher()
        isMethodist = user.isMethodist()
    >
<#else>
    <#assign
        name = "unknown"
        teacherName = "unkhown"
        isAdmin = false
        isTeacher = false
        isMethodist = false
    >

</#if>
