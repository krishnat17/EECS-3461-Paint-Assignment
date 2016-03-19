import java.awt.*;

import javax.swing.*;

public class PaintFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public PaintMenu paintMenu;
	public PaintPanel paintPanel;
	public ColorSizeBar colorSizeBar;
	public PaintTools paintTools;
	public static Cursor cursor;
	
	public PaintFrame() {
		
		cursor = Cursor.getDefaultCursor();
		setCursor(cursor);
		
		this.
		setSize(1280, 720);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		
		paintMenu = new PaintMenu();
		paintPanel = new PaintPanel();
		paintTools = new PaintTools();
		colorSizeBar = new ColorSizeBar();
		
		add(paintMenu, BorderLayout.NORTH);
		add(new JScrollPane(paintPanel),BorderLayout.CENTER);
		add(paintTools,BorderLayout.EAST);
		add(colorSizeBar, BorderLayout.SOUTH);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}