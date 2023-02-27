package com.ynhj.chatgpt;

import javax.swing.*;

/**
 * @date: 2023/2/27
 * @author: yangniuhaojiang
 * @title: MainFrame
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("chat gpt");
        //设置窗口显示尺寸
        setSize(600, 400);
        //设置窗口是否可以关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        JTextField textField = new JTextField("请输入问题", 20);
        JButton sendBtn = new JButton("发送");
        jPanel.add(textField);
        jPanel.add(sendBtn);
        add(jPanel);
        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
