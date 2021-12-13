<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container mt-3">
	<form action="<%=request.getContextPath()%>/board?cmd=update" method="POST">
		<input type="hidden" name="id" value="${dto.id}"/>
		<div class="form-group">
			<label for="title">Title:</label>
			<input type="text" class="form-control" placeholder="title" id="title" name="title" value="${dto.title}">
		</div>
	
		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control" id="summernote" name="content">${dto.content}</textarea>
		</div>
	
		<button type="submit" class="btn btn-primary">글쓰기 수정</button>
	</form>
</div>

<script>
	$('#summernote').summernote({
        placeholder: '글쓰기',
        tabsize: 2,
        height: 400
      });
    
  </script>


</body>
</html>