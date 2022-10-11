/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author Deepak
 */
public class Notepad implements ActionListener
{
    JFrame jf;
    
    JMenuBar menubar;
    JMenu file;
    JMenuItem neww, open, save, saveas, exit, page_setup, print;
    
    JTextArea textarea;
    
    String title="Untitled - Notepad";
    
    File filee;
    
    public Notepad()
    {
        try
        {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        jf=new JFrame(title);
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        menubar=new JMenuBar();
        
        file=new JMenu("File");
        
        neww=new JMenuItem("New");
        neww.addActionListener(this);
        file.add(neww);
        
        open=new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        
        save=new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        
        saveas=new JMenuItem("Save As");
        saveas.addActionListener(this);
        file.add(saveas);
        
        file.addSeparator();
        
        page_setup=new JMenuItem("Page Setup");
        page_setup.addActionListener(this);
        file.add(page_setup);
        
        print=new JMenuItem("Print...");
        print.addActionListener(this);
        file.add(print);
        
        file.addSeparator();
        
        exit=new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);
        
        menubar.add(file);
        
        jf.setJMenuBar(menubar);
        
        textarea=new JTextArea();
        JScrollPane scollpane=new JScrollPane(textarea);
        scollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jf.add(scollpane);
        
        jf.setVisible(true);
    }
    public static void main(String[] args)
    {
        new Notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==neww)
        {
            newNotepad();
        }
        if(e.getSource()==exit)
        {
            System.exit(0);
        }
        if(e.getSource()==save)
        {
            save();
        }
    }
    
    public void newNotepad()
    {
        String text=textarea.getText();
        if(!text.equals(""))
        {
            int i=JOptionPane.showConfirmDialog(jf, "Do you want to save this file ?");
            if(i==0)
            {
                save();
                setTitle(title);
                textarea.setText("");
            }
            else if(i==1)
            {
                textarea.setText("");
            }
        }
    }
    public void save()
    {
        try
        {
            JFileChooser filechooser=new JFileChooser();
            int i=filechooser.showSaveDialog(jf);
            
            if(i==0)
            {
                filee=filechooser.getSelectedFile();
            
                String text=textarea.getText();
                byte[] b=text.getBytes();
                FileOutputStream fos=new FileOutputStream(filee);
                fos.write(b);
                
                setTitle(filee.getName());
                
                fos.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    void setTitle(String title)
    {
        jf.setTitle(title);
    }
}
