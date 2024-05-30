<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>GoT - Add Character</title>
</head>
<body>

<p>House: ${param.house}</p>

<form method="post">
<p>
  Father:
  <select name="father">
    <option></option>
    <c:forEach items="${houses}" var="h">
      <c:forEach items="${h.characters}" var="c">
        <option>${c.name}</option>
      </c:forEach>
    </c:forEach>
  </select>

  Mother:
  <select name="mother">
    <option></option>
    <c:forEach items="${houses}" var="h">
      <c:forEach items="${h.characters}" var="c">
        <option>${c.name}</option>
      </c:forEach>
    </c:forEach>
  </select>
</p>
<p>Name: <input type="text" name="name"> <button>Add</button></p>
</form>

</body>
</html>
