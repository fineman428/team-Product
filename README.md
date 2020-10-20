


# 운영

## CI/CD 설정

* Repository 구성
각 서비스 구현체들은 git hub repository 를 통합 구성되었음.

![image](https://user-images.githubusercontent.com/23513745/96562401-b0f6fd00-12fb-11eb-8b2d-6c5428318dbc.png)

* EKS 클러스터 구성 
![image](https://user-images.githubusercontent.com/23513745/96562583-e6034f80-12fb-11eb-9653-3c345d577d37.png)

* ECR Repository 구성 (서비스 별 )
![image](https://user-images.githubusercontent.com/23513745/96562656-f74c5c00-12fb-11eb-86ff-1956b5647b92.png)



## 클라우드 환경 서비스 테스트 (gateway 외부 URL)

* Step 1 product (상품) 등록, product(상품) -> rental(렌탈)/delivery(배송) 에 상품 및 재고 수량 등록
![image](https://user-images.githubusercontent.com/23513745/96562980-5a3df300-12fc-11eb-9129-21c708a96e9a.png)

* Step 2-1 rental (렌탈 요청정보) 등록, rental(렌탈) -> delivery(배송) 에 주문건 배송정보 등록 / 
![image](https://user-images.githubusercontent.com/23513745/96563001-645ff180-12fc-11eb-8d7f-fba6dd462402.png)

* Step 2-2, rental 주문정보 등록 후 information(myOrder) View CQRS에 등록 결과 확인 
![image](https://user-images.githubusercontent.com/23513745/96563329-c91b4c00-12fc-11eb-96c7-b34a2f1a9438.png)


## Configmap , 설정의 외부 주입을 통한 유연성을 제공
![image](https://user-images.githubusercontent.com/23513745/96563579-0ed81480-12fd-11eb-8104-0f8369ffc1f3.png)


## 동기식 호출 / 서킷 브레이킹 / 장애격리

* 서킷 브레이킹 프레임워크의 선택: Spring FeignClient + Hystrix 옵션을 사용하여 구현함

* 시나리오는 상품(product)-->렌탈(rental) 연결을 기준으로 시뮬레이션이 주성되었고, 상품등록 요청이 과도할 경우 CB 를 통하여 장애격리.

* application.yml 파일 수정, Hystrix 를 설정:  요청처리 쓰레드에서 처리시간이 610 밀리가 넘어서기 시작하여 어느정도 유지되면 CB 회로가 닫히도록 (요청을 빠르게 실패처리, 차단) 설정

![image](https://user-images.githubusercontent.com/23513745/96563655-26170200-12fd-11eb-8f46-2e71f19f56db.png)

* Product 서비스 product entity prepoersist 에 쓰레드 및 sleep 을 통한 부하처리 적용 
![image](https://user-images.githubusercontent.com/23513745/96563674-2e6f3d00-12fd-11eb-8b2c-072112a9cd71.png)



### 오토스케일 아웃



## 무정지 재배포


