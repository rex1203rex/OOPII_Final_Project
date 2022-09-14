import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class FloorPanel extends JPanel{
	
	private Image floorImg;

	/*********pig_variable************/

	private String floor = "floor.jpg";
    private JButton top;
    private int originalX = 0;
    private int originalY = 0;

    /*********pocheng_variable***********/

    private ItemListPanel itemList;
    
    /*********pig_variable***************************/
    private int firstNum = 0;
    private int secondNum = 0;
    private int thirdNum = 0;
    private int fourthNum = 0;

    private Image scareBoxImg = new ImageIcon("Resource/scareBox.png").getImage();
    private Image scareBoxOpenImg = new ImageIcon("Resource/scareBoxOpen.png").getImage();
    private Image dollImg = new ImageIcon("Resource/doll.png").getImage();
    //
    private ImageIcon keylockImgIcon = new ImageIcon("Resource/keylock.png");
    private Image keylockImg = new ImageIcon("Resource/keylock.png").getImage();
    private JLabel keylockLabel = new JLabel(keylockImgIcon);
    //
    private JPanel pwdPanel;

    private JButton first;
    private JButton second;
    private JButton third;
    private JButton fourth;
    /************************************/

    private boolean scareBoxMode = true;
    private boolean scareBoxOpenMode = false; 
    private boolean dollMode = false;
    private boolean isfirst = true ;
    private boolean pwdPanelMode = false;

    /*****flash*****/
    public static final int RADIUS = 200;
    public static Point mousePoint = null;   

    /**********reset************/
    public void reset(){
        scareBoxMode = true;
        scareBoxOpenMode = false; 
        dollMode = false;
        isfirst = true ;
        pwdPanelMode = false;
        this.remove(keylockLabel);
        pwdPanel.setVisible(false);
        this.revalidate();
    }
    /***************************/

	public FloorPanel(ItemListPanel itemList){      
        this.itemList=itemList;
        floorImg = setImg(floor);       
        setLayout(null);
        setBounds(0,0,1100,600);

        /*************pig 12/6****************/

        pwdPanel = new JPanel();
        //
        keylockLabel.setBounds(365,190,keylockImg.getWidth(null),keylockImg.getHeight(null));
        pwdPanel.setBounds(400,300,200,50);
        //
        pwdPanel.setLayout(new GridLayout(1,4));
        this.displayButton();
        
        addMouseListener(new MouseClickHandler());  /******to close the pwdPanel*******/
        /*****flash*****/
        if(!GamePanel.isTurnOn){
            this.flashLight();   
        }

    }


    /*****flash*****/
    public void flashLight(){
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePoint = e.getPoint();
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mousePoint = null;
                repaint();
            }
        };
        addMouseMotionListener(mouseHandler);
        addMouseListener(mouseHandler);
    
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);


        g.drawImage(floorImg,originalX,originalY,getWidth(),getHeight(),null);
        if(scareBoxMode){
            g.drawImage(scareBoxImg,350,350,300,207,null); // location, size
        }

        if(scareBoxOpenMode){
            g.drawImage(scareBoxOpenImg,350,350,300,207,null); 
        }

        if(dollMode && isfirst ){
            g.drawImage(dollImg,270,270,dollImg.getWidth(null)/2,dollImg.getHeight(null)/2,null);
        }


        /*****flash*****/
        if(!GamePanel.isTurnOn){
            Graphics2D g2d = (Graphics2D) g.create();
            Paint paint = Color.BLACK;
            if (mousePoint != null) {
                paint = new RadialGradientPaint(
                    mousePoint,
                    RADIUS,
                    new float[]{0, 1f},
                    new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 255)});
            }
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }

    }	
    /*********pig*********/
    public class btnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == top){
        	    GamePanel.changePanel("playPanel");
        	}

            if(e.getSource() == first){
                if(firstNum == 9){
                    firstNum = 0;
                }else{
                    firstNum = firstNum + 1;
                }
                first.setText(String.valueOf(firstNum));
            }
            if(e.getSource() == second){
                if(secondNum == 9){
                    secondNum = 0;
                }else{
                    secondNum = secondNum + 1;
                }
                second.setText(String.valueOf(secondNum));
            } 
            if(e.getSource() == third){
                if(thirdNum == 9){
                    thirdNum = 0;
                }else{
                    thirdNum = thirdNum + 1;
                }
                third.setText(String.valueOf(thirdNum));
            } 
            if(e.getSource() == fourth){
                if(fourthNum == 9){
                    fourthNum = 0;
                }else{
                    fourthNum = fourthNum + 1;
                }
                fourth.setText(String.valueOf(fourthNum));
            }
            if(firstNum == 3 && secondNum == 9 && thirdNum == 7 && fourthNum == 3 && !pwdPanelMode){
                scareBoxMode = false;
                pwdPanel.setVisible(false);
                scareBoxOpenMode = true;
                dollMode = true;
                FloorPanel.this.remove(keylockLabel);
                revalidate();
                repaint();
            }else if( pwdPanelMode ){
                pwdPanelMode = false;

            } 
        }

    }
    /*******Kuo********/
    public void displayButton(){
        top = new JButton(new ImageIcon("Resource/toparrow.png"));
        top.setBounds(getWidth()/2-80,20,50,50);
        top.addActionListener(new btnListener());
        add(top);
        top.setContentAreaFilled(false);
        top.setBorderPainted(false);

        first = new JButton(Integer.toString(firstNum));
        first.addActionListener(new btnListener());
        second = new JButton(Integer.toString(secondNum));
        second.addActionListener(new btnListener());
        third = new JButton(Integer.toString(thirdNum));
        third.addActionListener(new btnListener());
        fourth = new JButton(Integer.toString(fourthNum));
        fourth.addActionListener(new btnListener());
        pwdPanel.add(first);
        pwdPanel.add(second);
        pwdPanel.add(third);
        pwdPanel.add(fourth);

        add(pwdPanel);
        pwdPanel.setVisible(false);
        
    }
    public Image setImg(String image){
        Image picture = new ImageIcon("Resource/"+image).getImage();
        return picture;
    }

    /*******************12/6 pig method********************/
    private class MouseClickHandler extends MouseAdapter{
        public void mouseClicked(MouseEvent e){

            int x=e.getX(),y=e.getY();

            if(350<x && x<650 && 350<y && y<557 && scareBoxMode == true){
                //
                FloorPanel.this.add(keylockLabel);
                pwdPanel.setVisible(true);
                FloorPanel.this.revalidate();
                repaint();
            }else{
                firstNum = 0;
                secondNum = 0;
                thirdNum = 0;
                fourthNum = 0;
                first.setText(Integer.toString(firstNum));
                second.setText(Integer.toString(secondNum));
                third.setText(Integer.toString(thirdNum));
                fourth.setText(Integer.toString(fourthNum)); 
                pwdPanel.setVisible(false);
                FloorPanel.this.remove(keylockLabel);
                FloorPanel.this.revalidate();
                repaint();
            }
            //
            if(270 <x && x< 270+dollImg.getWidth(null) && 270<y && y< 270+dollImg.getHeight(null) && dollMode == true){
                dollMode = false;
                isfirst = false;
                pwdPanelMode = true;
                itemList.dollItem.setVisible(true);
                repaint();
            }

        }       

    }
    /******************************************************/
}