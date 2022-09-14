import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Doll extends JPanel {
    public static boolean isDollRemove=false;
    public static boolean isNailRemove=false;

    private ImageIcon dollIcon = new ImageIcon("Resource/Doll2.png");
    private Image dollImg = dollIcon.getImage().getScaledInstance(1000, 600,  java.awt.Image.SCALE_SMOOTH);
    private ImageIcon newdollIcon = new ImageIcon(dollImg);
    private JLabel doll =new JLabel(newdollIcon);
    private ItemListPanel itemList;

    public Doll(ItemListPanel itemList){
        this.itemList = itemList;
        this.setLayout(null);
        this.setBounds(0,0,1000,600);
        doll.setBounds(0,0,1000,600);
        this.add(doll);
        doll.addMouseListener(new PreviousHandler());
    }
    private class PreviousHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            //System.out.println(e.getX()+","+e.getY());
            int[] left_Eye = {383,438,203,261};
            int [] right_Eye = {541,600,187,251};
            int [] belly = {411,503,412,471};
            int ClickedX = e.getX();
            int ClickedY = e.getY();
            if(itemList.nailSelect){
                if(ClickedX>= left_Eye[0] && ClickedX<= left_Eye[1]
                    && ClickedY >= left_Eye[2] && ClickedY <= left_Eye[3]){
                    itemList.rune3Item.setVisible(true);
                    itemList.rune3Select = true;
                }
                if(ClickedX>= right_Eye[0] && ClickedX<= right_Eye[1]
                    && ClickedY >= right_Eye[2] && ClickedY <= right_Eye[3]){
                    itemList.rune4Item.setVisible(true);
                    itemList.rune4Select = true;
                }
                if(ClickedX>= belly[0] && ClickedX<= belly[1]
                    && ClickedY >= belly[2] && ClickedY <= belly[3]){
                    itemList.bellyBoxItem.setVisible(true);
                    itemList.bellyBoxSelect = true;
                }
                if(itemList.rune3Select && itemList.rune4Select && itemList.bellyBoxSelect){
                    itemList.remove(itemList.dollItem);
                    itemList.remove(itemList.nailItem);
                    Doll.isDollRemove=true;
                    Doll.isNailRemove=true;
                    itemList.revalidate();
                    GamePanel.changePreviousPanel();
                }
            }else{
                GamePanel.changePreviousPanel();
            }
        }
    }
}
