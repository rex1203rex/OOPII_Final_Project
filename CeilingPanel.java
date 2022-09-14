import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
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

public class CeilingPanel extends JPanel implements ActionListener{
	
	private Image ceilingImg;

	/*********pig_variable************/

	private String ceiling = "ceiling.jpg";
    private JButton bottom;
    private int originalX = 0;
    private int originalY = 0;

    /*********pocheng_variable***********/

    private int ReleasedX = 0;
    private int ReleasedY = 0;
    private ItemListPanel itemList;

    /********* Items ************/
    // Rex rune 
    private ImageIcon runeIcon;
    private Image runeImg = new ImageIcon("Resource/runeRollPaper.png").getImage();
    private JLabel rune;

    // REX morseCode 

    private ImageIcon morseCodeIcon;
    private Image morseCodeImg = new ImageIcon("Resource/morseCode.jpg").getImage();
    private JLabel morseCode;

    private boolean runeMode = true;
    private boolean smallMorseMode = true;
    private boolean morseMode = false;

    /*****flash*****/
    public static final int RADIUS = 200;
    public static Point mousePoint = null;   
    
    // music
    public int count = 0;

    /**********reset************/
    public void reset(){
        runeMode = true;
        smallMorseMode = true;
        morseMode = false;
    }
    /***************************/
	public CeilingPanel(ItemListPanel itemList){      
        this.itemList=itemList;
        ceilingImg = setImg(ceiling);       
        setLayout(null);
        setBounds(0,0,1100,600);
        this.displayButton();
        this.addMouseListener(new MouseHandler());

        /*****flash*****/
        if(!GamePanel.isTurnOn){
            this.flashLight();   
        }

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(ceilingImg,originalX,originalY,getWidth(),getHeight(),null);
        if(runeMode){
            g.drawImage(runeImg,300,445,56,28,this);
        }
        if(morseMode){
            g.drawImage(morseCodeImg,295,50,400,400,this);
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

    /*********pig*********/
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == bottom){
    	    GamePanel.changePanel("playPanel");
    	}
    }
    public void displayButton(){
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

    //12/06
    private class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            int x = e.getX(),y = e.getY();
            if(300<x && x<356 && 445<y && y<473){
                runeMode = false;
                itemList.runeBtn.setVisible(true);
                repaint();
            }
            if(295<x && x<695 && 50<y && y<450 && morseMode){
                morseMode = false;
                repaint();
            }else if(457<x && x<540 && 172<y && y<240 && !morseMode){
                morseMode = true;
                if(count == 0){
                    AePlayWaveCeiling t = new AePlayWaveCeiling("Media/meow.wav",CeilingPanel.this);
                    t.start();
                    count = t.count;
                }
                repaint();                
            }


        }
    }
    //

}