package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class System extends JFrame implements ActionListener {
    public System(){
        setTitle("Wisielec");
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension sizeScreen = tools.getScreenSize();
        int widthScreen = sizeScreen.width;
        int heightScreen = sizeScreen.height;
        setBounds(widthScreen/4, heightScreen/4,widthScreen/2,heightScreen/2);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
