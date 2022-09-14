import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class BellyBox extends JPanel{

    public static boolean isBellyBoxRemove=false;

    private JTextField pwdField = new JTextField(10);
    private ImageIcon bellyBoxIcon = new ImageIcon("Resource/bellyBox.png");
    private ImageIcon panelImg = new ImageIcon("Resource/bellyBox1.png");
    private JLabel bellyBox =new JLabel(panelImg);
    private ItemListPanel itemList;
    // 12/10 new 
    public JPanel pwdPanel;
    public JPanel fieldPanel;
    public JButton[] btn = new JButton[9];
    public JButton btnZero = new JButton("0");

    public JButton clear = new JButton("clear");
    public JButton enter = new JButton("enter");

    public String[] number = {"1","2","3","4","5","6","7","8","9"} ;
    public Integer[] checkNumber = new Integer[4];
    public int count = 0;
    public String input = "";

    public BellyBox(ItemListPanel itemList){
        this.itemList = itemList;
        setLayout(null);
        setBounds(0,0,1000,600);
        bellyBox.setBounds(0,0,1000,600);
        bellyBox.addMouseListener(new PreviousHandler());
        
        pwdField.setEditable(false);
        pwdField.setHorizontalAlignment(SwingConstants.CENTER);
        pwdField.setFont(new Font("serif",Font.PLAIN,50));

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new BorderLayout());
        //fieldPanel.add(new JPanel());
        fieldPanel.add(pwdField,BorderLayout.CENTER);
        //fieldPanel.add(new JPanel());
        fieldPanel.setBounds(600,100,300,100);

        pwdPanel = new JPanel();
        pwdPanel.setBounds(600,200,300,300);
        pwdPanel.setLayout(new GridLayout(4,3,1,1));
        for(int i=0;i<9;i++){
            btn[i]=new JButton(number[i]);
            pwdPanel.add(btn[i]);
            btn[i].addActionListener(new NumberHandler());
        }
        clear.addActionListener(new FunctionListener());
        enter.addActionListener(new FunctionListener());
        pwdPanel.add(clear);
        pwdPanel.add(btnZero);
        pwdPanel.add(enter);
        btnZero.addActionListener(new NumberHandler());
        this.add(fieldPanel);
        this.add(pwdPanel);
        this.add(bellyBox);
    }

    public void clearAll(){
        count = 0 ;
        pwdField.setText("");
        input = ""; 

        for(int i =0 ; i<4;i++){
            checkNumber[i] = 0 ;
        }
    }
    private class PreviousHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            BellyBox.this.clearAll();
            GamePanel.changePreviousPanel();
        }
    }
    private class FunctionListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if (event.getActionCommand() == "clear") {
                for(int i =0 ; i<4;i++){
                    checkNumber[i] = 0 ;
                }
                BellyBox.this.clearAll();
            }else if (event.getActionCommand() == "enter"){
                if(checkNumber[0]==8 && checkNumber[1]==2 
                    && checkNumber[2]==0 && checkNumber[3]==3){
                    itemList.remove(itemList.bellyBoxItem);
                    BellyBox.isBellyBoxRemove=true;
                    itemList.revalidate();
                    itemList.bellyBoxItem.setVisible(false);
                    itemList.keyItem.setVisible(true);
                    GamePanel.changePreviousPanel();
                    BellyBox.this.clearAll();
                }else{
                    JOptionPane.showMessageDialog(BellyBox.this,"You are wrong ","WARNING",JOptionPane.WARNING_MESSAGE);
                    BellyBox.this.clearAll();
                }
            }

        }
    }
    private class NumberHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if (count < 4){
                checkNumber[count] = Integer.valueOf(event.getActionCommand());
                input = input + event.getActionCommand();
                pwdField.setText(input);
                count += 1;
            }
        }
    }
}