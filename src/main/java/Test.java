import mapper.DataProcessing;
import pojo.AbstractUser;

import java.sql.SQLException;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        try {
            DataProcessing.connectToDatabase();
        } catch (SQLException e) {
            System.err.println("操作失败：" + e.getMessage());
            System.err.flush();
        }
        while(true){
            System.out.println("欢迎进入系统，选择菜单");
            System.out.println("1.登录 2.退出");
            Scanner sc = new Scanner(System.in);
            int select1 = sc.nextInt();
            if(select1==1){
                System.out.println("输入用户名");
                String name = sc.next();
                System.out.println("输入密码");
                String password = sc.next();
                AbstractUser tempUser = null;
                try {
                    tempUser = DataProcessing.searchUser(name,password);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }

                if(tempUser!=null){
                    tempUser.showMenu();
                }else{
                    System.out.println("登录失败");
                    System.err.flush();
                }
            }if(select1==2){
                System.exit(0);
            }else{

            }

        }
    }

//    private static void enterMethod(User tempUser) {
//        tempUser.showMenu();
//    }

}
