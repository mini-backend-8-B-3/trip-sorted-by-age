# ๐๏ธ์๋กGO

์ง๊ธ ์ด ์๊ฐ ๊ฐ์ฅ ์ฆ๊ฑฐ์ด ์๊ฐ์ ์ ์ฅํ๋ค.
์ฌํ๋ ์ซ์์ ๋ถ๊ณผํด!
์ฌํํGO, ์ถ์ฒํGO, ๊ฐ๋ณด์GO!

**๊ฐ๋ฐ๊ธฐ๊ฐ**
2022.08.12-2022.08.18


## ๐ฅํ์์๊ฐ

- BE: ์์นํ, ์์น์ธ, ํ์งํ
- FE: ๊น์๊ฒฝ, ๋ฐ์ฌ์ , ๋ฐ์ค๊ธฐ
<br>

## โ๏ธ๊ธฐ์ ์คํ

* **๋ฐฑ์๋**
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat&logo=Spring Security&logoColor=white"/> <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white"/>  <img src="https://img.shields.io/badge/JWT-000000?style=flat&logo=JWT&logoColor=white"/> <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=Gradle&logoColor=white"/> <img src="https://img.shields.io/badge/amazon s3-569A31?flat&logo=Gradle&logo=amazons3&logoColor=green">

* **ํ๋ก ํธ์๋**
<img src="https://img.shields.io/badge/html5-E34F26?style=flat&logo=Gradle&logo=html5&logoColor=white"/> <img src="https://img.shields.io/badge/css-1572B6?style=flat&logo=css3&logo=Gradle&logoColor=white"/> <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat&logo=Gradle&logo=javascript&logoColor=black"/> <img src="https://img.shields.io/badge/react-61DAFB?style=flat&logo=react&logo=Gradle&logoColor=black"/> <img src="https://img.shields.io/badge/styled components-DB7093?style=flat&logo=Gradle&logo=styledcomponents&logoColor=pink"/> <img src="https://img.shields.io/badge/react query-61DAFB?style=flat&logo=Gradle&logo=reactquery&logoColor=FF4154"/> <img src="https://img.shields.io/badge/amazon s3-569A31?style=flat&logo=amazons3&logoColor=green">

  [ํ๋ก ํธ์๋ ๊นํ๋ธ๋ก ์ด๋ํ๊ธฐ]( https://github.com/byjgpark/W6_Mini_Project_Team3)

  

## ๐ํต์ฌ๊ธฐ๋ฅ๐

```
1. ํ์๊ฐ์/๋ก๊ทธ์ธ
JWT ์ธ์ฆ ๋ฐฉ์์ผ๋ก ๋ก๊ทธ์ธ ๊ตฌํ

2. ID, ๋๋ค์ ์ค๋ณตํ์ธ, ๊ฐ ํ๋๋ณ ์ ํจ์ฑ์ฒดํฌ

3. ๊ฒ์๊ธ CRUD
๊ฒ์๊ธ ๋ชฉ๋ก ์กฐํ, ๋ฑ๋ก, ์์ , ์ญ์ , ์์ธ์กฐํ

4. ๋๊ธ CRUD
๊ฒ์๊ธ ๋ณ ๋๊ธ ์กฐํ, ์ญ์ 
  
5.์ด๋ฏธ์ง ์๋ก๋

์ฌ์ฉํ์ง ์๋ ๊ธฐ๋ฅ: ๋๋๊ธ (branch: recomment-basic)
```

## ๐ฅ๋ฐ๋ชจ์์
https://youtu.be/gQNNWwZIluk


## ๐ํธ๋ฌ๋ธ ์ํ โน๏ธ

**1. CORS ์ค์ ** 
```sh
- WEBCONFIG ์ค์ ์ ํตํ์ฌ CORS์ค์ . 
  - ๋ถ์์  ์์ฒญ ์ฒ๋ฆฌ๋ฅผ ์ํด AllowedMethod ์ฌ์ค์ 
```  


**2. JPA N+1 ์ฟผ๋ฆฌ ์ฑ๋ฅ๊ฐ์ ** 
```sh
  - @Query ๋ฌธ์ ์ฌ์ฉํ์ฌ Entity๋ฅผ fetch join ์ฌ์ฉํ์ฌ ์ฐ๊ฒฐ, ์ฟผ๋ฆฌ ํ๋ฒ์ผ๋ก ์นด๋ ์กฐํํ๋๋ก ํจ. 
  
```
   
   
**3. payload ์ค์  ์๋ฌ** 
```sh
  - ๋ฐ์์จ ๊ฐ์์ id๋ฒํธ๋ฅผ ์ถ์ถํ์ฌ ๊ฐ URI๋ง๋ค ์นด๋ ID์ ๋๊ธ ID๋ฅผ ๊ตฌ๋ณํ์ฌ ๋ฃ์ด์ฃผ์ด์ผ ํ๋๋ฐ 
    ์๋ชป๋ payload๊ฐ ์๋ ฅ๋์ด CORS์ค๋ฅ๋ก ์ด์ด์ง. 
  
```


**4. JWT Refresh Token ๋ณด๋ฅ** 
```sh
  - ์์ ์ ์ฌ์ฉ๋JWT ๋ฐฉ์์ด Refreshํ ํฐ์ผ๋ก ๊ถํ์ ํ์ธํ๋ ๋ฐฉ์์ด์ด์, 
    AccessToken์ผ๋ก ๊ถํ์ ํ์ธํ๊ณ  Refreshํ ํฐ์ ์ ์ฅ์ ํด๋๋ ๋ฐฉ์์ผ๋ก ์์ . 

```


**5. ๋ฐฐํฌ์ ํ์ผ ์๋ก๋ ๊ฒฝ๋ก ์ค๋ฅ, ๊ถํ ์ค์  ์ค๋ฅ** 
```sh
  - ๊ถํ์ค์ ์ค๋ฅ: ๋ค๋ฅธ ์ฃผ์์์ ํ์ผ์ ์๋ก๋ ํ  ์ ๋ฐฐํฌํ ์ฌ๋ ์ธ์ ํ์ผ ์๋ก๋๊ฐ ์คํจํ๋ ํ์, 
                  ํน์ ๊ฒฝ๋ก๊ฐ ์๋ชป๋์ด ํ์ผ์ด ์ฌ๋ผ๊ฐ์ง ์๋ ์ค๋ฅ. 
  -> ํ์ผ ๊ฒฝ๋ก๋ฅผ "image/"๋ก ๋ฐ๊ฟ jarํ์ผ์ด ์๋ ๊ณณ์ "image"ํด๋๋ฅผ ์์ฑํ๊ณ , 
     EC2 ๊ถํ์ ๋ชจ๋  ํฌํธ์์ ๊ฐ๋ฅํ๋๋ก ์ง์ญ๊ณผ ํฌํธ ์ค์ ์ ํ์ฌ ํด๊ฒฐ. 
```
  
  
  
  [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fmini-backend-8-B-3%2Ftrip-sorted-by-age.git&count_bg=%23FF8000&title_bg=%23615E67&icon=spring.svg&icon_color=%23FFFFFF&title=%EC%A1%B0%ED%9A%8C%EC%88%98&edge_flat=false)](https://hits.seeyoufarm.com)



