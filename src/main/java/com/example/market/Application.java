package com.example.market;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {
        final JFrame jFrame = new JFrame();
        jFrame.setSize(new Dimension(100, 100));
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JButton ok = new JButton("Ok");
        ok.setActionCommand("Exit");
        ok.addActionListener(e -> {
            System.out.println(e.getActionCommand());
            jFrame.dispose();
        });
        jFrame.setContentPane(ok);
        jFrame.setVisible(true);
    }
}
