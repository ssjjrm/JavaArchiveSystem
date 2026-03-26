package pojo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.*;


public class Browser extends AbstractUser {
    private String name;
    private String password;
    private String role;

    public Browser() {
    }

    public Browser(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

//    @Override
//    public void showMenu()  {
//        while(true){
//            System.out.println("*****************菜单******************");
//            System.out.println("1.下载档案；2.档案列表；3.修改个人密码；4.退出登录。");
//            Scanner sc = new Scanner(System.in);
//            int choice = sc.nextInt();
//            if(choice==1){
//                try {
//                    System.out.println("输入档案号");
//                    String archiveId = sc.next();
//                    String downloadFileBrowser = "src\\main\\resources\\data\\download_files\\Browser\\"+this.name+"\\";
//                    downloadArchive(archiveId,downloadFileBrowser);
//                } catch (IOException | SQLException e) {
//                    System.err.println("操作失败：" + e.getMessage());
//                    System.err.flush();
//                }
//            }
//            if(choice==2){
//                try {
//                    listAllArchives();
//                } catch (SQLException e) {
//                    System.err.println("操作失败：" + e.getMessage());
//                    System.err.flush();
//                }
//            }if(choice==3){
//                System.out.println("输入新密码");
//                String newPassword = sc.next();
//                try {
//                    changeSelfInfo(this.name,newPassword, this.role);
//                } catch (SQLException e) {
//                    System.err.println("操作失败：" + e.getMessage());
//                    System.err.flush();
//                }
//            }if(choice==4){
//                return;
//            }
//        }
//
//    }



    /// ////////////////////////////新方法//////////////////////////


    @Override
    public void showMenu()  {
        while(true){
            System.out.println("*****************菜单******************");
            System.out.println("1.下载档案；2.档案列表；3.修改个人密码；4.退出登录。");
            Scanner sc = new Scanner(System.in); // 完全保留不删除

            // 核心修改：先获取返回值，判断关闭按钮
            // 自定义按钮文本
            Object[] menuButtons = {
                    "1.下载档案",
                    "2.档案列表",
                    "3.修改个人密码",
                    "4.退出登录"
            };

            int result = JOptionPane.showOptionDialog(
                    null,
                    "*****************菜单******************\n1.下载档案；2.档案列表；3.修改个人密码；4.退出登录。",
                    "档案管理系统-浏览者菜单",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, // 关键：去掉问号图标
                    null,
                    menuButtons, // 自定义的4个按钮
                    menuButtons[0] // 默认选中第一个按钮
            );

            // 判断是否点击了右上角叉键（关闭按钮）
            if (result == JOptionPane.CLOSED_OPTION) {
                System.exit(0); // 直接退出整个程序
            }

            // 点击按钮的话，再+1赋值给choice
            int choice = result + 1;

            if(choice==1){
                try {
                    System.out.println("输入档案号");
                    String archiveId = JOptionPane.showInputDialog("输入档案号");
                    if (archiveId == null) continue; // 点取消直接回到菜单，不执行后续逻辑

                    String downloadFileBrowser = "src\\main\\resources\\data\\download_files\\Browser\\"+this.name+"\\";
                    downloadArchive(archiveId,downloadFileBrowser);
                } catch (IOException | SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==2){
                try {
                    listAllArchives();
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==3){
                System.out.println("输入新密码");
                String newPassword = JOptionPane.showInputDialog("输入新密码");
                if (newPassword == null) continue; // 点取消直接回到菜单，不执行后续逻辑

                try {
                    changeSelfInfo(this.name,newPassword, this.role);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==4){
                return;
            }
        }
    }












    /// //////////////////////////////////////////////////////////





    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return "Browser{name = " + name + ", password = " + password + ", role = " + role + "}";
    }
}
