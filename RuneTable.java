import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class RuneTable extends JPanel{
    private ImageIcon runeTableIcon = new ImageIcon("Resource/runeTable.jpg");
    private Image runeTableImg =new ImageIcon("Resource/runeTable.jpg").getImage();
    private ItemListPanel itemList;

    public RuneTable(ItemListPanel itemList){
        this.setLayout(null);
        this.setBounds(0,0,1000,600);
        this.addMouseListener(new PreviousHandler());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(runeTableImg,0,0,this.getWidth(),564,null);
    }
    private class PreviousHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            GamePanel.changePreviousPanel();
        }
    }
}
