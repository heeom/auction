## Description <br>
옥션사이트를 벤치 마킹한 중고 물품 경매 사이트  

기존의 웹프로젝트를 springMVC로 리팩토링하는 프로젝트이다.  

**기존 프로젝트를 리팩토링하는 이유?**  

springMVC에 대해 공부한 내용을 프로젝트에 적용해 보고 싶어서 servlet으로 구현한 팀프로젝트를 springmvc로 리팩토링 하려고 한다.   
또, 일단 돌아가게만 구현한 예전 내 코드들을 좀 더 효율적인 방법으로 수정하는 데에 중점을 두고 싶어서 기존에 개발했던 프로젝트를 리팩토링하기로 했다.  


프로젝트 진행 과정과 학습 내용은 블로그에 상세하게 기록하는 걸 목표로 한다!  
https://devswim.tistory.com/  


## Environment  

Spring Boot 2.5.6  
gradle  
Java 11  
Packaging: War  
lombok  
Jsp  
Spring JDBC Template, MySQL  
Intellij  

## TO-DO-LIST

기능구현목록  

회원관리  

1. 로그인, 로그아웃  
    1. (예외처리) 일치하지 않는 비밀번호 입력  
2. 회원가입  
    1. 중복아이디, 이메일, 닉네임 검증  
    2. 이메일 인증    
3. 회원정보 수정 : id로 회원정보 조회  
    1. (예외처리) 중복닉네임 입력  
4. 관리자페이지  
    1. 특정회원조회 : id로 회원정보 조회  
    2. 회원목록조회 : 회원 전체 목록 조회  
