import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.Paint;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class RoomPanel extends JPanel implements ActionListener{
	
	private Image pillowImg;
    public Integer pillowMove=50;

    private Image sheetImg;
    private Integer sheetX;
    private Integer sheetY;
    private Integer sheetWidth;
    private Integer sheetHeight;
    private Integer sheetMove = 50;
    private boolean sheetMode = true;

    private Image rune2Img;
    public Integer rune2X;
    public Integer rune2Y;
    public Integer rune2Width;
    public Integer rune2Height;
    public boolean rune2Mode = true;

    // 12/13 new 
    private Image pokerImg;
    public Integer pokerX;
    public Integer pokerY;
    public Integer pokerWidth;
    public Integer pokerHeight;
    public boolean pokerMode = true;

    private SelectPokerPanel selectPokerPanel;

	/*********pig_variable************/

    private String roomString = "room.jpeg";
    private Image room = setImg(roomString);
    private JButton right,left,top,bottom;

    private int centerx = 0;
    private int centery = 0;
    private int imagex = 0;
    private int imagey = 0;
    private int currentx = 0;
    private int currenty = 0;

    /*********pocheng_variable***********/

    private int ReleasedX = 0;
    private int ReleasedY = 0;
    public ItemListPanel itemList;
  

    /*****flash*****/
    public static final int RADIUS = 200;
    private Point mousePoint = null;

	/*********pig_constructor************/

    /**************reset*************/
    public void reset(){
        rune2Mode = true;
        pokerMode = true;    
        sheetMode = true; 
        pillowMove=50;
        sheetMove=50;
        selectPokerPanel.reset();
        selectPokerPanel.setVisible(false);
    }
    /********************************/

	public RoomPanel(ItemListPanel itemList){
        
        // 12/9
        this.itemList=itemList;
        setLayout(null);
        setBounds(0,0,1100,600);
        pillowImg = new ImageIcon("Resource/pillow.png").getImage();
        rune2Img = new ImageIcon("Resource/rune2.png").getImage();
        rune2X = getWidth()/2 + getWidth()/17 - 5;
        rune2Y = getHeight()/2 + getHeight()/16 + 55;
        rune2Width = getWidth()/30;
        rune2Height = getHeight()/30;

        // 12/13
        sheetImg = new ImageIcon("Resource/sheet.png").getImage();
        sheetX=getWidth()/2-230;
        sheetY=getHeight()/2-120;
        sheetWidth=300;
        sheetHeight=164;
        
        pokerImg = new ImageIcon("Resource/poker.png").getImage();
        pokerX =  (sheetX+sheetWidth)/2+getWidth()/10;
        pokerY = (sheetY+sheetHeight)/2+getHeight()/5;
        pokerWidth = 100;
        pokerHeight = 100;   
        selectPokerPanel = new SelectPokerPanel(this);
        this.add(selectPokerPanel);
        selectPokerPanel.setVisible(false);
        
        
        this.displayButton();
        addMouseListener(new GetKeyHandler());

        /*****flash*****/
        if(!GamePanel.isTurnOn){
            this.flashLight();   
        }
    }
    /********************/

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

        /******pig*******/
		

		g.drawImage(room,centerx,centery,getWidth(),getHeight(),this);
			
        if(rune2Mode){
            g.drawImage(rune2Img,rune2X,rune2Y,rune2Width,rune2Height,this);
        }
        // 12/13 new 
        if(pokerMode){
            g.drawImage(pokerImg,pokerX,pokerY,pokerWidth,pokerHeight,this);
        }
		g.drawImage(pillowImg,getWidth()/2+75,getHeight()/2-40+pillowMove,150,118,this);
        g.drawImage(sheetImg,sheetX,sheetY+sheetMove,sheetWidth,sheetHeight,this);								
		
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
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == left )
            GamePanel.changePanel("livingRoomListPanel");
        if (e.getSource() == right )
            GamePanel.changePanel("bathRoomListPanel");            
        if (e.getSource() == top )
            GamePanel.changePanel("ceilingListPanel");
        if (e.getSource() == bottom )
            GamePanel.changePanel("floorListPanel");
        
    }
    public void displayButton(){
        right = new JButton(new ImageIcon("Resource/rightarrow.png"));
        right.setBounds(getWidth()-170,getHeight()/2-70,50,50);
        right.addActionListener(this);
        add(right);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);

        left = new JButton(new ImageIcon("Resource/leftarrow.png"));
        left.setBounds(20,getHeight()/2-70,50,50);
        left.addActionListener(this);
        add(left);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);

        top = new JButton(new ImageIcon("Resource/toparrow.png"));
        top.setBounds(getWidth()/2-80,20,50,50);
        top.addActionListener(this);
        add(top);
        top.setContentAreaFilled(false);
        top.setBorderPainted(false);

        bottom = new JButton(new ImageIcon("Resource/bottomarrow.png"));
        bottom.setBounds(getWidth()/2-80,getHeight()-100,50,50);
        bottom.addActionListener(this);
        add(bottom);
        bottom.setContentAreaFilled(false);
        bottom.setBorderPainted(false);

    }

    public Image setImg(String image){
        Image picture = new ImageIcon("Resource/"+image).getImage();
        return picture;
    }

    /*********pocheng_eventhandler*******/

    private class GetKeyHandler extends MouseAdapter{
        boolean pillowMode = true;
        // boolean sheetMode = true;
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            ReleasedX=e.getX();
            ReleasedY=e.getY();

            if((rune2Mode)&&(!pillowMode) && ReleasedX>rune2X 
                && ReleasedX< rune2X+rune2Width && ReleasedY>rune2Y 
                && ReleasedY< rune2Y+rune2Height){
                itemList.rune2Item.setVisible(true);
                rune2Mode = false;
                repaint();
            }                                  
            if(ReleasedX>getWidth()/2+75 && ReleasedX< getWidth()/2+225 
                && ReleasedY>getHeight()/2-40+pillowMove && ReleasedY<getHeight()/2+pillowMove+118){
                if(pillowMode){
                    pillowMove=15;
                    repaint();
                    pillowMode=false;
                }
                else{
                    pillowMove=50;
                    repaint();
                    pillowMode=true;
                }
            }

            // 12/13 new 
            if((pokerMode)&&(!sheetMode) && ReleasedX>pokerX && ReleasedX< pokerX+pokerWidth && ReleasedY>pokerY 
                && ReleasedY< pokerY+pokerHeight){             
                selectPokerPanel.setVisible(true);
            }                                  
            if(ReleasedX>sheetX && ReleasedX<sheetX+sheetWidth && ReleasedY>sheetY && ReleasedY<sheetY+sheetHeight){
                if(sheetMode){
                    sheetMove=-40;
                    repaint();
                    sheetMode=false;
                }
                else{
                    sheetMove=50;
                    repaint();
                    sheetMode=true;
                }
            }
            //
            int leftOpenX = 62;
            int rightOpenX = 179;
            int downOpenY = 106;
            int topOpenY = 401;

            // if user select key and released mouse at door -> win
            if(ReleasedX > leftOpenX && ReleasedX < rightOpenX 
                && ReleasedY>downOpenY && ReleasedY<topOpenY){
                if(itemList.keySelected){
                    JOptionPane.showMessageDialog(null,"You win the Game!!!!","Congratulations!",JOptionPane.INFORMATION_MESSAGE);
                    GamePanel.changePanel("afterPanel");
                }else{
                }
            }
        }        
    }
}