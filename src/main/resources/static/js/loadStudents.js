jQuery(document).ready(function () {

    jQuery(document).off('click', '.attendance-cells').on('click', '.attendance-cells', function () {
       let cell = jQuery(this);
       let studentId = cell.data('student-id');
       let attendanceDate = cell.data('date');

       let newMark = cell.text().trim() === '' ? 'Н' : '';
       cell.text(newMark);

       let token = $("input[name='_csrf']").val();

        jQuery.ajax({
           url: '/attendance/update',
           type: 'POST',
           data: JSON.stringify({
               studentId: studentId,
               attendanceDate: attendanceDate,
               mark: newMark
           }),
           contentType: 'application/json',
           beforeSend: function (xhr) {
               xhr.setRequestHeader('X-CSRF-TOKEN', token);
           },
           success: function (response) {
               console.log('Отметка успешно обновлена');
           },
           error: function () {
               console.error("Ошибка при обновлении отметки")
           }
       })
    });
});
