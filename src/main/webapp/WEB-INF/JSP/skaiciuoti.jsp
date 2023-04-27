<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Skaičiuoti</title>
    </head>
    <body>
        <h4> ${sk1} ${zenklas} ${sk2} = ${rezultatas} </h4>
    </body>

    <button onclick="goBack()">Go Back</button>

    <script>
    function goBack() {
      window.history.back();
    }
    </script>

</html>
