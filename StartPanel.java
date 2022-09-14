import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener{

	/*****StartPanel_variable*******/

	private final ImageIcon titleImage = new ImageIcon("Resource/title.png");
	private final Image background = new ImageIcon("Resource/cover.jpeg").getImage();
	private JLabel gameTitle = new JLabel(titleImage);
	private JButton strButton = new JButton(new ImageIcon("Resource/start.png"));
	private JButton exitButton = new JButton(new ImageIcon("Resource/exit.png"));
	private JButton settingButton = new JButton(new ImageIcon("Resource/setting.png"));
	// private JButton settingButton = new JButton("Setting");

	/*******************************/
	
	public StartPanel(){
		
		setPreferredSize(new Dimension(1100, 600));
		setLayout(null);
		gameTitle.setBounds(350,-50,400,400);
		this.add(gameTitle);// game title //		
		this.setButton();// set buttons(start, exit, setting) //

	}

    private void setButton(){
    	// start button //
		strButton.setBounds(460,270,196,56);
		strButton.setContentAreaFilled(false);
        strButton.setBorderPainted(false);
		strButton.addActionListener(this);

		settingButton.setBounds(460,350,196,56);
		settingButton.setContentAreaFilled(false);
        settingButton.setBorderPainted(false);
        settingButton.addActionListener(this);

		// exit button //
		
		exitButton.setBounds(460,430,196,56);
		exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(this);
		// add buttons in panel //

		// add buttons in panel //
		this.add(strButton);
		this.add(exitButton);
		this.add(settingButton);
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getSource() == strButton){
    		GamePanel.changePanel("beforePanel");
    	}
    	if(e.getSource() == exitButton){
    		System.exit(0);
    	}
    	if(e.getSource() == settingButton){
    		GamePanel.changePanel("settingPanel");
    	}

    }
	@Override
    public void paintComponent(Graphics g) {//draw out the start picture

        super.paintComponent(g);
        g.drawImage(background, 0,0, 1100, 600, null);

    }
}