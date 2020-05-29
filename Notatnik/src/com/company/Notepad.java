package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EventListener;
import java.util.Scanner;


public class Notepad extends JFrame implements ActionListener {
    JTextArea textArea = new JTextArea();
    JPopupMenu popupMenu;
    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;

    public Notepad(){
        setTitle("Notepad");
        //ustawienie lokalizacji i wielkości okna
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension sizeScreen = tools.getScreenSize();
        int widthScreen = sizeScreen.width;
        int heightScreen = sizeScreen.height;
        setBounds(widthScreen/4, heightScreen/4,widthScreen/2,heightScreen/2);


        //tworzenie panelu przewijalnego z paskami przewijania i dodajemy na niego obszar tekstowy
        JScrollPane sp = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(sp,BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(1,2));
        add(buttons,BorderLayout.SOUTH);

        JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelRight = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //dodawanie paneli do panelu zbiorczego
        buttons.add(panelLeft);
        buttons.add(panelRight);

        JButton title = new JButton("Title");
        title.setActionCommand("41");
        JButton sign = new JButton("Sign");
        sign.setActionCommand("42");
        panelLeft.add(title);
        panelLeft.add(sign);
        title.addActionListener(this);
        sign.addActionListener(this);

        JRadioButton gr = new JRadioButton("Green");
        gr.setActionCommand("51");
        JRadioButton yl = new JRadioButton("Yellow");
        yl.setActionCommand("52");
        JRadioButton wh = new JRadioButton("White");
        wh.setActionCommand("53");

        ButtonGroup bg = new ButtonGroup();
        bg.add(gr);
        bg.add(yl);
        bg.add(wh);

        panelRight.add(gr);
        panelRight.add(yl);
        panelRight.add(wh);

        gr.addActionListener(this);
        yl.addActionListener(this);
        wh.addActionListener(this);

        popupMenu = new JPopupMenu();
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);

        popupMenu.add(cut);
        popupMenu.add(copy);
        popupMenu.add(paste);

        textArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == 3){
                    popupMenu.show(textArea, e.getX(), e.getY());
                }
            }
        });

        setJMenuBar(toMenuBar());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(true);
    }
    private JMenuBar toMenuBar(){
        JMenuBar temp = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setActionCommand("11");
        JMenuItem save = new JMenuItem("Save as ...");
        save.addActionListener(this);
        save.setActionCommand("12");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setActionCommand("13");
        mFile.add(open);
        mFile.add(save);
        mFile.addSeparator();
        mFile.add(exit);
        temp.add(mFile);

        JMenu mEdit = new JMenu("Edit");
        JMenuItem smallFont = new JMenuItem("Small Font");
        smallFont.addActionListener(this);
        smallFont.setActionCommand("21");
        JMenuItem bigFont = new JMenuItem("Big Font");
        bigFont.addActionListener(this);
        bigFont.setActionCommand("22");
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(this);
        clear.setActionCommand("23");
        JMenuItem typeFont = new JMenuItem("Font Type");
        typeFont.addActionListener(this);
        typeFont.setActionCommand("24");
        JMenuItem colorFont = new JMenuItem("Font color");
        colorFont.addActionListener(this);
        colorFont.setActionCommand("25");
        mEdit.add(typeFont);
        mEdit.add(colorFont);
        mEdit.add(smallFont);
        mEdit.add(bigFont);
        mEdit.addSeparator();
        mEdit.add(clear);
        temp.add(mEdit);


        JMenu mHelp = new JMenu("Help");
        JMenuItem aboutMe = new JMenuItem("About me");
        aboutMe.addActionListener(this);
        aboutMe.setActionCommand("31");
        mHelp.add(aboutMe);
        temp.add(mHelp);
        return temp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == copy){
            String copyed = textArea.getSelectedText();
            StringSelection selection = new StringSelection(copyed);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection,selection);
        }

        if(e.getSource() == paste) {
            Clipboard clipboard  = Toolkit.getDefaultToolkit().getSystemClipboard();
            try{
                String toPaste = clipboard.getData(DataFlavor.stringFlavor).toString();
                textArea.insert(toPaste, textArea.getCaretPosition());
            }catch(UnsupportedFlavorException ex){
                ex.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource() == cut) {
            String copyed = textArea.getSelectedText();
            textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));
            StringSelection selection = new StringSelection(copyed);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }

        switch(Integer.parseInt(e.getActionCommand()))
        {
            case 11:
            {
                JFileChooser openFileChooser = new JFileChooser();
                int i = openFileChooser.showOpenDialog(this);
                if(i == JFileChooser.APPROVE_OPTION){
                    File file = openFileChooser.getSelectedFile();
                    try{
                        Scanner scanner = new Scanner(file);
                        while(scanner.hasNextLine()){
                            textArea.append(scanner.nextLine()+"\n");
                        }
                        scanner.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            }
            case 12:
            {
                JFileChooser saveFileChooser = new JFileChooser();
                int i = saveFileChooser.showSaveDialog(this);
                File file = saveFileChooser.getSelectedFile();
                try{
                    FileWriter out = new FileWriter(file);
                    out.write(textArea.getText());
                    out.close();
                } catch (IOException ex) {
                    System.out.println("Nie mogę zapisać pliku: "+file.getAbsolutePath());
                    System.out.println("Problem: "+ex);
                }
                break;
            }
            case 13:
            {
                System.exit(0);
                break;
            }
            case 21:
            {
                String text = textArea.getText();
                text = text.toLowerCase();
                textArea.setText(text);
                break;
            }
            case 22:
            {
                String text = textArea.getText();
                text = text.toUpperCase();
                textArea.setText(text);
                break;
            }
            case 23:
            {
                textArea.setText("");
                break;
            }
            case 24:
            {
                int default_size = 16;
                int default_style = Font.PLAIN;
                String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

                GridBagLayout layout = new GridBagLayout();
                layout.columnWidths = new int[] {200};
                layout.rowHeights = new int[] {100,300};

                JFrame container = new JFrame();
                container.setLayout(layout);
                container.setBounds(150,150,400,400);
                container.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                JComboBox<String> comboBoxFontNames = new JComboBox<String>(fonts);
                JTextArea textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(textArea);

                GridBagConstraints comboConstraints = new GridBagConstraints();
                comboConstraints.gridx = 0;
                comboConstraints.gridy = 0;

                comboBoxFontNames.setFont(new Font("Times New Roman", Font.PLAIN, 12));
                container.add(comboBoxFontNames, comboConstraints);

                comboBoxFontNames.addActionListener((actionEvent) -> {
                    String selectedFamilyName = (String)comboBoxFontNames.getSelectedItem();
                    Font selectedFont = new Font(selectedFamilyName, default_style, default_size);
                    textArea.setFont(selectedFont);
                    textArea.repaint();
                });
                container.setVisible(true);
                break;
            }
            case 25:
            {
                String[] colors = {"Red","Blue","Green","Yellow","Pink"};
                JFrame container = new JFrame();
                container.setBounds(150,150,200,100);
                container.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                JComboBox<String> comboBoxFontColors = new JComboBox<>(colors);
                JTextArea textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setViewportView(textArea);

                container.add(comboBoxFontColors);


                comboBoxFontColors.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedColorName = (String) comboBoxFontColors.getSelectedItem();
                        String text = textArea.getText();
                        if(selectedColorName == "Red")
                            textArea.setForeground(Color.RED);
                        else if(selectedColorName == "Blue")
                            textArea.setForeground(Color.BLUE);
                        else if(selectedColorName == "Green")
                            textArea.setForeground(Color.GREEN);
                        else if(selectedColorName == "Yellow")
                            textArea.setForeground(Color.YELLOW);
                        else if(selectedColorName == "Pink")
                            textArea.setForeground(Color.PINK);
                    }
                });
                container.pack();
                container.setVisible(true);
                break;
            }
            case 31:
            {
                JOptionPane.showMessageDialog(this, "Twórca: Michał Ajziuk");
                break;
            }
            case 41:
            {
                setTitle("Nowy plik");
                break;
            }
            case 42:
            {
                textArea.append("Twórcą tego jest Michał Ajziuk , PRAWA ZASTZREŻONE");
                break;
            }
            case 51:
            {
                textArea.setBackground(Color.GREEN);
                break;
            }
            case 52:
            {
                textArea.setBackground(Color.YELLOW);
                break;
            }
            case 53:
            {
                textArea.setBackground(Color.WHITE);
                break;
            }
            default:
            {
                break;
            }
        }
    }
}
