/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

/**
 *
 * @author PATHIK
 */
public class BalanceEnquiry extends JFrame implements ActionListener {
    
    JButton back;
     String pinnumber;
    BalanceEnquiry(String pinnumber){
         this.pinnumber = pinnumber;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        
        back = new JButton("Back");
        back.setBounds(355, 520,150,30);
        back.addActionListener(this);
        image.add(back);
        
         conn c = new conn();
         int balance = 0;
          try{
            ResultSet rs = c.s.executeQuery("Select * from bank where pin = '"+pinnumber+"'");
            while(rs.next()){
                
                if(rs.getString("type").equals("Deposit")){
                    balance +=Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -=Integer.parseInt(rs.getString("amount"));
                }
            
                 }
          }catch(Exception e){
              System.out.println(e);
          }
          
          JLabel text = new JLabel ("Your Current Account balance is RS "+ balance);
          text.setForeground(Color.WHITE);
          text.setBounds(170,300,400,30);
          image.add(text);
          
          
        setSize(900,900);
        setLocation(300,0);
        
        setVisible(true);
        
    }
    
     public void actionPerformed(ActionEvent ae){
         setVisible(false);
         new Transcation(pinnumber).setVisible(true);
     }
    
    public static void main(String args[]){
        new BalanceEnquiry("");
        
    }
    
}
