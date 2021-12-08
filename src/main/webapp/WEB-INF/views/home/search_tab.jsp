<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(document).ready(function(){
		$('.search_tab').fadeIn(400).addClass('show');

		$('.del').click(function(){
			$.ajax('a.sdl',{
				success:function(rdata){

					$('.t1>span').html(rdata+"　");
				}
			});
		});



		$('.pk').click(function(){
			$.ajax('test.sr',{

				success:function(rdata){
					$('.t2').html(rdata);

				}
			});
		});

		$('.close').click(function(){
			var more_menu = $('.header_more_menu'),
				search_tab = $('.search_tab'),
				login_tab = $('.login_tab');

			if(more_menu.hasClass('show') === true) {
				more_menu.animate({top:-350}, 300).removeClass('show');
			} else if(search_tab.hasClass('show') === true) {
				search_tab.fadeOut(100).removeClass('show');
			} else if(login_tab.hasClass('show') === true) {
				login_tab.fadeOut(100).removeClass('show');
			}

			$('.back_drop').css('display', 'none');
		});



	});

	$(function(){
		$('.tab li').click(function(){
			var tabType = $(this).index();

			$('.wrap > div').eq(tabType).show().siblings('div').hide();
		});
	});

</script>
<%
String k1 = "　",k2 = "　", k3 = "　", k4 = "　", k5 = "　", k6 = "　", k7 = "　", k8 = "　", k9 = "　", k10 = "　";
Cookie[] cookies = request.getCookies();



int find = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword1")){
		   find = i;
		   break;
		  }
}
if(find != -1){
	 k1 = URLDecoder.decode(cookies[find].getValue(),"UTF-8");

// 	out.println("1. "+ URLDecoder.decode(cookies[find].getValue(), "UTF-8")+"<br>");
}


int find2 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword2")){
		   find2 = i;
		   break;
		  }
}
if(find2 != -1){
	k2 = URLDecoder.decode(cookies[find2].getValue(),"UTF-8");
// 	out.println("2. "+ URLDecoder.decode(cookies[find2].getValue(), "UTF-8")+"<br>");
}

int find3 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword3")){
		   find3 = i;
		   break;
		  }
}
if(find3 != -1){
	k3 = URLDecoder.decode(cookies[find3].getValue(),"UTF-8");
// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}
int find4 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword4")){
		   find4 = i;
		   break;
		  }
}
if(find4 != -1){
	k4 = URLDecoder.decode(cookies[find4].getValue(),"UTF-8");
// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}
int find5 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword5")){
		   find5 = i;
		   break;
		  }
}
if(find5 != -1){
	k5 = URLDecoder.decode(cookies[find5].getValue(),"UTF-8");
// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}
int find6 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword6")){
		   find6 = i;
		   break;
		  }
}
if(find6 != -1){
	k6 = URLDecoder.decode(cookies[find6].getValue(),"UTF-8");
// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}
int find7 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword7")){
		   find7 = i;
		   break;
		  }
}
if(find7 != -1){
	k7 = URLDecoder.decode(cookies[find7].getValue(),"UTF-8");
// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}
int find8 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword8")){
		   find8 = i;
		   break;
		  }
}
if(find8 != -1){
	k8 = URLDecoder.decode(cookies[find8].getValue(),"UTF-8");
// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}
int find9 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword9")){
		   find9 = i;
		   break;
		  }
}
if(find9 != -1){
	k9 = URLDecoder.decode(cookies[find9].getValue(),"UTF-8");
// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}
int find10 = -1;
for(int i=0; i<cookies.length; i++){
	 if(cookies[i].getName().equals("keyword10")){
		   find10 = i;
		   break;
		  }
}
if(find10 != -1){
	k10 = URLDecoder.decode(cookies[find10].getValue(),"UTF-8");

// 	out.println("3. "+ URLDecoder.decode(cookies[find3].getValue(), "UTF-8")+"<br>");
}

// for(Cookie c: cookies){
//     out.print(c.getName()+",");
// 	out.println(URLDecoder.decode(c.getValue(),"UTF-8")+"<br>");
// }


%>

<div class="search_tab">
	<div class="keyword_tab_show">
		<ul class="tab">
			<li>최근</li>
			<li><a href="#" class="pk">인기</a></li>
		</ul>

		<div class="wrap">
			<div class="t1">
				<span><a href="searchPro.sr?keyword=<%=k1 %>"><%=k1 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k2 %>"><%=k2 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k3 %>"><%=k3 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k4 %>"><%=k4 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k5 %>"><%=k5 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k6 %>"><%=k6 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k7 %>"><%=k7 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k8 %>"><%=k8 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k9 %>"><%=k9 %></a></span>
				<span><a href="searchPro.sr?keyword=<%=k10 %>"><%=k10 %></a></span>
				<ul>
					<li class="del"><img src="../img/trash.png"></img><a href="#" class="del">전체삭제</a></li>
					<li class="close"><a href="#">닫기</a></li>
				</ul>
			</div>
			<div class="pklist">
				<div class="t2">
				<span>1.인기버튼을 다시 클릭해 주세요.</span>
				<span>2.인기버튼을 다시 클릭해 주세요.</span>
				<span>3.인기버튼을 다시 클릭해 주세요.</span>
				<span>4.인기버튼을 다시 클릭해 주세요.</span>
				<span>5.인기버튼을 다시 클릭해 주세요.</span>
				<span>6.인기버튼을 다시 클릭해 주세요.</span>
				<span>7.인기버튼을 다시 클릭해 주세요.</span>
				<span>8.인기버튼을 다시 클릭해 주세요.</span>
				<span>9.인기버튼을 다시 클릭해 주세요.</span>
				<span>10.인기버튼을 다시 클릭해 주세요.</span>
				<ul>
					<li></li>
					<li class="close"><a href="#">닫기</a></li>
				</ul>

				</div>

			</div>
		</div>
	</div>
</div>
