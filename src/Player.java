public class Player extends Role implements Move {
    protected String[][] posSign;//位置标记
    private Box box;
    private int targetNumber;
    private int counter;//记录已经有箱子到达的目标位置的个数

    Player(char playerShape,Box boxInMap){
        shape=playerShape;
        box=boxInMap;
    }

    void initialization(int targetNum){
        targetNumber=targetNum;
    }

    @Override
    public void moveTop() {
        if(posSign[rowNum-1][colNum]=="inSpace"){
            posSign[rowNum-1][colNum] = "player";
            updateTopMove(posSign);
        }
        //2、box
        else if(posSign[rowNum-1][colNum]=="box"){
            //箱子只有在上边是inSpace或target两种情况下可以移动
            //(1)inSpace
            if(posSign[rowNum-2][colNum]=="inSpace"){
                posSign[rowNum-1][colNum]="player";
                posSign[rowNum-2][colNum]="box";
                updateTopMove(posSign);
                box.rowNum-=1;
            }
            //(2)target
            else if(posSign[rowNum-2][colNum]=="target"){
                posSign[rowNum-1][colNum]="player";
                posSign[rowNum-2][colNum]="occupation";
                updateTopMove(posSign);
                box.rowNum-=1;
                judgeWin();
            }
            //其它情况
            else{
                System.out.println("无法向上移动");
            }
        }
//3、target
        else if(posSign[rowNum-1][colNum]=="target"){
            posSign[rowNum-1][colNum]="playerAndTargetOverlap";
            updateTopMove(posSign);
        }
//4、occupation
        else if(posSign[rowNum-1][colNum]=="occupation"){
            //occupation可移动的情况只有上边是inSpace或target两种情况
            //(1)inSpace
            if(posSign[rowNum-2][colNum]=="inSpace"){
                posSign[rowNum-1][colNum]="playerAndTargetOverlap";
                posSign[rowNum-2][colNum]="box";
                updateTopMove(posSign);
            }
            //(2)target
            else if(posSign[rowNum-2][colNum]=="target"){
                posSign[rowNum-1][colNum]="playerAndTargetOverlap";
                posSign[rowNum-2][colNum]="occupation";
                updateTopMove(posSign);
            }
            //其它情况
            else {
                System.out.println("无法向上移动");
            }
        }else{
            System.out.println("无法向上移动");
        }


    }

    @Override
    public void moveBottom() {
        System.out.println("row:"+rowNum+"col:"+colNum);
        if(posSign[rowNum+1][colNum]=="inSpace"){
            posSign[rowNum+1][colNum] = "player";
            updateBottomMove(posSign);
        }
//2、box
        else if(posSign[rowNum+1][colNum]=="box"){
            //箱子只有在下边是inSpace或target两种情况下可以移动
            //(1)inSpace
            if(posSign[rowNum+2][colNum]=="inSpace"){
                posSign[rowNum+1][colNum]="player";
                posSign[rowNum+2][colNum]="box";
                updateBottomMove(posSign);
                box.rowNum+=1;
            }
            //(2)target
            else if(posSign[rowNum+2][colNum]=="target"){
                posSign[rowNum+1][colNum]="player";
                posSign[rowNum+2][colNum]="occupation";
                updateBottomMove(posSign);
                box.rowNum+=1;
                judgeWin();

            }
            //其它情况
            else{
                System.out.println("无法向下移动");
            }
        }
//3、target
        else if(posSign[rowNum+1][colNum]=="target"){
            posSign[rowNum+1][colNum]="playerAndTargetOverlap";
            updateBottomMove(posSign);
        }
//4、occupation
        else if(posSign[rowNum+1][colNum]=="occupation"){
            //occupation可移动的情况只有下边是inSpace或target两种情况
            //(1)inSpace
            if(posSign[rowNum+2][colNum]=="inSpace"){
                posSign[rowNum+1][colNum]="playerAndTargetOverlap";
                posSign[rowNum+2][colNum]="box";
                updateBottomMove(posSign);
            }
            //(2)target
            else if(posSign[rowNum+2][colNum]=="target"){
                posSign[rowNum+1][colNum]="playerAndTargetOverlap";
                posSign[rowNum+2][colNum]="occupation";
                updateBottomMove(posSign);
            }
            //其它情况
            else {
                System.out.println("无法向下移动");
            }
        }else{
            System.out.println("无法向下移动");
        }
    }

    @Override
    public void moveLeft() {
        System.out.println("row:"+rowNum+"col:"+colNum);
        //判断玩家左边边位置的情况,可能是以下情况:
        //  1、空格inSpace
        //  2、箱子box
        //  3、目标点 target
        //  4、已有箱子占领的目标点 occupation
        //  5、其它不可移动的情况
        //1、inSpace
        if(posSign[rowNum][colNum-1]=="inSpace"){
            posSign[rowNum][colNum-1] = "player";
            updateLeftMove(posSign);
        }
        //2、box
        else if(posSign[rowNum][colNum-1]=="box"){
            //箱子只有在左边是inSpace或target两种情况下可以移动
            //(1)inSpace
            if(posSign[rowNum][colNum-2]=="inSpace"){
                posSign[rowNum][colNum-1]="player";
                posSign[rowNum][colNum-2]="box";
                updateLeftMove(posSign);
                box.colNum-=1;
            }
            //(2)target
            else if(posSign[rowNum][colNum-2]=="target"){
                posSign[rowNum][colNum-1]="player";
                posSign[rowNum][colNum-2]="occupation";
                updateLeftMove(posSign);
                box.colNum-=1;
                judgeWin();

            }
            //其它情况
            else{
                System.out.println("无法向左移动");
            }
        }
        //3、target
        else if(posSign[rowNum][colNum-1]=="target"){
            posSign[rowNum][colNum-1]="playerAndTargetOverlap";
            updateLeftMove(posSign);
        }
        //4、occupation
        else if(posSign[rowNum][colNum-1]=="occupation"){
            //occupation可移动的情况只有左边是inSpace或target两种情况
            //(1)inSpace
            if(posSign[rowNum][colNum-2]=="inSpace"){
                posSign[rowNum][colNum-1]="playerAndTargetOverlap";
                posSign[rowNum][colNum-2]="box";
                updateLeftMove(posSign);
            }
            //(2)target
            else if(posSign[rowNum][colNum-2]=="target"){
                posSign[rowNum][colNum-1]="playerAndTargetOverlap";
                posSign[rowNum][colNum-2]="occupation";
                updateLeftMove(posSign);
            }
            //其它情况
            else {
                System.out.println("无法向左移动");
            }
        }else{
            System.out.println("无法向左移动");
        }

    }

    @Override
    public void moveRight() {
        System.out.println("row:"+rowNum+"col:"+colNum);
        //判断玩家右边位置的情况,可能是以下情况:
        //  1、空格inSpace
        //  2、箱子box
        //  3、目标点 target
        //  4、已有箱子占领的目标点 occupation
        //  5、其它不可移动的情况
        //1、inSpace
        if(posSign[rowNum][colNum+1]=="inSpace"){
            posSign[rowNum][colNum+1] = "player";
            updateRightMove(posSign);
        }
        //2、box
        else if(posSign[rowNum][colNum+1]=="box"){
            //箱子只有在右边是inSpace或target两种情况下可以移动
            //(1)inSpace
            if(posSign[rowNum][colNum+2]=="inSpace"){
                posSign[rowNum][colNum+1]="player";
                posSign[rowNum][colNum+2]="box";
                updateRightMove(posSign);
                box.colNum+=1;
            }
            //(2)target
            else if(posSign[rowNum][colNum+2]=="target"){
                posSign[rowNum][colNum+1]="player";
                posSign[rowNum][colNum+2]="occupation";
                updateRightMove(posSign);
                box.colNum+=1;
                judgeWin();

            }
            //其它情况
            else{
                System.out.println("无法向右移动");
            }
        }
        //3、target
        else if(posSign[rowNum][colNum+1]=="target"){
            posSign[rowNum][colNum+1]="playerAndTargetOverlap";
            updateRightMove(posSign);
        }
        //4、occupation
        else if(posSign[rowNum][colNum+1]=="occupation"){
            //occupation可移动的情况只有右边是inSpace或target两种情况
            //(1)inSpace
            if(posSign[rowNum][colNum+2]=="inSpace"){
                posSign[rowNum][colNum+1]="playerAndTargetOverlap";
                posSign[rowNum][colNum+2]="box";
                updateRightMove(posSign);
            }
            //(2)target
            else if(posSign[rowNum][colNum+2]=="target"){
                posSign[rowNum][colNum+1]="playerAndTargetOverlap";
                posSign[rowNum][colNum+2]="occupation";
                updateRightMove(posSign);
            }
            //其它情况
            else {
                System.out.println("无法向右移动");
            }
        }
        //其它情况
        else{
            System.out.println("无法向右移动");
        }
    }


    private void updateTopMove(String[][] posSign){
        setPlayerPos(posSign);
        rowNum-=1;
        System.out.println("updateTopMove------row:"+rowNum+"col:"+colNum);
    }

    private void updateBottomMove(String[][] posSign){
       setPlayerPos(posSign);
        rowNum+=1;
        System.out.println("updateBottomMove------row:"+rowNum+"col:"+colNum);
    }

    private void updateLeftMove(String[][] posSign){
        setPlayerPos(posSign);
        colNum-=1;
        System.out.println("updateLeftMove-----row:"+rowNum+"col:"+colNum);



    }

    private void updateRightMove(String[][] posSign){
        setPlayerPos(posSign);
        colNum+=1;
        System.out.println("updateRightMove-----row:"+rowNum+"col:"+colNum);
    }


    private void setPlayerPos(String[][] posSign){
        if(posSign[rowNum][colNum]=="playerAndTargetOverlap"){
            setCurrentPosToTarget(posSign);
        }else if(posSign[rowNum][colNum]=="player"){
            setCurrentPosInSpace(posSign);
        }
    }

    private void setCurrentPosToTarget(String[][] posSign){
        posSign[rowNum][colNum] = "target";
    }

    private void setCurrentPosInSpace(String[][] posSign){
        posSign[rowNum][colNum] = "inSpace";
    }

    private void judgeWin(){
        counter++;
        if(counter==targetNumber){
            System.out.println("胜利！！！");
        }

    }



}
