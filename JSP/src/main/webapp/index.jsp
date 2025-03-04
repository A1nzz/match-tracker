<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dota 2 Tournament App</title>
    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #1a1a1a, #333333);
            font-family: 'Arial', sans-serif;
            color: #ffffff;
            text-align: center;
        }

        h1 {
            font-size: 3em;
            margin-bottom: 20px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }



        .button {
            padding: 15px 30px;
            font-size: 1.2em;
            color: #ffffff;
            background: rgba(255, 0, 0, 0.8); /* Красный фон */
            border: none;
            border-radius: 25px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
            text-decoration: none;

        }

        .button:hover {
            background-color: rgba(255, 0, 0, 1); /* Яркий красный при наведении */
            transform: scale(1.05);
        }

        .button:active {
            transform: scale(0.95);
        }
    </style>
</head>
<body>
<h1>Добро пожаловать в Match Tracker</h1>
<a href="${pageContext.request.contextPath}/tournaments" class="button">Go to Tournaments</a>

</body>
</html>