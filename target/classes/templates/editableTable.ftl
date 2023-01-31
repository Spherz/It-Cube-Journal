<! DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title> JavaScript editable table </title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<style>
    @import url('https://fonts.googleapis.com/css?family=Montserrat:400,500');
    body {
        font-family: 'Montserrat', sans-serif;
        padding: 0;
        margin: 0;
        text-align: center;
    }
    h1 {
        position: relative;
        padding: 0;
        margin: 10;
        font-family: "Raleway", sans-serif;
        font-weight: 300;
        font-size: 40px;
        color: #080808;
        -webkit-transition: all 0.4s ease 0s;
        -o-transition: all 0.4s ease 0s;
        transition: all 0.4s ease 0s;
    }
    body {
        font-size: 0.75em;
    }
    table {
        border-spacing: 10px;
    }
    tr > th {
        text-align: right;
    }
    .table-fill {
        background: white;
        border-radius:3px;
        border-collapse: collapse;
        height: 320px;
        margin: auto;
        max-width: 600px;
        padding:5px;
        width: 100%;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        animation: float 5s infinite;
    }
    th {
        color: #D5DDE5;;
        background: #1b1e24;
        border-bottom: 4px solid #9ea7af;
        border-right: 1px solid #343a45;
        font-size: 23px;
        font-weight: 100;
        padding: 24px;
        text-align: left;
        text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
        vertical-align: middle;
    }
    th:first-child {
        border-top-left-radius: 3px;
    }
    th:last-child {
        border-top-right-radius: 3px;
        border-right: none;
    }
    tr {
        border-top: 1px solid #C1C3D1;
        border-bottom-: 1px solid #C1C3D1;
        color: #666B85;
        font-size: 16px;
        font-weight: normal;
        text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
    }
    tr:hover td {
        background: #4E5066;
        color: #FFFFFF;
        border-top: 1px solid #22262e;
    }
    tr:first-child {
        border-top: none;
    }
    tr:last-child {
        border-bottom: none;
    }
    tr:nth-child(odd) td {
        background: #EBEBEB;
    }
    tr:nth-child(odd):hover td {
        background: #4E5066;
    }
    tr:last-child td:first-child {
        border-bottom-left-radius: 3px;
    }
    tr:last-child td:last-child {
        border-bottom-right-radius: 3px;
    }
    td:last-child {
        border-right: 0px;
    }
    td {
        background: #FFFFFF;
        padding: 20px;
        text-align: left;
        vertical-align: middle;
        font-weight: 300;
        font-size: 18px;
        text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
        border-right: 1px solid #C1C3D1;
    }
</style>
<body>
<h1> Example </h1>
<table summary="Editable table with datasets ordered in columns" class="table-fill">
    <tbody>
    <tr>
        <th scope="col"> Month </th>
        <th scope="col"> Sales </th>
    </tr>
    <tr>
        <th scope="row"> January </th>
        <td> 16000 </td>
    </tr>
    <tr>
        <th scope="row"> February </th>
        <td> 10000</td>
    </tr>
    <tr>
        <th scope="row"> March </th>
        <td> 20000 </td>
    </tr>
    <tr>
        <th scope="row"> April </th>
        <td> 7300 </td>
    </tr>
    <tr>
        <th scope="row"> May </th>
        <td> 12000 </td>
    </tr>
    <tr>
        <th scope="row"> June </th>
        <td> 1099 </td>
    </tr>
    </tbody>
</table>
<br />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"> </script>
<script>
    var table = document.querySelector('table');
    var dataCells = table.querySelectorAll('tr > td');
    var rows = table.querySelectorAll('tr');
    var code = document.querySelector('code');
    var resetButton = document.querySelector('.reset');
    rows = Array.prototype.slice.call(rows, 1);
    var ncols = rows[0].children.length - 1;
    var initialData = {0:[1,2,3,4,5],1:[6,7,8,9,10],2:[11,12,13,14,15]};
    function parseTable () {
        var d = {};
        Array.prototype.forEach.call(rows, function (row, i) {
            var rowrowCells = row.querySelectorAll('td');
            return Array.prototype.map.call(rowCells, function (cell, j) {
                if (!d[j]) d[j] = [];
                d[j].push(parseInt(cell.textContent, 10));
            });
        });
        return d;
    }
    function setTableData (data) {
        Array.prototype.forEach.call(rows, function (row, i) {
            var rowrowCells = row.querySelectorAll('td');
            return Array.prototype.map.call(rowCells, function (cell, j) {
                cell.textContent = initialData[j][i];
            });
        });
    }
    Array.prototype.forEach.call(dataCells, function (cell) {
        cell.contentEditable = true;
    });
    table.addEventListener('keyup', function (e) {
        if (e.target.tagName === 'TD') {
            setTimeout(function () {
                code.innerText = JSON.stringify( parseTable() );
            }, 0);
        }
    });
</script>
</body>
</html>