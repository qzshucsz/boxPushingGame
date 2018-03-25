import java.io.*;
import java.util.Scanner;

public class Main {
    public static  void main(String[] args) throws IOException {

        //读取地图数据
        String mapPath="/root/桌面/OOP/maps/2.map";
        char command=' ';
        Map myMap=new Map(mapPath);
        myMap.readMapAndPrint();
        System.out.println("Map info:\n"+"rows="+myMap.m+"---------"+"cols="+myMap.n);
        System.out.println("玩家初始位置为："+myMap.playerPoint.position+
                           "\n"+"rows="+myMap.playerPoint.rowNum+"-------"+
                            "cols="+myMap.playerPoint.colNum);
        Scanner inputScanner=new Scanner(System.in);
        System.out.println("请输入操作：" +
                "（温馨提示),可供操作的命令有W(上）、S（下）、A（左）和D（右）,若输入长度大于1,则取首字符为输入命令,其它的全部为非法操作");
        do{
            command=inputScanner.next().charAt(0);
            System.out.println("您输入的操作命令为："+command);
            switch (command){
                case 'w':
                case 'W':
                    myMap.playerPoint.moveTop();
                    myMap.writeScreen(myMap.playerPoint.posSign,myMap.m,myMap.n);
                    break;
                case 'S':
                case 's':
                    myMap.playerPoint.moveBottom();
                    myMap.writeScreen(myMap.playerPoint.posSign,myMap.m,myMap.n);
                    break;
                case 'A':
                case 'a':
                    myMap.playerPoint.moveLeft();
                    myMap.writeScreen(myMap.playerPoint.posSign,myMap.m,myMap.n);
                    break;
                case 'D':
                case 'd':
                    myMap.playerPoint.moveRight();
                    myMap.writeScreen(myMap.playerPoint.posSign,myMap.m,myMap.n);
                    break;
                default:
                    System.out.println("请根据上面的提示，输入正确操作");
                    break;

            }
        }while(command!=27);

        inputScanner.close();





    }
}
