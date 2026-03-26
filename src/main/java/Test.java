import mapper.DataProcessing;
import pojo.AbstractUser;
import pojo.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;


//public class Test {
//    public static void main(String[] args) {
//        try {
//            DataProcessing.connectToDatabase();
//        } catch (SQLException e) {
//            System.err.println("操作失败：" + e.getMessage());
//            System.err.flush();
//        }
//        while(true){
//            System.out.println("欢迎进入系统，选择菜单");
//            System.out.println("1.登录 2.退出");
//            Scanner sc = new Scanner(System.in);
//            int select1 = sc.nextInt();
//            if(select1==1){
//                System.out.println("输入用户名");
//                String name = sc.next();
//                System.out.println("输入密码");
//                String password = sc.next();
//                AbstractUser tempUser = null;
//                try {
//                    tempUser = DataProcessing.searchUser(name,password);
//                } catch (SQLException e) {
//                    System.err.println("操作失败：" + e.getMessage());
//                    System.err.flush();
//                }
//
//                if(tempUser!=null){
//                    tempUser.showMenu();
//                }else{
//                    System.out.println("登录失败");
//                    System.err.flush();
//                }
//            }if(select1==2){
//                System.exit(0);
//            }else{
//
//            }
//
//        }
//    }
//
//
//}





public class Test {
    public static void main(String[] args) {
        try {
            DataProcessing.connectToDatabase();
        } catch (SQLException e) {
            System.err.println("操作失败：" + e.getMessage());
            System.err.flush();
        }

        while (true) {
            JTextField nameField = new JTextField();
            nameField.setPreferredSize(new Dimension(250, 25));
            JPasswordField pwdField = new JPasswordField();
            pwdField.setPreferredSize(new Dimension(250, 25));
            Object[] loginPanel = {
                    "账号：", nameField,
                    "密码：", pwdField
            };
            int option = JOptionPane.showConfirmDialog(
                    null,
                    loginPanel,
                    "档案管理系统 - 登录",
                    JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText().trim();
                String password = new String(pwdField.getPassword()).trim();

                // 完全用你自己的登录验证逻辑
                AbstractUser tempUser = null;
                try {
                    tempUser = DataProcessing.searchUser(name,password);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
                if (tempUser != null) {
                    JOptionPane.showMessageDialog(null, "登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    // 登录成功，退出图形界面，调用showMenu()
                    tempUser.showMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                    nameField.setText("");
                    pwdField.setText("");
                }
            }
            else {
                System.exit(0);
            }

        }

    }
}








