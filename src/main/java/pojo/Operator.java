package pojo;

import mapper.DataProcessing;

import javax.swing.*;
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

            Object[] menuButtons = {
                    "1.上传档案",
                    "2.下载档案",
                    "3.档案列表",
                    "4.修改个人密码",
                    "5.退出登录"
            };

            int result = JOptionPane.showOptionDialog(
                    null,
                    "*****************菜单******************\n1.上传档案；2.下载档案；3.档案列表；4.修改个人密码；5.退出登录。",
                    "档案管理系统-录入员菜单",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    menuButtons,
                    menuButtons[0]
            );

            // 点右上角叉 → 直接退出整个程序
            if (result == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }

            int choice = result + 1;

            if(choice==1){
                String archiveId = String.valueOf(DataProcessing.getArchivesLength());

                // 图形界面输入 创建者
                String creator = JOptionPane.showInputDialog("输入档案创建者");
                if (creator == null) continue;

                // 图形界面输入 描述
                String description = JOptionPane.showInputDialog("输入档案描述");
                if (description == null) continue;

                // 图形界面输入 文件名
                String fileName = JOptionPane.showInputDialog("输入文件名");
                if (fileName == null) continue;

                Archive archive = new Archive(archiveId,creator, LocalDateTime.now(),description,fileName);
                try {
                    DataProcessing.insertArchive(archive);
                    JOptionPane.showMessageDialog(null, "上传成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                    JOptionPane.showMessageDialog(null, "上传失败："+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(choice==2){
                try {
                    // 图形界面输入档案号
                    String archiveId = JOptionPane.showInputDialog("输入档案号");
                    if (archiveId == null) continue;

                    String downloadFileOperator = "src\\main\\resources\\data\\download_files\\Operator\\"+this.name+"\\";
                    downloadArchive(archiveId,downloadFileOperator); // 父类已图形化
                } catch (IOException | SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }

            if(choice==3){
                try {
                    listAllArchives(); // 父类已改成表格展示
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }

            if(choice==4){
                // 图形界面输入新密码
                String newPassword = JOptionPane.showInputDialog("输入新密码");
                if (newPassword == null) continue;

                try {
                    changeSelfInfo(this.name,newPassword, this.role);
                    JOptionPane.showMessageDialog(null, "密码修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                    JOptionPane.showMessageDialog(null, "修改失败："+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(choice==5){
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
