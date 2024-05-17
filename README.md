# Pixel Game
=============
## 게임 소개
턴제 게임

하단 플레이어를 날려서 획득한 아이템으로 상단 몬스터를 무찌르자.
## 진행 상황
| 1주차   | 2주차       | 3주차        | 4주차         | 5주차     | 6주차            | 7주차          | 8주차  | 9주차  |
|:-------:|:-----------:|:------------:|:-------------:|:---------:|:----------------:|:--------------:|:------:|:------:|
| ui<br>배치  | 아이템<br>랜덤 배치 | 캐릭터 이동<br>구현 | 캐릭터<br>충돌 처리 | 몬스터<br>구현 | 스테이지1,2<br>구현 | 보스 스테이지<br>구현 |디버깅|디버깅|
| 50%  | 100% | 100% | 80% |70% | 40% | 0% | 0% | 0% |

+ ui 배치: hp ui 배치 필요
+ 캐릭터 충돌 처리: 몬스터와 상단 플레이어 충돌처리 필요
+ 몬스터 구현 : 몬스터 사망 애니메이션, 몬스터 공격 구현 필요
+ 스테이지1,2 : 스테이지 1에서 승리조건 추가해야 함



![스크린샷 2024-05-09 204809](https://github.com/ojh6507/SPGTermProject/assets/45549589/932f09a0-0003-4739-8c5b-1dede3257266)

## 주별 커밋 수
| 1주차   | 2주차       | 3주차        | 4주차         | 5주차     | 6주차            | 7주차          | 8주차  | 9주차  |
|:-------:|:-----------:|:------------:|:-------------:|:---------:|:----------------:|:--------------:|:------:|:------:|
| 1  | 3 | 18 | 15 | 6 | 0 |0 |0|
********
## 현재까지 진행된 화면
![GIFMaker_me (1)](https://github.com/ojh6507/SPGTermProject/assets/45549589/e61149d0-7e70-4fee-9dd2-da166db914f1)

********
## MainScene에 등장하는 gameObject
### WarriorHead : 
![rightface](https://github.com/ojh6507/SPGTermProject/assets/45549589/3733b354-f105-4b7d-82c0-4be9913ec72d)
#### 동작구성
**보드 벽과 충돌시 튕기는 움직임 구현**

#### 상호작용 정보 :
보드 경계, Item, Bomb, Obstacle과 충돌 상호작용

### Board :
![tile1](https://github.com/ojh6507/SPGTermProject/assets/45549589/5a06d062-14b3-4dbc-949b-5f1b422edfa7)

배경 역할.
### SwordStrike :
![swordstrike](https://github.com/ojh6507/SPGTermProject/assets/45549589/e577a92d-6b69-4de9-a20a-0f3c01e90cec)
#### 동작 구성: 리소스를 설정, 이동 방향 설정

### Warrior :
![warrior_idle_animsheet](https://github.com/ojh6507/SPGTermProject/assets/45549589/c21a0325-89de-4415-816a-6c2b9b3118d9)
![warrior_animsheet](https://github.com/ojh6507/SPGTermProject/assets/45549589/9ae4835b-696e-4cce-8e49-6e25cb6b2534)

#### 동작 구성:
SwordStrike를 생성합니다. 상태에 따라 (idle, attacked, hitted) 애니메이션 변경

#### 상호작용 정보 :
몬스터와 충돌시 데미지 적용

### Slime:
![red_slime_sheet](https://github.com/ojh6507/SPGTermProject/assets/45549589/08ad6b82-fffa-4c31-be6e-1b126f651519)
![blue_slime_sheet](https://github.com/ojh6507/SPGTermProject/assets/45549589/55744812-626f-4a19-942a-e2fcc2f9f6bc)

#### 동작 구성:
오른쪽에 미리 배치되어 있고 Turn이 시작될 때 왼쪽으로 한번 움직인다.
Turn이 시작될 때 Target을 새롭게 계산해서 움직임

### MapLoader
#### 동작구성:
보드 위에 아이템과 장애물들을 생성하고 랜덤 배치


### TurnBasedController
#### 동작구성:
1. 턴이 시작됐을 때 동작을 수행하도록 다른 오브젝트들의 함수 호출
2. 턴 종료

### SwordItem
![sword](https://github.com/ojh6507/SPGTermProject/assets/45549589/7b84cf71-b369-4430-a2b9-ad766dd7832e)

#### 동작구성:
MapObject를 상속 받음.

SwordItem 리소스 설정
### ShieldItem
![shield](https://github.com/ojh6507/SPGTermProject/assets/45549589/5110669b-cf30-4410-b1ec-e0e147a9df82)


#### 동작구성:
MapObject를 상속 받음.

SwordItem 리소스 설정

### Obstacle
![obstacle](https://github.com/ojh6507/SPGTermProject/assets/45549589/61a3d4d6-3aad-4be2-8542-7337e3c92e47)
#### 동작구성:
Sprite 상속 받음.
#### 상호작용 정보 :
Warrior Head와 Obstacle 충돌시 반사각으로 Warrior Head가 날라감.

Obstacle 리소스 설정.

### Bomb
![bomb](https://github.com/ojh6507/SPGTermProject/assets/45549589/524ada5c-49f2-4908-88c0-d8126356fad8)
#### 동작구성:
Sprite 상속 받음
#### 상호작용 정보 :
Warrior Head와 Bomb 충돌시 Warrior Head는 날라가는 움직임을 멈춘다.


