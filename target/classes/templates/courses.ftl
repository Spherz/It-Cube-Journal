<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <script>
        $(document).ready(function () {
            $('#coursesDropDown').change(function () {
                let courseId = $(this).val();
                $.ajax({
                    type: "GET",
                    url: '/courses/' + courseId,
                    success: function (result) {
                        var result = JSON.parse(result);
                        let s = '';
                        for(let i = 0; i < result.length; i++) {
                            s+= '<option value=">' + result[i].id + '">' + result[i].name + '</option>';
                        }
                        $('studentsDropDown').html(s);
                    }
                });
            });
        });
    </script>

    <form action="/user" method="post">
        Направления: <select class="form-select form-select-lg mb-4" id="coursesDropDown" aria-label=".form-select-lg example">
            <option>Выберите направление</option>
            <#list courses as course>
                <option value="${course.id}">${course.courseName}</option>
            </#list>
        </select>
        Students: <select class="form-select form-select-lg mb-4" id="studentsDropDown" aria-label=".form-select-lg example"></select>
    </form>
</@c.page>