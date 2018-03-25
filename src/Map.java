import java.io.*;

public class Map {

   Map(String inputMapPath){
       mapPath=inputMapPath;
    }

    protected int m;//rows of map
    protected int n;//cols of map
    private int targetNum=0;//已经完成到个数
    private String mapPath;
    protected Player playerPoint;
    private OutSpace outSpace;
    private Wall solidWall;
    private InSpace inSpace;
    private Box box;
    private Target targetPoint;
    private Occupation occupyPoint;
    private PlayerAndTargetOverlap overlapPoint;

    void readMapAndPrint() throws IOException {
        int rowNo = 0;//记录行号
        String readString;

        //读取地图文件
        File inputMap=new File(mapPath);
        FileReader mapReader=new FileReader(mapPath);
        BufferedReader mapBufferReader=new BufferedReader(mapReader);


        outSpace = new OutSpace(' ');
        //char outSpace=' ';//墙外空地

        solidWall = new Wall('\u25A0');
        //char solidWall='*';//墙

        inSpace = new InSpace(' ');
        //char inSpace=' ';//墙内空地

        box = new Box('\u25A1');
        //char lineBox='@';//箱子

        targetPoint = new Target('\u25CE');
        //char targetPoint='#';//目的地

        playerPoint = new Player('\u2605',box);
        //char playerPoint='!';//玩家所在位置

        occupyPoint = new Occupation('\u25A3');
        //char occupyPoint='$';//箱子已经在目的地

        overlapPoint = new PlayerAndTargetOverlap('\u2691');
        int singleChar;
        boolean isFirstRow = true;
        StringBuilder singleRowGraph = new StringBuilder();
        while ((readString = mapBufferReader.readLine()) != null) {
            if (isFirstRow) {
                n = Integer.parseInt(readString.split(" ")[0]);
                m = Integer.parseInt(readString.split(" ")[1]);
                playerPoint.posSign=new String[m+1][n+1];
                isFirstRow = false;
            } else {
                for (int i = 0; i < n; i++) {
                    singleChar = readString.charAt(i) - '0';
                    switch (singleChar) {
                        case 0:
                            singleRowGraph.append(outSpace.shape).append(" ");
                            playerPoint.posSign[rowNo+1][i+1]="outSpace";
                            break;
                        case 1:
                            singleRowGraph.append(solidWall.shape).append(" ");
                            playerPoint.posSign[rowNo+1][i+1]="wall";
                            break;
                        case 2:
                            singleRowGraph.append(inSpace.shape).append(" ");
                            playerPoint.posSign[rowNo+1][i+1]="inSpace";
                            break;
                        case 3:
                            singleRowGraph.append(box.shape).append(" ");
                            playerPoint.posSign[rowNo+1][i+1]="box";
                            break;
                        case 4:
                            singleRowGraph.append(targetPoint.shape).append(" ");
                            playerPoint.posSign[rowNo+1][i+1]="target";
                            targetNum+=1;
                            break;
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            singleRowGraph.append(playerPoint.shape).append(" ");
                            playerPoint.position = rowNo *n + i + 1;
                            //玩家位置按行列来表示,且行和列均从1开始而不是0
                            playerPoint.rowNum = rowNo + 1;
                            playerPoint.colNum = i + 1;
                            playerPoint.posSign[rowNo+1][i+1]="player";
                            break;
                        case 9:
                            singleRowGraph.append(occupyPoint.shape).append(" ");
                            playerPoint.posSign[rowNo+1][i+1]="occupation";
                            break;
                        default:
                            break;
                    }
                }
                rowNo += 1;
                System.out.println(singleRowGraph);
                singleRowGraph = new StringBuilder("");

            }
        }
        mapBufferReader.close();
        mapReader.close();
        playerPoint.initialization(targetNum);
    }

    void writeScreen(String[][] mapString,int m,int n){
        for(int i=1;i<=m;i++){
            StringBuilder updateGame=new StringBuilder();
            for(int j=1;j<=n;j++){
                switch (mapString[i][j]){
                    case "outSpace":
                        updateGame.append(outSpace.shape).append(" ");
                        break;
                    case "wall":
                        updateGame.append(solidWall.shape).append(" ");
                        break;
                    case "inSpace":
                        updateGame.append(inSpace.shape).append(" ");
                        break;
                    case "box":
                        updateGame.append(box.shape).append(" ");
                        break;
                    case "target":
                        updateGame.append(targetPoint.shape).append(" ");
                        break;
                    case "player":
                        updateGame.append(playerPoint.shape).append(" ");
                        break;
                    case "occupation":
                        updateGame.append(occupyPoint.shape).append(" ");
                        break;
                    case "playerAndTargetOverlap":
                        updateGame.append(overlapPoint.shape).append(" ");
                        break;
                    default:
                        break;

                }
            }
            System.out.println(updateGame);
            updateGame=new StringBuilder("");
        }
        System.out.println("\n\n");

    }

}
