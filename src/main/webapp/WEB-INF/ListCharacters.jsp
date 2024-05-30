<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>GoT - Characters</title>
</head>
<body>

<form method="get">
<p>
  <select name="house">
	<c:forEach items="${houses}" var="h">
      <option <c:if test="${h.name == house.name}">selected</c:if>>${h.name}</option>
	</c:forEach>
  </select>
  <button>Select</button>
  <a href="AddHouse">Add House</a> |
  <a href="AddCharacter?house=${house.name}">Add Character</a>
</p>
</form>

<table border="1" cellpadding="5" cellspacing="2">
  <thead>
    <tr><th>Name</th><th>Father</th><th>Mother</th></tr>
  </thead>
  <tbody>
    <c:forEach items="${house.characters}" var="c">
      <tr>
        <td>${c.name}</td>
        <td>${c.father}</td>
        <td>${c.mother}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>