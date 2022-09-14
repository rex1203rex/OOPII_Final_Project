import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
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
import java.util.ArrayList;

public class BathRoomPanel extends JPanel {
	private JButton left;
    private ItemListPanel itemList;  
    private final Image background = new ImageIcon("Resource/bathRoom.jpg").getImage();
    private final Image crackImg =new ImageIcon("Resource/crack.png").getImage();
    private final Image bigCrackImg =new ImageIcon("Resource/bigCrack.png").getImage();
    private final Image balloonImg = new ImageIcon("Resource/balloon.png").getImage();
    private final Image rollPaperImg=new ImageIcon("Resource/rollPaper.png").getImage();
    private final Image nailImg=new ImageIcon("Resource/nail.png").getImage();
    private ArrayList<Integer[]> crackLoc = new ArrayList<Integer[]>();
    private ArrayList<Integer[]> balloonLoc = new ArrayList<Integer[]>();
    private int clickMirrorCount=0;
    private int maxClickMirrorCount=10;
    private int clickWrongBalloonCount=0;
    private boolean isFirstSetBalloonLoc = true;
    private boolean isClickedBalloon3=false;
    private boolean isClickedBalloon6=false;
    /*****flash*****/
    public static final int RADIUS = 200;
    public static Point mousePoint = null;   

    /*****reset****/
    public void reset(){
        crackLoc = new ArrayList<Integer[]>();
        balloonLoc = new ArrayList<Integer[]>();
        clickMirrorCount=0;
        maxClickMirrorCount=10;
        clickWrongBalloonCount=0;
        isFirstSetBalloonLoc = true;
        isClickedBalloon3=false;
        isClickedBalloon6=false;       
    }
    /***************/

