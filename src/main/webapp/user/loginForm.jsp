<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Servlet 게시판</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container-fluid vh-100 bg-light">
		 <div class="vh-100 row align-items-center justify-content-center">
	    	<div class="col-6 justify-content-center">
		    	<div class="container bg-white p-5">
		    		<div class="row mb-3 text-center">
			    		<div class="col">
				    		<h3>로그인</h3>
			    		</div>
		    		</div>
		     	 	<form action="#">
		    		<div class="row mb-3">
			    		<div class="col">
				    		 <div class="form-floating">
					      		<input type="text" class="form-control" id="username" placeholder="아이디를 입력해주세요" name="username">
					      		<label for="username">아이디</label>
					    	</div>
			    		</div>
		    		</div>
		    		<div class="row mb-3">
			    		<div class="col">
						    <div class="form-floating">
						      <input type="text" class="form-control" id="password" placeholder="비밀번호를 입력해주세요" name="password">
						      <label for="password">비밀번호</label>
						    </div>
			    		</div>
		    		</div>
		    		<div class="row mb-3">
			    		<div class="col">
						   	<div class="d-grid">
							 	<button type="submit" class="btn btn-primary btn-block">로그인</button>
							</div>
			    		</div>
		    		</div>
				  	</form>
		    		<div class="row">
			    		<div class="col d-flex justify-content-end">
						   <p>아직 계정이 없으신가요?</p>
                			<a href="#" class="text-decoration-none">회원 가입</a>
			    		</div>
		    		</div>
					   
	    		</div>
	   	 	</div>
		    
	  	</div>
	</div>
</body>
</html>