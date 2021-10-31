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

### 기능구현목록  

### [회원관리]  

1. 회원가입    
    1. 중복 닉네임, 아이디, 이메일 검증  
2. 로그인     
    1. (예외) 일치하지 않는 비밀번호 입력   
3. 로그아웃    
4. 회원정보 수정    
    1. (예외) 중복 닉네임 입력  

### [상품관리]  

1. 상품 등록  
2. 상품 조회  
3. 상품 목록  
    페이징
4. 상품 수정 기능  
    1. 마감 일시 수정 불가능  
    2. 상품등록한 회원만 수정가능
5. 상품 삭제
    1. 상품등록한 회원만 삭제가능
    

### [경매기능]  

1. 입찰 기능  
    1. 입찰 금액 < 낙찰금액일 경우  
        1. 입찰액 증가  
        2. 입찰자(참여자 수) 증가  
    2. 입찰 금액 ≥ 낙찰금액일 경우  
        1. 즉시 낙찰후 경매 종료  
2. 경매 종료 시간 상품 등록시 설정한 마감일까지 1초씩 감소하는 기능  
    1. 경매 시간 종료시 입찰자 있는 경우  
        1. 마지막 입찰자 회원에게 낙찰  
    2. 경매 시간 종료시 입찰자 있는 경우  
        1. 유찰 처리  


### [추가]    
1. 관리자 권한  
    spring security  
2. 중복닉네임, 이메일, 아이디 검증    
    validation  
3. 상품등록  
    이미지 파일 등록  
