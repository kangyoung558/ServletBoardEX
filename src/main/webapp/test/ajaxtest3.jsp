<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <button onclick="idCheck()"> 아이디 있니?</button>
 <div id="box"></div>
 <script>
 	function idCheck() {
 		fetch("http://localhost:8080/servletboard/ajax").then(function(data) {
 			return data.text();
 		}).then(function(data) {
 				alert(data);
 			})
 	}
 </script>
</body>
</html>