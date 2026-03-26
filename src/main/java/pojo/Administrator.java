package pojo;

import mapper.DataProcessing;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

            Object[] menuButtons = {
                    "1.新增用户",
                    "2.删除用户",
                    "3.修改用户",
                    "4.用户列表",
                    "5.下载档案",
                    "6.档案列表",
                    "7.修改个人密码",
                    "8.退出登录"
            };

            int result = JOptionPane.showOptionDialog(
                    null,
                    "*****************菜单******************\n1.新增用户；2.删除用户；3.修改用户；4.用户列表；\n5.下载档案；6.档案列表；7.修改个人密码；8.退出登录。",
                    "档案管理系统-管理员菜单",
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
                String newName = JOptionPane.showInputDialog("输入用户名");
                if (newName == null) continue;

                String newPassword = JOptionPane.showInputDialog("输入密码");
                if (newPassword == null) continue;

                // ============== 【核心修改：角色输入框 → 3个按钮选择】 ==============
                Object[] roleButtons = {"Administrator(管理员)", "Operator(录入员)", "Browser(浏览者)"};
                int roleResult = JOptionPane.showOptionDialog(
                        null,
                        "请选择用户角色",
                        "选择角色",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        roleButtons,
                        roleButtons[0]
                );
                // 点取消/关闭，返回菜单
                if (roleResult == JOptionPane.CLOSED_OPTION) continue;
                // 赋值对应角色
                String newRole;
                switch (roleResult) {
                    case 0: newRole = "Administrator"; break;
                    case 1: newRole = "Operator"; break;
                    case 2: newRole = "Browser"; break;
                    default: continue;
                }

                try {
                    DataProcessing.insertUser(newName, newPassword, newRole);
                    JOptionPane.showMessageDialog(null, "新增用户成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                    JOptionPane.showMessageDialog(null, "新增用户失败："+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(choice==2){
                String newName = JOptionPane.showInputDialog("输入用户名");
                if (newName == null) continue;

                try {
                    DataProcessing.deleteUser(newName);
                    JOptionPane.showMessageDialog(null, "删除用户成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                    JOptionPane.showMessageDialog(null, "删除用户失败："+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }

            if(choice==3){
                String newName = JOptionPane.showInputDialog("输入用户名");
                if (newName == null) continue;

                String newPassword = JOptionPane.showInputDialog("输入密码");
                if (newPassword == null) continue;

                // ============== 【核心修改：角色输入框 → 3个按钮选择】 ==============
                Object[] roleButtons = {"Administrator(管理员)", "Operator(录入员)", "Browser(浏览者)"};
                int roleResult = JOptionPane.showOptionDialog(
                        null,
                        "请选择用户角色",
                        "选择角色",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        roleButtons,
                        roleButtons[0]
                );
                // 点取消/关闭，返回菜单
                if (roleResult == JOptionPane.CLOSED_OPTION) continue;
                // 赋值对应角色
                String newRole;
                switch (roleResult) {
                    case 0: newRole = "Administrator"; break;
                    case 1: newRole = "Operator"; break;
                    case 2: newRole = "Browser"; break;
                    default: continue;
                }

                try {
                    DataProcessing.updateUser(newName, newPassword, newRole);
                    JOptionPane.showMessageDialog(null, "修改用户成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                    JOptionPane.showMessageDialog(null, "修改用户失败："+e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
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

                // 新增：图形界面表格展示
                if (allUsers == null || allUsers.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "当前没有用户记录", "提示", JOptionPane.INFORMATION_MESSAGE);
                    continue;
                }

                // 表格列名
                String[] columns = {"用户名", "密码", "角色"};
                DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

                // 填充用户数据
                for (AbstractUser user : allUsers) {
                    tableModel.addRow(new String[]{
                            user.getName(),
                            user.getPassword(),
                            user.getRole()
                    });
                }

                // 创建表格
                JTable table = new JTable(tableModel);
                table.setRowHeight(25);
                table.getColumnModel().getColumn(0).setPreferredWidth(150);
                table.getColumnModel().getColumn(1).setPreferredWidth(150);
                table.getColumnModel().getColumn(2).setPreferredWidth(150);

                // 滚动面板
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setPreferredSize(new Dimension(500, 300));

                // 弹窗展示
                JOptionPane.showMessageDialog(
                        null,
                        scrollPane,
                        "用户列表（总数：" + allUsers.size() + "）",
                        JOptionPane.PLAIN_MESSAGE
                );
            }



            if(choice==5){
                try {
                    String archiveId = JOptionPane.showInputDialog("输入档案号");
                    if (archiveId == null) continue;

                    String downloadFileAdministrator = "src\\main\\resources\\data\\download_files\\Administrator\\"+this.name+"\\";
                    downloadArchive(archiveId,downloadFileAdministrator); // 父类已图形化
                } catch (IOException | SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }

            if(choice==6){
                try {
                    listAllArchives(); // 父类已改成表格展示
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
            }

            if(choice==7){
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

            if(choice==8){
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
