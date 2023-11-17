# README

# [ 시시비비 ]

## 📰 프로젝트 소개

## CCBB: 게임내에서 일어나는 다툼을 중재해 주는 사이트

CCBB는 평소 리그 오브 레전드라는 게임을 하면서 다툼이 발생할 때 서로의 시시비비를 따져보기 위한 서비스 입니다. 이를 위해 투표 공약 보증금과 같은 기능을 추가하여 사용자들에게 부담을 주지 않고 자발적으로 참여할 수 있게 만들었습니다.

## ⚙️ 기술 스택

### BackEnd
<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=black">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=black">
<img src="https://img.shields.io/badge/JPA-61DAFB?style=for-the-badge&logo=JPA&logoColor=black">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=black">

### FrontEnd

<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
<img src="https://img.shields.io/badge/recoil-3578E5?style=for-the-badge&logo=recoil&logoColor=black">
<img src="https://img.shields.io/badge/axios-5A29E4?style=for-the-badge&logo=axios&logoColor=black">
<img src="https://img.shields.io/badge/styledcomponents-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=black">
<img src="https://img.shields.io/badge/chartdotjs-FF6384?style=for-the-badge&logo=chartdotjs&logoColor=black">

### Infra

<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=black">
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=black">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=black">
<img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=black">

### Cooperation

<img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=black">
<img src="https://img.shields.io/badge/jirasoftware-0052CC?style=for-the-badge&logo=jirasoftware&logoColor=black">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
<img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=black">
<img src="https://img.shields.io/badge/gerrit-EEEEEE?style=for-the-badge&logo=gerrit&logoColor=black">

## 💡 주요 기능


### 메인페이지

- 영상
  - Cloud Flare를 활용해 영상을 업로드 했습니다.
  - 해당 영상에 논점을 연동해서 영상위에 글이 보이도록 했고, 영상에 마우스를 올리면 영상이 실행되며, 영상을 클릭하면 해당 투표글로 가집니다.
  - 현재 가장 많은 투표수를 가진 게시글 5개에 사용된 영상으로 메인페이지를 구성했습니다.

![메인페이지](/uploads/877012d39976686ec7cd7abe1de1f209/메인페이지.gif)

### 투표

### 투표 진행 플로우 차트

![flowchart](/uploads/3c5cb5b9c2a2887e775df9389a58918a/flowchart.png)

### 투표게시글

![투표게시글](/uploads/0cf93186e8ffa67ccff85619642d4039/투표게시글.gif)

- 투표 기능
  - 투표 기간이 끝나기 전에는 득표수가 보이지 않음
  - 투표 기간이 끝난 후 투표 결과가 보임
- 와드 기능 (마이페이지 즐겨찾기)
- 댓글 기능
  - 댓글의 닉네임 색깔로 어느 쪽을 선택했는지 확인 가능 (미투표자는 검은색)

### 투표 작성

![글작성_2](/uploads/15a770bc0bfe225f49d6084aca076735/글작성_2.gif)

- 영상 업로드
  - 논점을 가리기 위해 영상이 필요하다. 영상을 업로드 하는 기능
- 티어 제한
  - 일정 티어 이상의 유저에게서만 투표를 받고 싶을 때 제한을 거는 기능
- 마감 날짜
  - 투표 마감날짜를 설정해서 해당 날짜에 투표를 마감하도록 하는 기능
- 보증금 기능
  - 게시글을 작성 할 때 보증금을 카카오페이를 통해서 결제
  - 투표의 결과에 따라 승자는 보증금을 돌려받고, 패자는 공약을 이행하면 환급 받을 수 있음
  - 공약을 이행하지 않을 시 투표 승자의 이름으로 보증금은 기부됨
- 카카오 페이 연동
  - 유저들이 글을 작성할 때 보증금을 걸게 되는데 이 때 사용됨

### 투표 수락

![투표수락](/uploads/c264b058517bf64cfac1f930eb6aad6f/투표수락.gif)

### 공약 이행

![투표수락](/uploads/c264b058517bf64cfac1f930eb6aad6f/투표수락.gif)

### 알림

- SSE(Server-Sent-Event)를 이용해 구축
- 투표, 댓글

### 티어연동

![티어연동_1](/uploads/422161394e84e28bd0e8978ae990dce0/티어연동_1.gif)

- RIOT API를 이용해 LOL 티어 정보 불러오기 기능

### 상품 응모

![응모](/uploads/23f75c94ad8ddeb652057ac3b396fb67/응모.gif)
- 사이트에서 활동하면서 모인 포인트를 사용하여 상품을 응모하는 기능
- **Pessimistic Lock**을 이용해 동시성 문제 해결

## 📄 아키텍처 설계도

![아키텍처](/uploads/ca1577a224efd6f602a0938b0eb8dc00/아키텍처.png)

## 📊 ERD

![ERD](/uploads/e0785e1de391f260f888bcea8f6f809d/ERD.png)

## ✌️ 팀원 소개

| 이름  | 역할            | 주요 임무                                                   |
| --- | ------------- | ------------------------------------------------------- |
| 최영창 | BE            | cloudflare 연동, 파일 관련 API, OAuth2 카카오 로그인, 카카오 페이 결제 API |
| 윤우혁 | BE, FE, Infra | CI/CD 구축, 알림 기능 (BE, FE), 투표 관련 FE 로직                   |
| 천병찬 | BE, FE        | 백엔드, 프론트엔드, 기획                                          |
| 채경호 | BE            | 게시판, 투표, 상품, 이벤트, 와드, 및 백엔드 전반 API 작성                   |
| 박종욱 | FE            | UI/UX, 프론트엔드, API연동, 투표수락,거절, 공약이행 로직구현                 |
| 신경희 | FE            | UI/UX, 프론트엔드, API연동                                     |
