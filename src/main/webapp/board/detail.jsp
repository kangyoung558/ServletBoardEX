<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<c:if test="${sessionScope.principal.id == dto.userId}">
		<a href="<%=request.getContextPath()%>/board?cmd=updateForm&id=${dto.id}" class="btn btn-warning m-2" >수정</a>
		<button onclick="deleteById(${dto.id})" class="btn btn-danger m-2" >삭제</button>
	</c:if>
	<br />
	<br />
	<h6 class="m-2">
		작성자 : <i>${dto.username}</i> 조회수 : <i>${dto.readCount}</i>
	</h6>
	<br/>
	<h3 class="m-2">
		<b>${dto.title}</b>
	</h3>
	<hr/>
	<div class="form-group">
		<div class="m-2">${dto.content}</div>
	</div>
	<hr/>
	
	<div class="row bootstrap snippets">
		<div class="col-md-12">
			<div class="comment-wrapper">
				<div class="panel panel-info">
					<div class="panel-heading m-2">
						<b>Comment</b>
					</div>
					<div class="panel-body">
						<input type="hidden" name="userId"
							value="" /> <input type="hidden"
							name="boardId" value="" />
						<textarea id="content" id="reply__write__form"
							class="form-control" placeholder="write a comment..." rows="2"></textarea>
						<br>

						<button
							onClick="replySave()"
							class="btn btn-primary pull-right">댓글쓰기</button>

						<div class="clearfix"></div>
						<hr />

						<!-- 댓글 리스트 시작-->
						<ul id="reply__list" class="media-list">
							<!-- 댓글 아이템 -->
							<li id="reply-${reply.id}" class="media">
								<div class="media-body">
									<strong class="text-primary">작성자</strong>
									<p>댓글 내용</p>
								</div>
								<div class="m-2">
									<i onclick="deleteReply()" class="material-icons">delete</i>
								</div>
							</li>
						</ul>
						<!-- 댓글 리스트 끝-->
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- 댓글 박스 끝 -->
</div>

<script>
	function deleteById(boardId) {
		var data = {
			boardId: boardId
		}
		$.ajax({
			type: "post",
			url: "/servletboard/board?cmd=delete",
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType: "json"
		}).done(function(result) {
			if(result.status == "ok") {
				location.href="index.jsp";
			}else {
				alert("삭제에 실패하였습니다.");
			}
		});
	}
    
</script>


</body>
</html>