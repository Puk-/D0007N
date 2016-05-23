package ahs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.w3c.dom.Document;
import java.io.*;
 
/**
 *  Start AppView, should be removed
 * @author Filip
 */
public class StartView implements ActionListener{    
    
    private AppView appView;
   
    private JFrame frame;
    private JLabel jL1, jL2;
    private JButton jB1, jB2;
    private final JPanel jPanel = new JPanel();
    private SpringLayout layout;
    private Container contentPane;
   
   
    public StartView(){
       
        appView = new AppView();
        
        frame = new JFrame("Welcome");
        frame.setSize(200,100);
//        frame.setPreferredSize(new Dimension(333, 410));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       
        contentPane = frame.getContentPane();
        layout = new SpringLayout();
        contentPane.setLayout(layout);
       
        jL1 = new JLabel("Leader");
        //jL2 = new JLabel("Leader");
       
        //jB1 = new JButton("Employee");
        //jB1.addActionListener(this);
        jB2 = new JButton("Leader");
        jB2.addActionListener(this);
 
        //jPanel.add(jL1);
        //jPanel.add(jL2);
        //jPanel.add(jB1);
        jPanel.add(jB2);
       
        frame.add(jPanel);
        frame.setVisible(false);
        appView.run();
       
    }// slut på konstruktor
   
    public void actionPerformed(ActionEvent e) {
        String a = e.getActionCommand();
        if(a.equals("Employee")){
                        
        }
        if(a.equals("Leader")){
            frame.setVisible(false);
            appView.run();
        }
    }
   
}