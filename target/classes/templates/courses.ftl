<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <form>
        Направление: <select class="form-select form-select-lg mb-4" id="coursesDropDown" aria-label=".form-select-lg example">
            <option>Выберите направление</option>
            <#list courses as course>
                <option value="${course.id}">${course.courseName}</option>
            </#list>
        </select>
        Список студентов: <select class="form-select form-select-lg mb-4" id="studentsDropDown" aria-label=".form-select-lg example"></select>
        <input type="hidden" name="_csrf" value="${_csrf.token}" class="form-control"/>
    </form>
    <script>
        $(document).ready(function () {
            $('#coursesDropDown').change(function () {
                let courseId = $(this).val();
                $.ajax({
                    type: "GET",
                    url: '/courses/' + courseId,
                    success: function (data) {
                        let response = JSON.parse(data);
                        let s = '';
                        console.log(response[0].title);
                        console.log(response);
                        for(let i = 0; i < response.length; i++) {
                            s+= '<option value="' + i + '">' + response[i] + '</option>';
                        }
                        return $('#studentsDropDown').html(s);
                    }
                });
            });
        });
    </script>
</@c.page>