import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GameFrame extends JFrame{

    private int FWidth;
    private int FHeight;
    private boolean gameStart=false;	

	public GameFrame(int FWidth, int FHeight){

		this.FWidth = FWidth;
		this.FHeight = FHeight;
		this.setTitle("Game Escape Room meow~");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FWidth, FHeight);
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		GamePanel gamePanel = new GamePanel();
		add(gamePanel, BorderLayout.NORTH);


	}

}