<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <p>Say <a href="hello">Hello</a></p>
  <%="Hello Word"%>
  <form method="post" action="hello">
    <h2>Name:</h2>
    <input type="text" id="say-hello-text-input" name="name" />
    <input type="submit" id="say-hello-button" value="Say Hello" />
  </form>
  </body>
</html>
