package pojo;

import mapper.DataProcessing;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public class Administrator extends AbstractUser {
    private String name;
    private String password;
    private String role;

    public Administrator() {
    }

    public Administrator(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }


    @Override
    public void showMenu() {
        while(true){
            System.out.println("*****************菜单******************");
            System.out.println("1.新增用户；2.删除用户；3.修改用户；4.用户列表；" +
                    "5.下载档案；6.档案列表；7.修改个人密码；8.退出登录。");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if(choice==1){
                System.out.println("输入用户名");
                String newName = sc.next();
                System.out.println("输入密码");
                String newPassword = sc.next();
                System.out.println("输入用户类型");
                String newRole = sc.next();
                try {
                    DataProcessing.insertUser(newName, newPassword, newRole);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==2){
                System.out.println("输入用户名");
                String newName = sc.next();
                try {
                    DataProcessing.deleteUser(newName);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==3){
                System.out.println("输入用户名");
                String newName = sc.next();
                System.out.println("输入密码");
                String newPassword = sc.next();
                System.out.println("输入用户类型");
                String newRole = sc.next();
                try {
                    DataProcessing.updateUser(newName, newPassword, newRole);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==4){
                Collection<AbstractUser> allUsers = null;
                try {
                    allUsers = DataProcessing.getAllUsers();
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
                for(AbstractUser u:allUsers){
                    System.out.println(u.toString());
                }
            }
            if(choice==5){
                try {
                    System.out.println("输入档案号");
                    String archiveId = sc.next();
                    String downloadFileAdministrator = "src\\main\\resources\\data\\download_files\\Administrator\\"+this.name+"\\";
                    downloadArchive(archiveId,downloadFileAdministrator);
                } catch (IOException | SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==6){
                try {
                    listAllArchives();
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }if(choice==7){
                System.out.println("输入新密码");
                String newPassword = sc.next();
                try {
                    changeSelfInfo(this.name,newPassword, this.role);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }if(choice==8){
                return;
            }
        }

    }


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
        return "Administrator{name = " + name + ", password = " + password + ", role = " + role + "}";
    }
}
