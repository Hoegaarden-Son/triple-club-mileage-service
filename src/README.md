############################################################
# 실행방법

-  (python 3.X 버전 이상)


#### [1. python 파일 실행 - DB scheme & 권한 설정]
    // 프로젝트 내 /src 와 동일 depth 의 /tools 폴더에서, 아래 실행합니다.
    python3 create_db.py

    // (mysql) 기준으로, 설정 변경 필요 시에,  
    /src/main/resources/application.properties 변경 필요합니다.


#### [2. mysql 서비스 및 application 실행]
    /src/main/java/~~/Application.java 실행

#### [3. / 에서 review 등록하여 event 발생]
- Web application에서 Review 를 등록/삭제/수정 하는 등의 상황을 가정하여 구현하였습니다.
- Review 를 등록/수정/삭제 하면 POST /events API 가 호출됩니다.
- 작성자ID 를 클릭할 시, Point 조회가능하도록 하였습니다.
- 수정/삭제 등 구현되지 않은 Logic 들이 있습니다.

#### [4. /events 외 API ]
...
- user ID 별 포인트 list API 조회 (혹은 web app 에서 user ID 클릭):
  /points/{userId}
- user ID 별 전체 포인트 API 조회 :
  /points/all/{userId}
- 발생 event 는 DB에 저장 (Events)   
############################################################
(*) 참고 말씀...

- index column 설정하지 못했는데,
  만약 설정한다면, select 의 condition 시 필요한 column 들에 대해 설정할 것 같습니다.
  
전체 구현되지 않아 주석처리된 로직 내용들이 있습니다.
이점 양해 부탁드리겠습니다,, (__)