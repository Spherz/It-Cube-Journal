<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>

<@c.page>
    <form>
        Направления: <select class="form-select form-select-lg mb-4" id="coursesDropDown" aria-label=".form-select-lg example">
            <option>Выберите направление</option>
            <#list courses as course>
                <option value="${course.id}">${course.courseName}</option>
            </#list>
        </select>
        Students: <select class="form-select form-select-lg mb-4" id="studentsDropDown" aria-label=".form-select-lg example"></select>
    </form>
    <script>
        $(document).ready(function () {
            $('#coursesDropDown').change(function () {
                let courseId = $(this).val();
                $.ajax({
                    type: "GET",
                    url: '/courses/' + courseId,
                    success: function (data) {
                        let result = JSON.parse(data);
                        console.log(result);
                        let s = '';
                        for(let i = 0; i < data.length; i++) {
                            s+= '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                        }
                        $('#studentsDropDown').html(s);
                    }
                });
            });
        });
    </script>
</@c.page>