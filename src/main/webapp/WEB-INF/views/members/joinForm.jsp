<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../home/head.jsp" />

<link href="/css/member.css" rel="stylesheet">
<link  href="/css/default.css" rel="stylesheet">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<div id="register">
	<div class="membership">
		<jsp:include page="membership.jsp" />
	</div>
	<div class="form">
		<form action="/members/join" name="rf" method="post" onsubmit="return register_form_submit(this);">
			<p>
				<span class="title">필수 입력사항</span>
			</p>
			<p>
				<input type="text" id="register_id" name="register_id" class="inp_small" maxlength="20" placeholder="아이디 (영문+숫자 조합 4~20자)" style="ime-mode:disabled" onkeyup="this.value=this.value.replace(/[^a-zA-Z-_0-9]/g,'');" tabindex="1" required="required">
				<input type="button" class="btn_check" onclick="duplicationCheck('id')" value="중복검사">
				<span id="result_id"></span>
			</p>
			<p>
				<input type="password" name="register_password" maxlength="50" placeholder="비밀번호" tabindex="2" required="required">
			</p>
			<p>
				<input type="password" name="register_re_password" maxlength="50" placeholder="비밀번호 확인" tabindex="3" required="required">
			</p>
			<p>
				<input type="text" id="register_nick" name="register_nick" class="inp_small" maxlength="10" placeholder="닉네임 (한글/영문/숫자 2~10자)" tabindex="4" required="required">
				<input type="button" class="btn_check" onclick="duplicationCheck('nick')" value="중복검사">
				<span id="result_nick"></span>
			</p>
			<p>
				<input type="text" name="register_email" maxlength="100" placeholder="이메일" tabindex="5" required="required">
			</p>
			<p>
				<span class="title">선택 입력사항</span>
			</p>
			<p>
				<input type="text" style="cursor:pointer;" id="zip" class="inp_small" name="register_zip" maxlength="7" placeholder="우편번호" onclick="zipSearch()" tabindex="6" readonly="readonly">
				<input type="button" class="btn_search" value="검색" onclick="zipSearch()">
			</p>
			<p>
				<input type="text" style="cursor:pointer;" id="addr1" name="register_address1" maxlength="100" placeholder="주소" onclick="zipSearch()" tabindex="7" readonly="readonly">
			</p>
			<p>
				<input type="text" id="addr2" name="register_address2" maxlength="100" placeholder="상세주소" tabindex="8">
			</p>
			<p>
				<input type="text" name="register_phone" maxlength="15" placeholder="연락처 번호" tabindex="9">
			</p>
			<p>
				<input type="submit" class="btn_submit" value="좌측 회원가입 약관을 모두 읽어 동의하였으며 회원가입을 진행하겠습니다.">
			</p>
		</form>
		
		<script type="text/javascript">
			var checkID = false;
			var checkNick = false;
		
			function duplicationCheck(type) {
				if(type == 'id') {
					// 첫 글자 영문자, 두번째부터 영문자, 숫자 조합 4~20자
					var regex = /^[A-Za-z][A-Za-z0-9]{3,20}$/g;
					
					if(!rf.register_id.value) {
						document.getElementById("result_id").innerHTML = "아이디를 입력해주세요.";
						document.getElementById("result_id").style.color = '#fc5230';
						rf.register_id.focus();
						return false;
					} else if(!regex.exec(rf.register_id.value)) {
						document.getElementById("result_id").innerHTML = "아이디는 첫 글자는 영문자로, 두번째 부터 영문자, 숫자를 조합한 4~20자 까지 사용 가능합니다.";
						document.getElementById("result_id").style.color = '#fc5230';
						rf.register_id.focus();
						return false;
					} else {
						var register_id = $('#register_id').val();
						
						$.ajax({
							url: '../member/duplicate?register_id=' + register_id,
							type: 'get',
							success: function(data) {
								if(data == 1) {
									document.getElementById("result_id").innerHTML = "누군가 이미 사용중인 아이디입니다 :(";
									document.getElementById("result_id").style.color = '#fc5230';
									rf.register_id.focus();
								} else {
									document.getElementById("result_id").innerHTML = "사용 가능한 아이디입니다 :)";
									document.getElementById("result_id").style.color = '#3883c9';
									rf.register_id.focus();
									checkID = true;
								}
							}, error: function() {
								alert("서버가 불안정하거나 연결되어 있지 않아 통신을 할 수 없습니다.");
							}
						});
					}
				} else if(type == 'nick') {
					// 특수문자 제외 한글/영문자/숫자 조합 2~10자
					var regex = /^[A-Za-z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/;
					
					if(!rf.register_nick.value) {
						document.getElementById("result_nick").innerHTML = "닉네임을 입력해주세요.";
						document.getElementById("result_nick").style.color = '#fc5230';
						rf.register_nick.focus();
						return false;
					} else if(!regex.exec(rf.register_nick.value)) {
						document.getElementById("result_nick").innerHTML = "닉네임은 한글/영문자/숫자로만 2~10자 까지 사용 가능합니다.";
						document.getElementById("result_nick").style.color = '#fc5230';
						rf.register_nick.focus();
						return false;
					} else {
						var register_nick = $('#register_nick').val();
						
						$.ajax({
							url: '../member/duplicationCheckPro.me?register_nick=' + register_nick,
							type: 'get',
							success: function(data) {
								if(data == 1) {
									document.getElementById("result_nick").innerHTML = "누군가 이미 사용중인 닉네임입니다 :(";
									document.getElementById("result_nick").style.color = '#fc5230';
									rf.register_id.focus();
								} else {
									document.getElementById("result_nick").innerHTML = "잘 어울리는 닉네임이네요 :)";
									document.getElementById("result_nick").style.color = '#3883c9';
									rf.register_id.focus();
									checkNick = true;
								}
							}, error: function() {
								alert("서버가 불안정하거나 연결되어 있지 않아 통신을 할 수 없습니다.");
							}
						});
					}
				}
			}
			
			function register_form_submit() {
				var regex_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
				var regex_phone = /^\d{3}-\d{3,4}-\d{4}$/;
				
				if(!checkID) {
					alert("아이디 중복검사를 통해 사용 가능한 아이디를 입력해주세요.");
					rf.register_id.focus();
					return false;
				} else if(!rf.register_password.value) {
					alert("비밀번호를 입력해주세요.");
					rf.register_password.focus();
					return false;
				} else if(rf.register_password.value.length < 6) {
					alert("비밀번호는 최소 6자 이상 입력해주세요.");
					rf.register_password.focus();
					return false;
				} else if(rf.register_password.value !== rf.register_re_password.value) {
					alert("비밀번호가 일치하지 않습니다.");
					rf.register_re_password.focus();
					return false;
				} else if(!checkNick) {
					alert("닉네임 중복검사를 통해 사용 가능한 닉네임을 입력해주세요.");
					rf.register_nick.focus();
					return false;
				} else if(!rf.register_email.value) {
					alert("이메일을 입력해주세요.");
					rf.register_email.focus();
					return false;
				} else if(!regex_email.exec(rf.register_email.value)) {
					alert("이메일 형식이 올바르지 않습니다.");
					rf.register_email.focus();
					return false;
				} else if(rf.register_phone.value.length > 0 && !regex_phone.exec(rf.register_phone.value)) {
					alert("연락처 형식이 올바르지 않습니다.");
					rf.register_phone.focus();
					return false;
				}
	
				return true;
			}
		
			function zipSearch() {
				new daum.Postcode({
			        oncomplete: function(data) {
		                var addr = ''; // 주소
		                var extraAddr = ''; // 참고항목
	
		                if (data.userSelectedType === 'R') { // 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
	
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가 (법정리 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝남
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
	
		                    extraAddr = extraAddr;
		                } else {
		                	extraAddr = '';
		                }
	
		                document.getElementById('zip').value = data.zonecode;
		                document.getElementById("addr1").value = addr + extraAddr;
		                document.getElementById("addr2").focus();
	 		        }
	 		    }).open();
			}
		</script>
	</div>
</div>

<jsp:include page="../home/tail.jsp" />
