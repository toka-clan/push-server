## todo
### 개발 
- 푸시 메시지 도메인 설계
  - [x] daily/weekly/monthly 엔티티 추가
- 푸시 메시지 DB저장로직 개발
  - [x] repository 추가
- api 개발
  - [x] 푸시 등록/수정/삭제 api
  - 클라쪽 로컬DB에 등록된 데이터와 싱크 중요
    - 서버 api 요청 실패시 클라에서 트랜잭션 처리
- 인증
  - JWT인증
- APIDOC
  - swagger apidoc
- push sender 개발
  - 프로젝트 properties 파일 받기
  - sendAll() 다건 전송 테스트 
- 배치 개발
  - @EnabledScheduling
  - 스프링 배치
- 카프카나 rabbitmq 도입?

### 빌드
- github actions?
- git -> githubactions -> aws

### 배포
- aws 배포 or nas활용
- 기도메인주소 활용
- db 
  - mysql 

### 모니터링
- 