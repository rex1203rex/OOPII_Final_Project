import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class LivingRoomPanel extends JPanel implements ActionListener{
	
	private Image livingroomImg;

	/*********pig_variable************/

	private String livingroom = "livingRoom.jpg";
    private JButton right;
    private int centerX = 0;
    private int centerY = 0;

    /*********pocheng_variable***********/
    private ItemListPanel itemList;

    private Image SudokuImg = new ImageIcon("Resource/sudoku.png").getImage();
    private Image smallSudokuImg = new ImageIcon("Resource/smallSudoku.png").getImage();
    private Image smallRuneTableImg = new ImageIcon("Resource/smallRuneTable.png").getImage();

    /*********pig_constructor************/
    public boolean SudokuMode = false;
    public boolean smallSudokuMode = true;
    public boolean smallRuneTableMode = false;

    /*****flash*****/
    public static final int RADIUS = 200;
    public static Point mousePoint = null;   

    // 12/9
    private RevolutionDairy revolutionDairy;
    private boolean bookSelect = false;    

    /***********reset***********/
    public void reset(){
        SudokuMode = false;
        smallSudokuMode = true;
        smallRuneTableMode = false;
        bookSelect = false;           
    }
    /***************************/

	public LivingRoomPanel(ItemListPanel itemList){      

        livingroomImg = setImg(livingroom);       
        setLayout(null);
        setBounds(0,0,1100,600);
        this.displayButton();
        this.itemList=itemList;
        this.addMouseListener(new MouseHandler());

        // 12/9
        revolutionDairy = new RevolutionDairy();
        this.add(revolutionDairy);
        revolutionDairy.setVisible(false);

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
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == right){
    	    GamePanel.changePanel("playPanel");
    	}
    }

    public void displayButton(){
        right = new JButton(new ImageIcon("Resource/rightarrow.png"));
        right.setBounds(getWidth()-170,getHeight()/2-70,50,50);
        right.addActionListener(this);
        add(right);
        right.setContentAreaFilled(false);
        right.setBorderPainted(false);
    }

    public Image setImg(String image){
        Image picture = new ImageIcon("Resource/"+image).getImage();
        return picture;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(livingroomImg,centerX,centerY,getWidth(),getHeight(),null);

        if(smallRuneTableMode){
            g.drawImage(smallRuneTableImg,470,263,50,50,null);
        }
        if(smallSudokuMode){
            g.drawImage(smallSudokuImg,400,477,30,30,this);
        }
        if(SudokuMode){
            g.drawImage(SudokuImg,250,50,500,500,this);
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
    public boolean isClickCatEar(int locX,int locY){
        int top=320;
        int bottom=top+20;
        int left=570;
        int right=left+20;
        if(locX>left && locX <right && locY >top && locY<bottom){
            return true;
        }else{
            return false;
        }
    }
    public boolean isClickSmallRuneTable(int locX,int locY){
        int top=263;
        int bottom=top+50;
        int left=470;
        int right=left+50;
        if(locX>left && locX <right && locY >top && locY<bottom){
            return true;
        }else{
            return false;
        }
    }
    public boolean isClickSmallSudoku(int locX,int locY){
        int top=477;
        int bottom=top+30;
        int left=400;
        int right=left+30;
        if(locX>left && locX <right && locY >top && locY<bottom){
            return true;
        }else{
            return false;
        }
    }
    private class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            int x=e.getX();
            int y=e.getY();

            if(isClickCatEar(x,y) && !smallRuneTableMode && !itemList.runeTableSelect){
                smallRuneTableMode = true;
                repaint();
            }
            if(isClickSmallRuneTable(x,y) && smallRuneTableMode){
                itemList.runeTableBtn.setVisible(true);
                itemList.runeTableSelect=true;
                smallRuneTableMode = false;
                repaint();
            }

            // big one should process first. Otherwise, u'll hava bug //
            if(250<x && x<750 && 50<y && y<550 && SudokuMode){ 
                SudokuMode = false;
                smallSudokuMode = true;
                repaint();
            }
            if(isClickSmallSudoku(x,y) && smallSudokuMode){
                smallSudokuMode = false;
                SudokuMode = true;
                repaint();
            }

            // 12/9
            int ClickedX = e.getX();
            int ClickedY = e.getY();
            int[] diary = {51,90,418,440};
            if(bookSelect == true && e.getSource() == LivingRoomPanel.this){
                revolutionDairy.setVisible(false);
                right.setVisible(true);
                bookSelect = false;
                revolutionDairy.page = 0;  
            }
            if(ClickedX >= diary[0] && ClickedX <= diary[1]
            && ClickedY >= diary[2] && ClickedY <= diary[3]){
                revolutionDairy.setVisible(true);
                right.setVisible(false);
                bookSelect = true;
            }            

        }
    }
}
