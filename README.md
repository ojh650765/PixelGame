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

보상 스테이지 (보상 스테이지는 총 4개 랜덤으로 생성/ 스테이지1,2,3 클리어시 랜덤으로 보상 스테이지로 이동) 
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
### 참고한 것들:
구글링, gpt
### 수업 내용에서 차용한 것
+ Scene 전환
+ Scene class
+ Sound class
+ CollisionHelper
+ sprite / anim sprite class
+ button class
+ Score class
+ Metrics class
+ GameView
+ Lazy Initialize로 LinePaint 생성 (stroke)

### 직접 개발
+ Sprite 에셋
+ Turn Based Controller 클래스(턴 기반 컨트롤러) -> 몬스터 움직임, 보드 초기화, 스테이지 클리어 관리
+ Player 날리기 (드래그로 targetX, targetY 정하고 dx, dy를 유클리드 거리 공식으로 구함.)
+ Player와 장애물(바위) 충돌시 반사각으로 날리기
+ 랜덤으로  보드판 위에 아이템 및 장애물 배치 TileGenerator (플레이어와 겹치지 않게)
+ Sound 개선 (Sound 클래스가 사용될 사운드를 미리 Pool과 Hash Map에 저장할 수 있도록 MainScene 생성자에서 함수 호출) -> 사운드 재생시 딜레이 되는 문제 해결
+ sprite hp bar 감소 구현
+ Slime 이동 (플레이어 턴이 끝나면 자기 위치보다 앞인 x값을 targetX로 설정하여 이동)

## 아쉬운 점
### 보충할 점
+ 콘텐츠 다양성
+ 최적화된 로직
+ 게임 밸런스, 난이도 조절
### 어려웠던 점
+ 턴 안에 몬스터가 앞으로 한칸만 움직일 수 있도록 처리하는 게 어려웠습니다.

## 발표 영상
1차 youtube:

https://www.youtube.com/watch?v=HGaGbaRLwSY
2차 youtube: 

https://www.youtube.com/watch?v=W9BWIl6AbiY
3차 youtube:

https://www.youtube.com/watch?v=yl7u5pYOKJc
