# Where Is My Home

## Team - 문준호 한성현

|문준호|한성현|
|:--:|:--:|
|[<img width="140px" height="140px" src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/c5429942-35f8-47c8-9c57-1b09653fc68b">](https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/c5429942-35f8-47c8-9c57-1b09653fc68b)|[<img width="140px" height="140px" src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/20318162-ee5a-4696-9844-ce8b6dcb738a">](https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/20318162-ee5a-4696-9844-ce8b6dcb738a)|
|팀장(FE)|팀원(BE)|
|[문준호](https://github.com/tjsduq0423)|[한성현](https://github.com/HanSungHyeon)|

---
## 기술 스택
<div style="text-align:center">
    <img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/d89b0cc8-baba-497e-a325-b814022d1eca" style="width:500px; margin-bottom:20px"/>
</div>

---
# 구현 기능

### 기능 1
- 공공데이터 + KaKao Map API를 활용한 아파트 정보 조회 시스템
### 기능 2
- Chart.js + Vue.js 3 관심도 추적 차트
### 기능 3
- OAuth2.0 + SpringSecurity 를 활용한 소셜 로그인 + 인증,인가 보안 시스템
### 기능 4
- Haversine Formula(구의 최단거리 알고리즘)을 활용한 아파트 주변 편의시설 정보 제공

---
# 기능 최적화

### 이벤트 핸들링 최적화 (debounce)
- 과도하게 이벤트 처리 함수(콜백함수)가 호출되지 않도록 하여 부하방지를 위해 쓰이는 방법입니다.
- 프로젝트에서는 북마크on/off, 카카오 지도 줌, 중심좌표 이동 이벤트 ,아이디 중복체크, 검색어 자동완성, 데이터 필터링 이벤트 등에 널리 쓰이며 이벤트 핸들러의 과도한 데이터 요청으로 인한 부하를 방지하여 프로젝트의 성능을 향상시켰습니다.
```javascript
function debounce(func, timeout = 300) {
  let timer;
  return function(){
    clearTimeout(timer);
    timer = setTimeout(() => {
      func.apply(this);
    }, timeout);
  };
}
```

### DB쿼리 최적화 (공간 인덱스)
- 현재 위도 경도를 기준으로 일점 범위 안에 포함된 데이터를 조회하는 과정에서 속도 저하 발생
- geometry 공간 데이터 타입 중 Point 타입을 사용하여 위도 경도를 저장
- sptial 공간 인덱스를 사용하여 쿼리 속도 개선
- 사용된 쿼리 
```
    select h.apt_code, h.apartmentName, h.lng, h.lat
    from HouseInfo h
    where st_contains(st_buffer(Point(:lng, :lat), :dist), h.localPoint);
```
|공간 인덱스 적용 전|공간 인덱스 적용 후|
|:--:|:--:|
|<img src ="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/b1821f25-2442-4d9f-9796-e644679e192e"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/f9604dcf-112d-4df8-9be8-4686ad9ece1d"/>
- ## 쿼리 속도 성능 약 150배 개선
    

---

# 프로젝트에 적용된 디자인 패턴
### FrontEnd 
Vue.js 3 Composition API는 프로젝트에서 활용되는 디자인 패턴입니다. Composition API는 JavaScript의 Proxy를 기반으로 동작하며, 객체 사이에 중간 계층을 추가하여 객체에 대한 접근을 제어하거나 변형하는 프록시 패턴과 유사한 개념입니다. 이를 통해 컴포넌트의 반응성을 관리하고 추적할 수 있습니다.

Vue의 Composition API에서 사용되는 Proxy 객체는 컴포넌트의 데이터에 대한 접근을 가로채고 변경을 감지하여 컴포넌트의 다시 렌더링을 트리거합니다. 이를 통해 상태 관리와 컴포저블 등의 효율적인 코드를 구현할 수 있습니다. 반응형 변수를 활용하여 컴포넌트의 상태를 관리하고, 이를 컴포저블로 분리하여 재사용 가능한 모듈로 만들 수 있었습니다.

프로젝트에서 Vue의 Composition API를 활용하여 상태 관리와 코드 구조화를 효율적으로 처리할 수 있습니다. 프록시 객체를 사용하여 컴포넌트의 데이터 접근을 제어하고 변경을 감지함으로써 컴포넌트의 반응성을 관리할 수 있습니다. 이는 짧은 기간의 프로젝트에서 많은 작업을 수행할 수 있게 해주었습니다.

----
### BackEnd
클라이언트에 필요한 데이터를 관리하는 서버를 구축하기 위해 Spring Boot 프레임워크를 사용했습니다. Spring Boot는 다양한 디자인 패턴을 내장하고 있어 서버의 자원 관리와 유지 보수성을 고려한 개발을 지원합니다.

의존성 주입(Dependency Injection) 패턴: Spring Boot는 의존성 주입을 통해 객체 간의 의존성을 해결합니다. 이를 통해 객체 간의 결합도를 낮추고 유지 보수성을 높입니다.

BeanFactory 패턴: Spring Boot는 BeanFactory 패턴을 사용하여 객체의 생성과 관리를 담당하는 컨테이너를 제공합니다. 이를 통해 객체의 라이프사이클과 의존성 관리를 효율적으로 처리할 수 있습니다.

또한, Spring Security의 필터 체인에서 Strategy 패턴과 프록시 패턴이 적용되어 있습니다. Strategy 패턴은 인증 및 인가와 관련된 설정을 동적으로 변경할 수 있는 유연한 로직을 제공합니다. 프록시 패턴과 AOP(Aspect-Oriented Programming)를 활용하여 로깅, 에러 핸들링, 트랜잭션 관리 등의 관심사를 분리하여 코드의 재사용성과 유지 보수성을 높입니다.

또한, JPA를 활용할 때 빌더 패턴을 사용하여 유연하고 유지 보수성이 높은 쿼리를 작성합니다. 빌더 패턴은 복잡한 객체 생성을 단순화하고 가독성을 향상시키는데 도움을 줍니다.

---

# API 문서 - PostMan API 문서
https://documenter.getpostman.com/view/23655032/2s93m1YPB4#0ee2b2f6-67a6-4ca9-ae1f-5c177e7a834e

<div style="text-align:center" >
    <img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/d94a0574-2adb-4de7-a826-b5ec896831bb" style="width:500px" />
</div>

## 로그인 & 회원 가입 & 비밀번호찾기

| 로그인 | 카카오 로그인 | 
| :--------: | :--------: | 
|   <img src="https://github.com/HanSungHyeon/Geofencing/assets/66876894/9fa24f99-a043-491a-a496-1c410a0543ec" />|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/a630b007-7e80-4999-8a88-73a0423de59f" />|


<br>


| 회원가입  | 비밀번호 찾기 |
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/870a8308-c827-42c5-a85f-498bb811eff2"/>|<img src = "https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/04890187-30d4-4436-9415-8558ce5e91ab"/>|

<br>

## 공지사항

| 공지사항 | 관리자 공지사항 작성
| :--------: | :--------: |
|<img src = "https://github.com/HanSungHyeon/Geofencing/assets/66876894/fe4aa204-ef78-426c-9b8f-bcf213512cf3"/>| <img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/ee239193-5455-44e6-a22b-08ddb9cdeca6"/>

<br>

## 게시판

| Q&A  | Q&A 작성 | Q&A 수정 |
| :--------: | :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/68eb21a9-16c7-47c0-a87a-1e28eb52e13e"/>|<img src = "https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/38c820fa-1e99-4f5f-b0a2-cdb3ca61dfd8"/>| <img src = "https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/42d41de4-b25e-459b-96dc-d5b0f968f43e"/>|

<br>

## 아파트 매매 정보

|아파트 매매 정보 - 마커| 맵 이동 | 마커 오버레이 | 
| :--------: | :--------: | :--------: | 
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/cb2fd756-0e96-42cd-a3d2-3de8d33077f5"/>|<img src = "https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/2a444ade-ce30-477d-8d38-00866b7e45fa"/>|<img src = "https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/b8a1ea39-b3d8-404c-9bef-cc8124fda228"/>

<br>

## 사이드바

|로드맵| 거래내역 & 인기순위 | 주변 인프라 & 뉴스 | 
| :--------: | :--------: | :--------: | 
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/2b423ba6-9e77-41d3-9659-74ef05cf2aac"/>|<img src = "https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/5ed2171b-b35f-40e3-af63-f027f3eafa12"/>|<img src = "https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/d5d03834-bdee-4616-9929-8186ce51b67b"/>|

<br>

## 필터링

|검색|매매정보 필터|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/016a4b64-37e7-4c58-b7e4-f070e8d1a2e6"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/fe569aa4-bfab-41d0-94b6-2a6980ec1739"/>

<br>

## 북마크

|북마크|
| :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/20bd0ec9-6fe2-416d-bdbf-2638f9332ea4" style="width:400px"/>|

<br>

## 프로필

|프로필 Q&A|프로필 북마크|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/fc23521a-b4d4-4928-b369-4b6025c8344c"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/c5081d6a-9e33-48f7-8bd8-061482bd3f62"/>

---
## UI 설계서

|메인화면 차트 | 로그인|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/080d17fe-1e1a-4230-a58d-e9085caad17a"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/810dd11c-942d-4952-9f56-de8f8d43315b"/>

<br>

|공지사항 | 북마크 프로필|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/fd377d6f-e8a8-4025-a851-81e6574dbf80"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/a3d0818c-62ae-4019-817b-a1dc1aa8003d"/>

<br>

|아파트 매매 정보 | 북마크 매매 정보|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/f171f45e-bcf4-4fdc-bf9c-211c4ee559fe"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/ee168661-da4f-4587-8c87-de5e2d4218c5"/>

<br>

---
<br>

## ERD
<div style="text-align:center">
    <img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/affe75b4-cfd8-4da8-943f-3a595824e5c4" style="width:700px"/>
</div>

---

## 클래스 다이어 그램

|게시판|편의시설|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/221cdd15-6f13-446a-b702-7f6a56b75f56"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/64d963cf-a26f-4377-bf3e-5fddc9348284"/>

<br>

|회원|동코드|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/b81ea169-a040-4241-8523-802da24eabba"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/18551780-6369-4620-ab8f-3b23b660d40d"/>

<br>

|북마크|아파트 매매 내역|
| :--------: | :--------: |
<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/0cb0b1d3-1664-442a-b9b3-fe9c9b353a1e"/>|<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/e9112b81-08ea-417a-aa26-ec34d159ed4f"/>

<br>

|아파트 정보|

<img src="https://github.com/tjsduq0423/WhereIsMyHome/assets/66876894/00e5330e-3a69-4ebe-9499-d83ab923a742"/>

<br>

---

<br>

## 라이센스

MIT &copy; [MJH&HSH]
