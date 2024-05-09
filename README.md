# Pixel Game
********
## 게임 소개:
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
## MainScene에 등장하는 gameObject
### WarriorHead : 
![rightface](https://github.com/ojh6507/SPGTermProject/assets/45549589/3733b354-f105-4b7d-82c0-4be9913ec72d)
#### 동작구성 및 핵심 코드
**보드 벽과 충돌시 튕기는 움직임 구현**
```java
    public void update(float elapsedSeconds) {
        // x 위치 업데이트
        x = dx * elapsedSeconds;
        // y 위치 업데이트
        y = dy * elapsedSeconds;
        
        // 경계 충돌 검사 및 반응
        if (x < leftBound) {
            x = leftBound;
            dx = -dx; // 방향 반전
        } else if (x > rightBound) {
            x = rightBound;
            dx = -dx; // 방향 반전
        }
        // 상하 경계 검사
        if (y < upperBound) {
            y = upperBound;
            dy = -dy; // y축 방향 반전
        } else if (y > lowerBound) {
            y = lowerBound;
            dy = -dy; // y축 방향 반전
        }
        // 위치 업데이트
        setPosition(x, y, HEAD_WIDTH, HEAD_HEIGHT);
    }
```
**날라가는 방향 결정 코드**
```java
    public boolean onTouch(MotionEvent event) {
        if (Warriormove) {
            return false;
        }
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //타겟 위치 리소스 렌더링
                float[] pts = Metrics.fromScreen(event.getX(), event.getY());
                targetX = pts[0];
                targetY = pts[1];
                targetRect.set(
                        targetX - TARGET_RADIUS, targetY - TARGET_RADIUS,
                        targetX  TARGET_RADIUS, targetY  TARGET_RADIUS
                );
                shouldDrawLine = true; 
                return true;
            case MotionEvent.ACTION_UP:
                float deltaX = targetX - x;  // X축 차이 계산
                float deltaY = targetY - y;  // Y축 차이 계산
                float distance = (float) Math.sqrt(deltaX * deltaX  deltaY * deltaY); //거리 계산

                dx = SPEED * (deltaX / distance);
                dy = SPEED * (deltaY / distance);

                Warriormove = true; // 움직이는 동안 컨트롤 불가하기 위함
                shouldDrawLine = false;
                return true;
        }
        return false;
    }
```
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

#### 동작 구성: SwordStrike를 생성합니다.
#### 핵심 코드: 상태에 따른 (idle, attacked, hitted) 애니메이션 변경
```java
private static final int[] resIds = {
            R.mipmap.warrior_idle_animsheet,R.mipmap.warrior_animsheet
    };

    @Override
    public void update(float elapsedSeconds) {

        switch (state) { //상태에 따라 애니메이션 변경
            case attack:
                ChangeAnimSprite(resIds[1],ANIM_FPS);
            if (frameIndex==WARRIOR_ATTACK_MOTION_FRAME && !attaked) {
                Slash();
                attaked = true;

            }
            if(frameIndex == WARRIOR_ATTACK_MOTION_END_FRAME){
               state=State.idle;
                changed = false;
                attaked = false;
                break;
            }
            if(frameIndex!=WARRIOR_ATTACK_MOTION_FRAME) attaked = false;
            break;
            case idle:
                ChangeAnimSprite(resIds[0],ANIM_IDLE_FPS);
                break;
        }
    }

    private void Slash() {  //swordStrike 생성
        Scene.top().add(MainScene.Layer.slash, SwordStrike.get(x, y ));
    }
    public  void ChangeState(State s){
        if(!changed){ //상태 변경
           anim_PlayTime =  System.currentTimeMillis();
            state = s;
           changed = true;
        }
    }
```
#### 상호작용 정보 :
몬스터와 충돌시 데미지 적용

### Slime:
![red_slime_sheet](https://github.com/ojh6507/SPGTermProject/assets/45549589/08ad6b82-fffa-4c31-be6e-1b126f651519)
![blue_slime_sheet](https://github.com/ojh6507/SPGTermProject/assets/45549589/55744812-626f-4a19-942a-e2fcc2f9f6bc)

#### 동작 구성:
오른쪽에 미리 배치되어 있고 Turn이 시작될 때 왼쪽으로 한번 움직인다.
#### 핵심 코드: 
Turn이 시작될 때 Target을 새롭게 계산해서 움직임
```java
    @Override
    public void update(float elapsedSeconds) {
        if (isMoving) {
            if (Math.abs(x-targetX) > 0.01f)  {
              dx = -SPEED;
            } else {
                dx = 0;
                isMoving = false;
            }
            super.update(elapsedSeconds);
        }
        collisionRect.set(dstRect);
        collisionRect.inset(0.11f, 0.11f);
    }


    public boolean ApplyDamage(float damaage){ //데미지 적용
        HP -= damaage;
        if(HP<-0)
            return true;
        return  false;
    }

    public void startLeftMove(float distanceToMove) { //targetX 계산
        this.targetX = x - distanceToMove;
        this.isMoving = true;
    }
```
### MapLoader
#### 동작구성:
보드 위에 아이템과 장애물들을 생성하고 랜덤 배치

#### 핵심 코드
```java
    public void ResetGenerateObjects(WarriorHead warriorHead){
        usedPositions.clear();
        int col = random.nextInt(9);
        randomX = leftBound  (col * 0.64f);
        int row = random.nextInt(9);
        randomY = upperBound  (row * 0.66f);        
        String posKey = generatePositionKey(randomX, randomY); // string으로 hash생성
        warriorHead.setPosition(randomX,randomY);  // 플레이어 위치 랜덤 생성하고 배치
        usedPositions.add(posKey); // 중복확인을 위한 컨테이너

        //아이템, 장애물들 생성
        generateObjects(OBSTACLE_COUNT, MainScene.Layer.obstacle);
        generateObjects(BOMB_COUNT, MainScene.Layer.bomb);
        generateObjects(TOTAL_COUNT -PLAYER_COUNT - OBSTACLE_COUNT - BOMB_COUNT  1, MainScene.Layer.item);
    }

    private void generateObjects(int count, MainScene.Layer layerType) {
      
      //중복없이 랜덤한 값 생성 및 오브젝트 배치
        int attempts = 0; // 현재 시도 횟수
        int attemptLimit = 20;
        int objectsPlaced = 0;
        if (scene == null) return;
        while (objectsPlaced < count && attempts < attemptLimit) {
            for (int i = 0; i < count; i) {
                calculatePositionX();
                calculatePositionY();
                String posKey = generatePositionKey(randomX, randomY);
                if (!usedPositions.contains(posKey)) {
                   scene.add(layerType, createObject(layerType, randomX, randomY));
                    objectsPlaced;
                }
            }
            attempts;
        }

    }
    private IGameObject createObject(MainScene.Layer layerType, float x, float y) { // layer에 맞는 오브젝트들 생성하고 반환 
        String posKey = generatePositionKey(x,y);
        if (layerType == MainScene.Layer.obstacle) {
            usedPositions.add(posKey);
            return new Obstacle(x, y);
        } else if (layerType == MainScene.Layer.bomb) {
            usedPositions.add(posKey);
            return new Bomb(x, y);
        }
        else if (layerType == MainScene.Layer.item) {
            if( random.nextInt(2) == 0){
                usedPositions.add(posKey);
                return new SwordItem(x, y);
            }
            else {
                usedPositions.add(posKey);
                return new ShieldItem(x, y);
            }
        }
        return null;
    }

    private String generatePositionKey(float x, float y) { //해시 생성
        return String.format("%.2f,%.2f", x, y);
    }

    private void calculatePositionX() { // 랜덤한 값  생성
        int col = random.nextInt(9);
        randomX = leftBound  (col * 0.64f);
    }

    private void calculatePositionY() { // 랜덤한 값  생성
        int row = random.nextInt(9);
        randomY = upperBound  (row * 0.66f);
    }
```

### TurnBasedController
#### 동작구성:
1. 턴이 시작됐을 때 동작을 수행하도록 다른 오브젝트들의 함수 호출
2. 턴 종료
#### 핵심 코드
```java
    @Override
    public void update(float elapsedSeconds) {
        if(scene ==null) return;
        
        if(GameStateManager.getInstance().isTurnActive()) { //턴 시작인지 확인
            warrior.ChangeState(Warrior.State.attack); //Warrior는 공격 상태로.
            ArrayList<IGameObject> Slimes = scene.objectsAt(MainScene.Layer.enemy); 모든 슬라임을 가져와서 targetX를 계산해서 왼쪽으로 그만큼 움직여라.
            for (int s = Slimes.size() - 1; s >= 0; s--) {
                Slime slime = (Slime) Slimes.get(s);
                slime.startLeftMove(2.f);
            }

            GameStateManager.getInstance().setTurnActive(false); //턴 종료
            RemoveAllObjects(); // 모든 보드판의 아이템 삭제 (보드판 아이템 재배치를 위해서)
            warriorHead.ResetMove(); // warriorHead는 다시 움직일 수 있도록 초기화
            this.mapLoader.ResetGenerateObjects(warriorHead); // 보드판의 아이템 재배치
        }
    }

    private void RemoveAllObjects(){ // 보드판 위에 모든 오브젝트를 갖고오고 씬에서 제거.

        ArrayList<IGameObject> bombs = scene.objectsAt(MainScene.Layer.bomb);
        for(int b = bombs.size() - 1; b>=0; b--){
            Bomb bomb = (Bomb) bombs.get(b);
            scene.remove(MainScene.Layer.bomb,bomb);
        }
        ArrayList<IGameObject> items = scene.objectsAt(MainScene.Layer.item);
        for (int i = items.size() - 1; i >= 0; i--) {
            IGameObject gobj = items.get(i);
            scene.remove(MainScene.Layer.item,gobj);
        }
        ArrayList<IGameObject> obstacles = scene.objectsAt(MainScene.Layer.obstacle);
        for(int o = obstacles.size() - 1; o>=0; o--) {
            Obstacle obs = (Obstacle) obstacles.get(o);
            scene.remove(MainScene.Layer.obstacle, obs);
        }

    }
```
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
Obstacle 리소스 설정

### CollisionChecker
#### 동작구성:
객체들을 AABB로 충돌 검사한다.
#### 핵심 코드:
WarriorHead와 Obstacle 충돌시 입사각, 반사각 계산
```java
    public void updateDirectionAfterCollision(RectF obstacleRect, RectF headRect) {
        float incidentX = this.warriorHead.GetDx();
        float incidentY = this.warriorHead.GetDy();

        // 충돌 방향에 따른 법선 벡터 설정
        float normalX = 0;
        float normalY = 0;

        float deltaX = (headRect.left  headRect.width() / 2) - (obstacleRect.left  obstacleRect.width() / 2);
        float deltaY = (headRect.top  headRect.height() / 2) - (obstacleRect.top  obstacleRect.height() / 2);

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            // 수평 충돌
            normalX = Math.signum(deltaX);
        } else {
            // 수직 충돌
            normalY = Math.signum(deltaY);
        }

        // 내적 계산
        float dotProduct = incidentX * normalX  incidentY * normalY;

        // 반사 벡터 계산
        float reflectedX = incidentX - 2 * dotProduct * normalX;
        float reflectedY = incidentY - 2 * dotProduct * normalY;

        // 반사된 벡터로 방향 갱신
        this.warriorHead.UpdateDxValue(reflectedX);
        this.warriorHead.UpdateDyValue(reflectedY);
        // 충돌 후 위치 조정을 계산
        float pushBackDistance = 0.05f; // 적절한 값을 실험을 통해 설정
        if (normalX != 0) {
            this.warriorHead.SetX(pushBackDistance * normalX);
        }
        if (normalY != 0) {
            this.warriorHead.SetY(pushBackDistance * normalY);
        }

    }
```
