package com.ynhj.chatgpt;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @date: 2023/2/27
 * @author: yangniuhaojiang
 * @title: ChatGpt
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class ChatGpt {
    private static final Logger LOGGER = LogManager.getLogger(ChatGpt.class);
    private final static ChatGptApi chatGptApi = ChatGptApi.getInstance();
    private JButton sendBtn;
    private JTextField inputField;
    private JTextArea answerArea;
    private JPanel gptPanel;
    private JLabel title;
    private JButton copyBtn;


    public ChatGpt() {
        answerArea.setLineWrap(true);
        copyBtn.addActionListener(actionEvent -> {
            LOGGER.info("copy btn click");
            answerArea.selectAll();
            answerArea.copy();
            JOptionPane.showMessageDialog(null, "复制成功！", "提示", 1);
        });
        sendBtn.addActionListener(actionEvent -> {
            LOGGER.info("send btn click");
            String text = inputField.getText();
            text = text.replaceAll(" ", "");
            String answer = "";
            try {
                answer = chatGptApi.chat(text);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "gpt 生成错误！", "错误", 0);
            }
            answerArea.setText(answer);
            inputField.setText("");
        });
    }

    public static void main(String[] args) {
        ChatGpt chatGpt = new ChatGpt();
        JFrame frame = new JFrame("ChatGpt");
        frame.setContentPane(chatGpt.gptPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        gptPanel = new JPanel();
        gptPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        sendBtn = new JButton();
        sendBtn.setText("发送");
        gptPanel.add(sendBtn, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inputField = new JTextField();
        inputField.setText("请输入问题");
        inputField.setToolTipText("请输入问题");
        gptPanel.add(inputField, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        answerArea = new JTextArea();
        answerArea.setEditable(false);
        answerArea.setEnabled(true);
        gptPanel.add(answerArea, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        title = new JLabel();
        title.setText("chat gpt问答");
        gptPanel.add(title, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyBtn = new JButton();
        copyBtn.setHorizontalAlignment(0);
        copyBtn.setHorizontalTextPosition(4);
        copyBtn.setText("复制");
        gptPanel.add(copyBtn, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return gptPanel;
    }
}