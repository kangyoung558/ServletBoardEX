<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "../layout/header.jsp" %>

<div class="container">
	<form action="/servletboard/user?cmd=join" method="post" onsubmit="return valid()" novalidate>
	  <div class="mb-3 mt-3 text-center">
	  <h2>회원가입</h2>
	  </div>
	  
	  <div class="row mb-3" >
	    <div class="col-sm-10">
	    	<input type="text" class="form-control"  placeholder="아이디를 입력 해주세요" id="username" name="username" required/>
	    </div>
	    <div class="col-sm-2">
		  	<button type="button" class="btn btn-info" onClick="checkUsername()">중복 체크</button>
	    </div>
	  </div>
	  
	  
	  <div class="mb-3">
	    <input type="password" class="form-control"  placeholder="비밀번호를 입력해주세요" name="password" required/>
	  </div>
	  
	  <div class="mb-3">
	    <input type="email" class="form-control"  placeholder="이메일을 입력해주세요" name="email" required/>
	  </div>
	  <div class="row mb-3" >
	    <div class="col-sm-10">
		    <input type="text" class="form-control"  placeholder="주소를 입력해주세요" id="address"  name="address" required readonly/>
	    </div>
	    <div class="col-sm-2">
		  	<button type="button" class="btn btn-info" onClick="goPopup()" value="팝업_domainChk">주소 검색</button>
	    </div>
	  </div>

	  <button type="submit" id="joinSubmitBtn" disabled class="btn btn-primary">회원가입</button>
	</form>
</div>

<script>
	
	var isChecking = false;
	
	function valid() {
		return isChecking;
	}
	
	function checkUsername() {
		//DB에서 확인해서 정상이면 isChecking = true
		var username =  $("#username").val();
		
 		$.ajax({
			type:"POST",
			url:"/servletboard/user?cmd=checkUsername",
			data:username,
			contentType:"text/plain; charset=utf-8",
			dataType:"text" //응답 받을 데이터의 타입 
		}).done(function(data) {
			if(data === 'ok') {
				isChecking = false;
				alert("id가 중복되었습니다.");
			}else {
				isChecking = true;
				$("#joinSubmitBtn").attr("disabled", false);
				alert("해당 id를 사용할 수 있습니다.");
				
			}
		}); 
	}
	



	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/servletboard/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}
	
	function jusoCallBack(roadFullAddr){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			var address = document.querySelector("#address");
			address.value = roadFullAddr;
			
	}

</script>

</body>
</html>