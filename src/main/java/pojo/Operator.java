package pojo;

import mapper.DataProcessing;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Operator extends AbstractUser {//档案录入人员

    private String name;
    private String password;
    private String role;

    public Operator() {
    }

    public Operator(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public void showMenu() {
        while(true){
            System.out.println("*****************菜单******************");
            System.out.println("1.上传档案；2.下载档案；3.档案列表；4.修改个人密码；5.退出登录。");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if(choice==1){
                String archiveId = String.valueOf(DataProcessing.getArchivesLength());
                System.out.println("输入档案创建者");
                String creator = sc.next();
                System.out.println("输入档案描述");
                String description = sc.next();
                System.out.println("输入文件名");
                String fileName = sc.next();
                Archive archive = new Archive(archiveId,creator, LocalDateTime.now(),description,fileName);
                try {
                    DataProcessing.insertArchive(archive);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }
            if(choice==2){
                try {
                    System.out.println("输入档案号");
                    String archiveId = sc.next();
                    String downloadFileOperator = "src\\main\\resources\\data\\download_files\\Operator\\"+this.name+"\\";
                    downloadArchive(archiveId,downloadFileOperator);
                } catch (IOException | SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }if(choice==3){
                try {
                    listAllArchives();
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }if(choice==4){
                System.out.println("输入新密码");
                String newPassword = sc.next();
                try {
                    changeSelfInfo(this.name,newPassword, this.role);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }if(choice==5){
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
        return "Operator{name = " + name + ", password = " + password + ", role = " + role + "}";
    }
}
