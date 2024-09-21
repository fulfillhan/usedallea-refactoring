# 👩🏻‍💻 중고 거래 기능 플랫폼 개발 1인 프로젝트 - Refactoring

`기간: 2023.10. ~ 2024.04.17` 

<br/>

## ☑️ Refactoring 현황
### Architecture
- 레이어드 아키텍처의 각 레이어를 분명하게 구분하여 의존성 분리
- 각 계층간 데이터 교환은 DTO를 사용
- 데이터 접근 계층에서의 데이터 교환은 엔티티 개념을 적용
- MVC를 이해하며 적용

### Implementation
- Restful API
- 메서드, 변수명을 지을 때 의미를 나타는 메시지를 표현하고자 함
- 인덱스와 풀스캔을 예상하며 쿼리 작성
- SOLID를 녹여내고자 고민하며 진행

<br/><br/>

## 🌿프로젝트 개요

이번 첫 프로젝트는 중고 물품 거래를 위한 플랫폼의 개발입니다. 사용자들이 중고 거래를 위해 간편하게 품목을 등록하고 관리할 수 있는 게시글을 작성할 수 있는 플랫폼입니다. 이를 통해, 중고 제품의 판매 및 구매 시 필요한 정보를 공유하고 소통하며, 거래 조건과 금액 등을 포함한 다양한 정보를 제공하여 원활한 거래를 지원합니다.

<br/>

## 🌿기술 스택

- 언어
    - Back-End: **`jdk 17`**, **`Spring Boot 3.2.3`**, **`MyBatis`**
    - Front-End: **`thymeleaf` ,`HTML5` ,  `CSS3`,  `Javascript` , `jQuery`**
- 개발 도구 : **`IntelliJ IDEA`**, **`MySQL workbench 8.0 CE`**
- 소스 관리 : **`Git` , `Git hub`**
- 사용 라이브러리 및 API :
    - 의존성 관리 : **`Gradle`**
    - 데이터 전송: **`AJAX` , `JSON`**
    - 로그:  **`Logback`**
    - Daum 주소 :**`DaumPostCard`**

<br/>
  
## 🌿ERD

![스크린샷 2024-09-05 122833](https://github.com/user-attachments/assets/8e6d37c3-8368-46ca-afe0-8e3c55c66c25)

<br/>
  
## 🌿주요 기능

- 인증 기능: http 세션 기반의 인증
- 상품 관리 기능: 상품 등록, 수정, 삭제 및 조회
- 사진 업로드/ 관리: 상품 사진 업로드
- 페이징 및 검색 기능: 키워드를 통한 검색으로 페이징
- 관심 상품 기능: 관심 상품 표시 및 취소
- 상품 상태 관리: 판매 중, 판매 완료, 삭제 등 상태 변경 기능
- 관리자 기능: 사용자 및 상품 관리 기능(수정, 삭제)

<br/>
  
## 🌿기능 요구 사항

1. 사용자 관리
- 회원 가입 및 정보 수정
- 로그인 / 로그아웃
- 회원 유효성 변경

1. 상품 관리
- 상품 목록 및 상세 조회
- 상품 사진 업로드 및 보기
- 특정 키워드로 상품 검색
- 관심 상품 표시/ 취소
- 상품 정보 등록, 수정, 삭제
- 상품 상태 변경

1. 관리자 기능
- 사용자 등록한 상품 관리
- 전체 상품 조회
- 사용자 상품 삭제 및 수정

<br/>
  
## 🌿기술 Specification
- 인증 과정, 아이디 확인 등 AJAX  구현
- 레이어드 아키텍처(Presentaion Layer, Business Layer,Data Access Layer) 적용
- 세션 기반으로 인증 및 인가  구현
- 회원 가입 시 패스워드의 보안 강화를 위한 암호화
- 회원 탈퇴 시 바로 탈퇴가 아닌, 활동성 여부를 ‘비활동’ 으로 변경