	public BathRoomPanel(ItemListPanel itemList){       

        setLayout(null);
        setBounds(0,0,1100,600);
        this.itemList=itemList;
        this.displayButton();
        addMouseListener(new MouseHandler());

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
    public void displayButton(){
        left = new JButton(new ImageIcon("Resource/leftarrow.png"));
        left.setBounds(20,getHeight()/2-70,50,50);
        left.addActionListener(new ActionHandler());
        add(left);
        left.setContentAreaFilled(false);
        left.setBorderPainted(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background , 0, 0, getWidth(), getHeight(), null);

        if(clickMirrorCount<maxClickMirrorCount){
            for(Integer[] loc:crackLoc){
                g.drawImage(crackImg ,loc[0]-25, loc[1]-25, 50,50, null);
            }           
        }else{
            g.drawImage(bigCrackImg ,340,100,120,120, null);
            if (isFirstSetBalloonLoc){
                this.setBalloonLoc(this.balloonLoc);
                isFirstSetBalloonLoc=false;
            }
            this.paintBalloon(g);
            if(isClickedBalloon3){
                g.drawImage(nailImg ,830,330, 50,50, null);
            }
            if(isClickedBalloon6){
                g.drawImage(rollPaperImg ,650, 400,50,50, null);
            }
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
    public void paintBalloon(Graphics g){
        if(itemList.nailAppear && itemList.numberLockCardAppear){
            ;//donothing
        }else{
            for(int i=0;i<9;i++){ 
                if(!(this.balloonLoc.get(i)[2]==0)?false:true){
                    g.drawImage(balloonImg ,this.balloonLoc.get(i)[0],this.balloonLoc.get(i)[1], 50,50, null); 
                    Image balloonImg = new ImageIcon("Resource/"+Integer.toString(i+1)+".png").getImage();    
                    g.drawImage(balloonImg ,this.balloonLoc.get(i)[0]+20,this.balloonLoc.get(i)[1]+10, 15,15, null);                
                }
    
            }
        }          
    }
    public void setBalloonLoc(ArrayList<Integer[]> balloonLoc){
        int[] locX            ={500,630,830,850,780,650,600,500,360};
        int[] locY            ={350,320,333,520,440,400,480,500,490};
        int[] isClickedBalloon={  0,  0,  0,  0,  0,  0,  0,  0,  0};
        for(int i=0;i<9;i++){
            Integer[] loc=new Integer[3];
            loc[0]=locX[i];
            loc[1]=locY[i];
            loc[2]=isClickedBalloon[i];
            balloonLoc.add(loc);
        }
    }
    public boolean isInsideMirror(MouseEvent e){
        int left=356;
        int right=450;
        int top=110;
        int bottom=200;
        int locX=e.getX();
        int locY=e.getY();
        if(locX>left && locX <right && locY >top && locY<bottom){
            return true;
        }else{
            return false;
        }
    }
    public boolean isClickBalloon(int clickX,int clickY,int locX,int locY){
        int left=locX;
        int right=locX+50;
        int top=locY;
        int bottom=locY+50;
        if(clickX<=right && clickX>=left && clickY <=bottom && clickY >=top){
            return true;
        }else{
            return false;
        }
    }
    public boolean isClickBalloon3(int clickX,int clickY){
        if(balloonLoc.size()!=0){
            int locX=balloonLoc.get(2)[0];
            int locY=balloonLoc.get(2)[1];
            int left=locX;
            int right=locX+50;
            int top=locY;
            int bottom=locY+50;
            if(clickX<=right && clickX>=left && clickY <=bottom && clickY >=top){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean isClickBalloon6(int clickX,int clickY){
        if(balloonLoc.size()!=0){
            int locX=balloonLoc.get(5)[0];
            int locY=balloonLoc.get(5)[1];
            int left=locX;
            int right=locX+50;
            int top=locY;
            int bottom=locY+50;
            if(clickX<=right && clickX>=left && clickY <=bottom && clickY >=top){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }    
    private class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            /***********kuo***********/
            if(isInsideMirror(e) && clickMirrorCount<maxClickMirrorCount){
                Integer[] loc =new Integer[2];
                loc[0]=e.getX();
                loc[1]=e.getY();
                crackLoc.add(loc);
                clickMirrorCount++;    
                repaint();                 
            }else if(isClickBalloon3(e.getX(),e.getY()) && isClickedBalloon3){
                itemList.nailItem.setVisible(true);
                itemList.nailAppear=true;
                isClickedBalloon3=false;
                repaint();                        
            }else if(isClickBalloon6(e.getX(),e.getY()) && isClickedBalloon6){
                itemList.numberLockCardItem.setVisible(true);
                itemList.numberLockCardAppear=true;
                isClickedBalloon6=false;
                repaint();
            }else if(!itemList.nailAppear || !itemList.numberLockCardAppear){           
                for(Integer[] loc:BathRoomPanel.this.balloonLoc){
                    if(isClickBalloon(e.getX(),e.getY(),loc[0],loc[1]) && loc[2]==0){
                        if(isClickBalloon3(e.getX(),e.getY())){
                            isClickedBalloon3=true;
                            repaint();
                        }else if(isClickBalloon6(e.getX(),e.getY())){
                            isClickedBalloon6=true;
                            repaint();
                        }else if(BathRoomPanel.this.clickWrongBalloonCount<2){
                            BathRoomPanel.this.clickWrongBalloonCount+=1;
                            JOptionPane.showMessageDialog(BathRoomPanel.this,
                                "You only have "+Integer.toString(3-BathRoomPanel.this.clickWrongBalloonCount)+" chance!",
                                "WARNING",
                                JOptionPane.WARNING_MESSAGE);
                            repaint();
                        }else{
                            JOptionPane.showMessageDialog(BathRoomPanel.this,
                                "You have died!",
                                "WARNING",
                                JOptionPane.WARNING_MESSAGE);    
                            GamePanel.changePanel("startPanel");
                        }
                        loc[2]=1;  
                    }

                }
            }
        }
    }

    private class ActionHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == left )
                GamePanel.changePanel("playPanel");
            repaint();
        }
    }
}