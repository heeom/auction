## Description <br>
옥션사이트를 벤치 마킹한 중고 물품 경매 사이트  

기존의 웹프로젝트를 springMVC로 리팩토링하는 프로젝트입니다.   

**기존 프로젝트를 리팩토링하는 이유?**  

springMVC에 대해 공부한 내용을 프로젝트에 적용해 보고 싶어서 servlet으로 구현한 팀프로젝트를 springmvc로 바꾸고,       
또, 일단 돌아가게만 구현한 예전 내 코드들을 클린코드로 수정하는 데에 중점을 두고 싶어서 기존에 개발했던 프로젝트를 리팩토링하기로 했습니다.    


프로젝트 진행 과정과 학습 내용 기록하는 블로그  
https://devswim.tistory.com/    


## Environment  

Spring Boot 2.5.6  
gradle  
Java 11   
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
    1. (예외처리) 일치하지 않는 비밀번호 입력   
3. 로그아웃    

### [상품관리]  

1. 상품 등록  
2. 상품 조회  
3. 상품 목록  
    페이징

    

### [경매기능]  

1. 입찰 기능, 즉시 낙찰    
    1. 입찰 금액 < 낙찰금액일 경우  
        1. 입찰액 증가    
    2. 입찰 금액 ≥ 낙찰금액일 경우  
        1. 즉시 낙찰후 경매 종료
        2. 입찰 불가능  
2. 경매 마감시간 <= 현재시간 -> 경매 종료  
    1. 경매 시간 종료시 입찰자 있는 경우  
        1. 마지막 입찰자 회원에게 낙찰  
    2. 경매 시간 종료시 입찰자 없는 경우  
        1. 유찰 처리  
3. 마감된 입찰 불가능  



### [추가]    
0. spring JDBCTemplate -> JPA
1. 로그인 인증  
    spring security + jwt
2. 중복닉네임, 이메일, 아이디 검증    
    validation  
3. 상품등록  
    이미지 파일 등록 
