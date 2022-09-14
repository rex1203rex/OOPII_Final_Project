import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class SelectPokerPanel extends JPanel{
    
    private JLabel[] lbe=new JLabel[10];
    private ImageIcon[] imgIcon=new ImageIcon[10]; 
    private Image[] img=new Image[10];
    private RoomPanel roomPanel;
    private int pokerCount=0;
    private int clickedX, clickedY;
    private int[] locationX = new int[10];
    private int[] locationY = new int[10];
    private int[] endX = new int[10];
    private int[] endY = new int[10];
    private boolean[] drawCheck = new boolean[10];

    /************reset*************/
    public void reset(){
        pokerCount=0;
        String number="";
        clickedX=0;
        clickedY=0;
        for(int i=0;i<10;i++){
            number=String.valueOf(i+1); 
            imgIcon[i]=new ImageIcon("Resource/poker"+number+".png");
            img[i] = imgIcon[i].getImage();
            lbe[i]=new JLabel(imgIcon[i]);
            drawCheck[i] = true;
        }
    }
    /******************************/
    public SelectPokerPanel(RoomPanel roomPanel){
        this.roomPanel=roomPanel;
        this.setLayout(new BorderLayout());
        setBounds(0,0,1000,600);
        String number="";
        for(int i=0;i<10;i++){
            number=String.valueOf(i+1); 
            imgIcon[i]=new ImageIcon("Resource/poker"+number+".png");
            img[i] = imgIcon[i].getImage();
            lbe[i]=new JLabel(imgIcon[i]);
            if(i<5){
                locationX[i] = 0+200*i;
                locationY[i] = 0;
                endX[i] = locationX[i]+200;
            }else{
                locationX[i] = 0+200*(i-5);
                locationY[i] = 300;
                endX[i] = locationX[i]+200;
            }
            drawCheck[i] = true;
        }
        this.addMouseListener(new ClickHandler());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i=0;i<10;i++){
            if(drawCheck[i]){
                g.drawImage(img[i],locationX[i],locationY[i],200,300,null);
            }
        }
    }
    public Image getPoker(){
        Image photo = new ImageIcon("Resource/Background.jpeg").getImage();
        return photo;
    }
    private class ClickHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){

            clickedX = e.getX();
            clickedY = e.getY();
            if(400<clickedX && clickedX<600 && 300 <clickedY && clickedY<600){
                roomPanel.pokerMode = false;
                roomPanel.itemList.runeDiaryItem.setVisible(true);
                roomPanel.repaint();
                SelectPokerPanel.this.setVisible(false);      
            }else if(800<clickedX && clickedX<1000 && 300 <clickedY && clickedY<600){
                SelectPokerPanel.this.setVisible(false);  
            }else{
                for(int i=0;i<9;i++){
                    

                    if(i<5){
                        if(pokerCount<=2 && drawCheck[i] && locationX[i]<clickedX && clickedX<endX[i]  && locationY[i]<clickedY && clickedY<locationY[i+5]){
                            pokerCount+=1;
                            if(pokerCount>2){
                                JOptionPane.showMessageDialog(SelectPokerPanel.this,
                                    "You have died!",
                                    "WARNING",
                                    JOptionPane.WARNING_MESSAGE);
                                GamePanel.changePanel("startPanel");
                            }else{
                            
                                JOptionPane.showMessageDialog(SelectPokerPanel.this,
                                    "You only have "+Integer.toString(3-pokerCount)+" chance!",
                                    "WARNING",
                                    JOptionPane.WARNING_MESSAGE);
                                drawCheck[i] = false;
                            }
                            repaint();
                             
                            break;
                        }
                    }else if(i>=5){
                        if(pokerCount<=2 && drawCheck[i] && locationX[i]<clickedX && clickedX<endX[i] && locationY[i]<clickedY){
                            pokerCount+=1;
                            if(pokerCount>2){
                                JOptionPane.showMessageDialog(SelectPokerPanel.this,
                                    "You have died!",
                                    "WARNING",
                                    JOptionPane.WARNING_MESSAGE);
                                GamePanel.changePanel("startPanel");
                            }else{
                            
                                JOptionPane.showMessageDialog(SelectPokerPanel.this,
                                    "You only have "+Integer.toString(3-pokerCount)+" chance!",
                                    "WARNING",
                                    JOptionPane.WARNING_MESSAGE);
                                drawCheck[i] = false;
                                
                            }
                            repaint(); 
                            break;
                        }
                    }

   
                }
            }
        }
    }   
}
