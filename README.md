Pixel Game
=============
## 게임 소개
+ 턴제 게임
+ 하단 플레이어를 날려서 획득한 아이템으로 상단 몬스터를 무찌르자.
## 진행 상황
| 1주차   | 2주차       | 3주차        | 4주차         | 5주차     | 6주차            | 7주차          | 8주차  | 9주차  |
|:-------:|:-----------:|:------------:|:-------------:|:---------:|:----------------:|:--------------:|:------:|:------:|
| ui<br>배치  | 아이템<br>랜덤 배치 | 캐릭터 이동<br>구현 | 캐릭터<br>충돌 처리 | 몬스터<br>구현 | 스테이지1,2<br>구현 | 보스 스테이지<br>구현 |디버깅|디버깅|
| 100%  | 100% | 100% | 100% |100% | 100% | 100% | 100% | 100% |


![commit_count](https://github.com/ojh6507/SPGTermProject/assets/45549589/956e1025-35a6-43e5-85b7-36ac1236379a)



## 주별 커밋 수
| 1주차   | 2주차       | 3주차        | 4주차         | 5주차     | 6주차            | 7주차          | 8주차  | 9주차  |
|:-------:|:-----------:|:------------:|:-------------:|:---------:|:----------------:|:--------------:|:------:|:------:|
| 4  | 18 | 15 | 21 | 25 | 16 |5 |16|30
*******
##  결과 화면
stage 1
![KakaoTalk_20240610_013115634_02](https://github.com/ojh6507/SPGTermProject/assets/45549589/47839139-e988-426a-8884-d74257b5fb3b)

stage 2
![KakaoTalk_20240610_013115634_01](https://github.com/ojh6507/SPGTermProject/assets/45549589/727120fb-3889-4484-a2c9-0b87d2650a48)

stage 3
![KakaoTalk_20240610_013115634_03](https://github.com/ojh6507/SPGTermProject/assets/45549589/903d2d53-bbd4-4db5-b300-55fc076f9a8a)

보상 스테이지 (보상 스테이지 종류 총 4개 랜덤으로 생성/ 스테이지1,2,3 클리어시 랜덤으로 보상 스테이지로 이동)

스테이지 1 클리어시 확률 40%로 이동

스테이지 2 클리어시 확률 50%로 이동

스테이지 3 클리어시 확률 70%로 이동 
![KakaoTalk_20240610_013115634](https://github.com/ojh6507/SPGTermProject/assets/45549589/4034b3dd-40d0-44c4-9f52-5935cb5b2fa5)

## 구현 내용
### 사용된 기술
+ TileGenerator
  Hash Set을 사용하여 중복없이 x,y 랜덤으로 생성  
  x,y를 String으로 key생성
  생성된 key가 Set에 있는지 확인.
  위 작업을 루프.
+ WarriorHead
  + 유클리드 거리 공식 (피타고라스 정리) 사용하여 dx, dy 구하여 targetX, targetY로 플레이어 직선 이동.
  + 플레이어 위치부터 targetX, targetY까지 직선 그리기

## 발표 영상
+ 1차 : https://www.youtube.com/watch?v=HGaGbaRLwSY
+ 2차 : https://www.youtube.com/watch?v=W9BWIl6AbiY
+ 3차 : https://www.youtube.com/watch?v=yl7u5pYOKJc
