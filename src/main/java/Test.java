import mapper.DataProcessing;
import pojo.AbstractUser;
import pojo.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
//        try {
//            DataProcessing.connectToDatabase();
//        } catch (SQLException e) {
//            System.err.println("操作失败：" + e.getMessage());
//            System.err.flush();
//        }

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

                User tempUser1 = null;
                AbstractUser tempUser2 = null;
                try {
                    tempUser1 = DataProcessing.searchUser(name,password);
                    String role = null;
                    if (tempUser1 == null) {
                        JOptionPane.showMessageDialog(null, "账号或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                        nameField.setText("");
                        pwdField.setText("");
                        continue;
                    }
                    role = tempUser1.getRole();
                    if ("administrator".equals(role)) {
                        tempUser2 = new Administrator(tempUser1.getId(), tempUser1.getName(), tempUser1.getPassword(), tempUser1.getRole());
                    } else if ("operator".equals(role)) {
                        tempUser2 = new Operator(tempUser1.getId(), tempUser1.getName(), tempUser1.getPassword(), tempUser1.getRole());
                    } else if ("browser".equals(role)) {
                        tempUser2 = new Browser(tempUser1.getId(), tempUser1.getName(), tempUser1.getPassword(), tempUser1.getRole());
                    }
                } catch (SQLException e) {
                    System.err.println("操作失败：" + e.getMessage());
                    System.err.flush();
                }
                if (tempUser2 != null) {
                    JOptionPane.showMessageDialog(null, "登录成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    // 登录成功，退出图形界面，调用showMenu()
                    tempUser2.showMenu();
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








