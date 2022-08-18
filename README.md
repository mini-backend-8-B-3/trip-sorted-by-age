# 욜로GO

One Paragraph of project description goes here

### 개발기간
2022.08.12-2022.08.18

## 팀원소개

- BE: 안승현, 양승인, 하지혜
- FE: 김은경, 박재정, 박준기

## 기술스택

### 백엔드
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat&logo=Spring Security&logoColor=white"/> <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white"/>  <img src="https://img.shields.io/badge/JWT-000000?style=flat&logo=JWT&logoColor=white"/> <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Gradle&logoColor=white"/> 

### 프론트엔드
 [프론트엔드 깃허브]( https://github.com/byjgpark/W6_Mini_Project_Team3)
<div>
<img src="https://img.shields.io/badge/axios-1877F2?style=flat&logo=ssockjs&logoColor=white">
<img src="https://img.shields.io/badge/sockjs-1877F2?style=flat&logo=ssockjs&logoColor=white">
<img src="https://img.shields.io/badge/stomp-1877F2?style=flat&logo=stomp&logoColor=white">
<img src="https://img.shields.io/badge/ApexChart-1877F2?style=flat&logo=ApexChart&logoColor=white"><br/>
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <br>
<img src="https://img.shields.io/badge/styled components-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=pink">
<img src="https://img.shields.io/badge/react query-61DAFB?style=for-the-badge&logo=reactquery&logoColor=FF4154">
<img src="https://img.shields.io/badge/recoil-F7A81B?style=for-the-badge&logo=route53&logoColor=white"><br>
<img src="https://img.shields.io/badge/amazon s3-569A31?style=for-the-badge&logo=amazons3&logoColor=green">
<img src="https://img.shields.io/badge/github actions-2088FF?style=for-the-badge&logo=github actions&logoColor=white">
<img src="https://img.shields.io/badge/cloudfront-04ACE6?style=for-the-badge&logo=cloudfront&logoColor=white">
<img src="https://img.shields.io/badge/route 53-F7A81B?style=for-the-badge&logo=route53&logoColor=white">
<div/>
  

## 핵심기능

```
1. 회원가입/로그인
JWT 인증 방식으로 로그인 구현

2. ID, 닉네임 중복확인, 각 필드별 유효성체크

3. 게시글 CRUD
게시글 목록 조회, 등록, 수정, 삭제, 상세조회

4. 댓글 CRUD
게시글 별 댓글 조회, 수정, 삭제
  
5.이미지 업로드
```

  ## 데모영상


## 트러블 슈팅
```
**1. CORS 설정** 
- WEBCONFIG 설정을 통하여 CORS설정. 
  - 불안전 요청 처리를 위해 AllowedMethod 재설정
```  
  
```
**2. JPA N+1 쿼리 성능개선** 
  - @Query 문을 사용하여 Entity를 fetch join 사용하여 연결, 쿼리 한번으로 카드 조회하도록 함. 
  
```
   
```
**3. payload 설정 에러** 
  - 받아온 값에서 id번호를 추출하여 각 URI마다 카드 ID와 댓글 ID를 구별하여 넣어주어야 하는데 
    잘못된 payload가 입력되어 CORS오류로 이어짐. 
  
```
  
```
**4. JWT Refresh Token 보류** 
  - 예제에 사용된JWT 방식이 Refresh토큰으로 권한을 확인하는 방식이어서, 
    AccessToken으로 권한을 확인하고 Refresh토큰은 저장을 해두는 방식으로 수정. 

```
  
```
**5. 배포시 파일 업로드 경로 오류, 권한 설정 오류** 
  - 권한설정오류: 다른 주소에서 파일을 업로드 할 시 배포한 사람 외에 파일 업로드가 실패하는 현상, 
                  혹은 경로가 잘못되어 파일이 올라가지 않는 오류. 
  -> 파일 경로를 "image/"로 바꿔 jar파일이 있는 곳에 "image"폴더를 생성하고, 
     EC2 권한을 모든 포트에서 가능하도록 지역과 포트 설정을 하여 해결. 
```
  
  



