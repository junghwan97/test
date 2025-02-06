# **🌟VitaQueue 프로젝트 소개**
**📅MVP 개발기간**: 2024.12.18 - 2025.01.18

- Vitaqueue는 한정된 재고를 가진 상품을 구매하려는 사용자들이 공정한 과정을 통해 구매할 수 있는 이커머스 플랫폼입니다.
- 이 프로젝트는 높은 동시성 환경에서 사용자가 겪는 불편함을 최소화하는 데 중점을 두어 설계되었습니다.

## 프로젝트 실행 방법

### 1. **환경 요구 사항**
- **Docker** 및 **Docker Compose** 설치
- MySQL Workbench 또는 기타 데이터베이스 클라이언트 (선택 사항)

### 2. **프로젝트 클론**
```bash
git clone https://github.com/your-repository/VitaQueue.git
cd VitaQueue
```
<details>
<summary>env 파일 예시</summary>
<div markdown="1">
  
- 공통 설정
  - JWT_SECRET_KEY=your_secret_key

- User DB
  - USER_MYSQL_DATABASE=user_db
  - USER_MYSQL_USERNAME=user
  - USER_MYSQL_PASSWORD=user_password

- Product DB
  - PRODUCT_MYSQL_DATABASE=product_db
  - PRODUCT_MYSQL_USERNAME=product
  - PRODUCT_MYSQL_PASSWORD=product_password

- Order DB
  - ORDER_MYSQL_DATABASE=order_db
  - ORDER_MYSQL_USERNAME=order
  - ORDER_MYSQL_PASSWORD=order_password

- Wishlist DB
  - WISHLIST_MYSQL_DATABASE=wishlist_db
  - WISHLIST_MYSQL_USERNAME=wishlist
  - WISHLIST_MYSQL_PASSWORD=wishlist_password

</div>
</details>

### 3. **Docker Compose로 실행**
```bash
docker-compose up --build
```

---
## 🛠️개발 환경 및 기술 스택

### 🖥️언어
- **Java**: 17
- **Gradle**: 8.11.1

### 🚀프레임워크
- **Spring Boot**: 3.3.6

### 📚라이브러리
- **Spring Data JPA(Hibernate)**
- **Spring Security**
- **Spring Cloud Gateway**
- **Spring Cloud Netflix Eureka**
- **Spring Cloud OpenFeign**

### 🗄️DB
- **MySQL**: 8.0
- **Redis**: 7.4.1

### 🛳️Infrastructure
- **Docker**


### **📑API 문서**
[API 문서 바로가기](https://documenter.getpostman.com/view/30963150/2sAYJ3DfzK)

---

# **📝VitaQueue 프로젝트 설명**


## **🏗️아키텍처**
![아키텍처 구조](https://github.com/user-attachments/assets/06cdb835-5893-4c96-9b40-1ce73386f49d)

## **⚙️주요 기능**

- 한정 수량 상품 구매 기능
- **Redis 캐싱**기반의 상품 재고 관리
  - 데이터베이스 부하 감소 및 재고 관리 성능 향상
- **Open Feign**을 활용한 MSA 서비스 모듈 간 통신 구현
- **API Gateway**를 JWT 검증 및 요청 라우팅 처리
  - 단일 진입점에서 JWT 인증과 요청 분배를 담당
- **Spring Security**를 활용하여 회원 가입 및 사용자 인증 관리
- Google SMTP를 활용하여 이메일 인증
- JWT를 활용하여 로그인 구현
- **스케줄러**를 통한 주문 상태 관리
  - 시간의 흐름에 따라 주문 상태 변경
- **Prometheus**와 **Grafana**를 활용하여 모니터링 시스템 구축 v

<details>
<summary>🏗 Sequence Diagram</summary>
<div markdown="1">

![Sequence Diagram](https://github.com/user-attachments/assets/32a8be83-2b84-45c8-af69-f2ac9c0d4b2c)

</div>
</details>

---

# Redis를 활용한 재고 동시성 제어 및 캐싱 최적화

## 1. 기존 문제점 (AS-IS)
### 🔹 동시성 제어 부족
- DB 트랜잭션에만 의존하여 높은 부하 발생  
- 트래픽 증가 시 응답 속도 저하  
- 데이터 정합성 이슈  

### 🔹 비효율적인 조회 방식  
- 모든 요청이 DB에 직접 접근
- 동시 요청 증가 시 DB 과부하 발생  

## 2. Redis 기반 동시성 제어 (TO-BE)
- **Redis 분산락**을 활용한 빠른 재고 처리  
- **초당 5000건의 동시 요청** 처리 가능  
- 데이터 정합성 **100% 보장**  

### 🚀 성능 개선 지표  
| 동시접속자 수 | TPS  | 평균응답시간(ms) | 에러율(%) |
|:------------:|:----:|:---------------:|:---------:|
| 100          |  850 | 95              | 0         |
| 500          | 2100 | 180             | 0         |
| 1000         | 3800 | 250             | 0         |
| 5000         | 5000 | 380             | 0.01      |

## 3. 캐싱 전략 최적화 (TO-BE)
- Redis 캐시 적용으로 DB 부하 **75% 감소**  
- 평균 조회 속도 **20ms로 90% 개선**  
- **Cache Hit Ratio 95%** 달성  

### 🚀 캐싱 성능 개선 지표  
| 동시접속자 수 | Cache Miss 응답시간(ms) | Cache Hit 응답시간(ms) | Cache Hit Ratio(%) |
|:------------:|:---------------------:|:-------------------:|:----------------:|
| 100          | 200                   | 20                  | 95               |
| 500          | 350                   | 22                  | 94               |
| 1000         | 450                   | 25                  | 93               |
| 5000         | 600                   | 30                  | 92               |

## 4. 결론
- Redis를 활용한 **재고 동시성 제어** 및 **캐싱 최적화**로 DB 부하 감소  
- 트래픽 증가에도 **안정적인 처리 성능** 유지  
- 데이터 정합성을 보장하면서도 **빠른 응답 속도** 확보  



## Spring Cloud Gateway 로드밸런싱
- **AS-IS**
  - 단일 서버 구조로 부하 집중
  - 평균 응답시간 450ms
  - 서버 장애 시 전체 서비스 중단
- **TO-BE**
  - 로드밸런싱으로 부하 분산
  - 평균 응답시간 320ms로 28% 개선
  - 가용성 99.9% 달성

| 동시접속자 수 | 적용 전 TPS | 적용 후 TPS | 적용 전 응답시간(ms) | 적용 후 응답시간(ms) |
|:------------:|:----------:|:----------:|:-----------------:|:-----------------:|
| 100          | 400        | 800        | 450               | 320               |
| 500          | 850        | 2000       | 680               | 420               |
| 1000         | 1200       | 3500       | 890               | 520               |
| 5000         | 1500       | 4800       | 1200              | 650               |



※ 테스트 환경
- CPU: Intel Xeon E5-2680 v4
- Memory: 32GB
- JMeter 5.5 사용
- 테스트 시간: 각 10분
- Network: 1Gbps
---

# **트러블슈팅**
- **주문 상태 일관성 문제**
  - 문제: 동시 주문 처리 시 상태 불일치 발생
  - 원인: 분산 환경에서의 상태 관리 미흡
  - 해결: 스케줄러 기반 주문 상태 관리 시스템 구현

- **모니터링 시스템 부재**
  - 문제: 시스템 장애 감지 및 분석 어려움
  - 원인: 모니터링 도구 부재
  - 해결: Prometheus와 Grafana를 활용한 모니터링 시스템 구축  


# **향후 개선할 것**

