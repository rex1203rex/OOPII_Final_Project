import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Image;
import java.util.ArrayList;

public class Items{

    private Image pillowImg = new ImageIcon("Resource/pillow1.png").getImage();
    private Image keyImg = new ImageIcon("Resource/key1.png").getImage();
    private Image doorImg = new ImageIcon("Resource/door1.png").getImage();
    private JLabel pillowLabel;
    public Integer centerX = 0;
    public Integer centerY = 0;
    public Integer pillowMove=50;
    public Integer keyMove=50;
    public Integer keyX = 0;
    public Integer keyY = 0;
    public Integer keyWidth=0;
    public Integer keyHeight=0;
    public boolean keyMode=true;


    public Items(RoomPanel roomPanel){

        this.keyX = roomPanel.getWidth()/2+roomPanel.getWidth()/17;
        this.keyY = roomPanel.getHeight()/2+keyMove+roomPanel.getHeight()/16;
        this.keyWidth = roomPanel.getWidth()/30;
        this.keyHeight = roomPanel.getHeight()/30;

        
    }

    public Integer getKeyX(){
        return keyX;
    }
    public Integer getKeyY(){
        return keyY;
    }
    public Integer getKeyWidth(){
        return keyWidth;
    }
    public Integer getKeyHeight(){
        return keyHeight;
    }
    public Image getDoorImg(){
        return doorImg;
    }
    public Image getKeyImg(){
        return keyImg;
    }
    public Image getPillowImg(){
        return pillowImg;
    }
    public static JLabel setItemLoc(ImageIcon targetIcon,Image targetImage,String photoLocation,JLabel targetLabel,String labelName,int width,int height,int locationX,int locationY,JPanel addPanel){
        targetIcon=new ImageIcon(photoLocation);
        targetLabel=new JLabel(labelName);
        targetImage = targetIcon.getImage();
        targetImage =targetImage.getScaledInstance(width,height,Image.SCALE_DEFAULT);
        targetIcon.setImage(targetImage);
        targetLabel.setIcon(targetIcon);
        targetLabel.setBounds(locationX,locationY,width,height);
        addPanel.add(targetLabel);
        return targetLabel;
    }

}