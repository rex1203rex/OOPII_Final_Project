import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPanel extends JPanel implements ActionListener{

	/*****SettingPanel_variable*******/

	private final ImageIcon titleImage = new ImageIcon("Resource/settingTitle.png");
	private final Image background = new ImageIcon("Resource/cover.jpeg").getImage();
	private JLabel gameTitle = new JLabel(titleImage);
	private JButton turnOn = new JButton(new ImageIcon("Resource/baby.png"));
	private JButton turnOff = new JButton(new ImageIcon("Resource/normal.png"));

	/*******************************/
	
	public SettingPanel(){
		
		setPreferredSize(new Dimension(1100, 600));
		setLayout(null);
		gameTitle.setBounds(350,-50,400,400);
		this.add(gameTitle);// game title //		
		this.setButton();// set buttons(start, exit, setting) //

	}

    private void setButton(){
    	// start button //
		turnOn.setBounds(300,350,196,56);
		turnOn.setContentAreaFilled(false);
        turnOn.setBorderPainted(false);
		turnOn.addActionListener(this);


		// exit button //
		
		turnOff.setBounds(600,350,196,56);
		turnOff.setContentAreaFilled(false);
        turnOff.setBorderPainted(false);
        turnOff.addActionListener(this);
		// add buttons in panel //

		this.add(turnOn);
		this.add(turnOff);
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == turnOn){
    		GamePanel.isTurnOn=true;
    		GamePanel.changePanel("startPanel");
    	}
    	if(e.getSource() == turnOff){
    		GamePanel.isTurnOn=false;
    		GamePanel.changePanel("startPanel");
    	}

    }
	@Override
    public void paintComponent(Graphics g) {//draw out the start picture

        super.paintComponent(g);
        g.drawImage(background, 0,0, 1100, 600, null);

    }
}