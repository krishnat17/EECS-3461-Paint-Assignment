import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class PaintPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	public static Graphics2D g2;
	private int currentX, currentY, oldX, oldY;
	public static BufferedImage image, loadedImage;
	public static Stroke currentStroke;
	public static String currentTool,fontName;
	public static Point startDrag, endDrag;
	public static boolean fill;
	private final static ImageStack<Image> undoStack = new ImageStack<>(10);
	private final static ImageStack<Image> redoStack = new ImageStack<>(10);
	public static int fontSize,bold,italic;
	public static Font font;

	public PaintPanel() {

		currentStroke = new BasicStroke(1);
		currentTool = "Pen";
		fill = false;
		fontSize = 28;
		fontName = "New Roma";
		bold = 0;
		italic = 0;
		
		setPreferredSize(new Dimension(1280,720));


		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				saveImageToStack(undoStack, image);

				if(currentTool.equals("Rect") || currentTool.equals("Circle") || currentTool.equals("Line")) {
					startDrag = new Point(e.getX(),e.getY());
					endDrag = startDrag;
				}

				if (e.getButton() == 1) {
					g2.setPaint(ColorPanel.color_border);
				}
				else{
					g2.setPaint(ColorPanel.color_inside);
				}

				oldX = e.getX();
				oldY = e.getY();

				if( currentTool.equals("Text"))
				{
					try {
						g2.setFont(new Font(fontName, bold + italic , fontSize));
						String s = (String)JOptionPane.showInputDialog("Enter Text:");
						g2.drawString(s,e.getX(),e.getY());
					}
					catch (NullPointerException e1) {}

				}
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {

				if(currentTool.equals("Rect")) {

					if (fill == true){
						g2.fillRect(Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
					}
					else {
						g2.drawRect(Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
					}
				}

				if(currentTool.equals("Circle")) {

					if (fill == true) {
						g2.fillOval(Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
					}
					else {
						g2.drawOval(Math.min(startDrag.x, e.getX()), Math.min(startDrag.y, e.getY()), Math.abs(startDrag.x - e.getX()), Math.abs(startDrag.y - e.getY()));
					}
				}

				if (currentTool.equals("Line")) {
					g2.drawLine(startDrag.x,startDrag.y,endDrag.x,endDrag.y);
				}

				startDrag = null;
				endDrag = null;
				repaint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				;

				g2.setStroke(currentStroke);

				currentX = e.getX();
				currentY = e.getY();

				if(currentTool.equals("Pen") || currentTool.equals("Eraser")){

					if(currentTool.equals("Eraser") ) {
						g2.setPaint(Color.WHITE);	
					}

					if (g2 != null) {

						g2.drawLine(oldX, oldY, currentX, currentY);

						oldX = currentX;
						oldY = currentY;
					}
				}

				if(currentTool.equals("Rect") || currentTool.equals("Circle") || currentTool.equals("Line")) {
					endDrag = new Point(e.getX(),e.getY());
				}

				repaint();

			}
		});

	}

	public void paintComponent(Graphics g) {

		if (image == null) {
			image = (BufferedImage) createImage(1920,1080);
			g2 = (Graphics2D) image.getGraphics();
			whiteOut();
		}

		g.drawImage(image, 0, 0, null);
	}

	public static void clear() {
		image = null;
		whiteOut();
		undoStack.clear();
		redoStack.clear();
		
	}

	public static void whiteOut() {
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 1920, 1080);
		g2.setPaint(ColorPanel.color_border);
		g2.setStroke(currentStroke);
	}

	public static void save(File file) throws IOException{
		ImageIO.write(image,"png",file);
	}

	public static void saveJpg(File file) throws IOException{
		ImageIO.write(image,"jpg",file);
	}

	public static void savePng(File file) throws IOException{
		ImageIO.write(image,"png",file);
	}

	public static void load(File file) throws IOException {
		clear();
		try{
			image = ImageIO.read(file);
			g2 = (Graphics2D) image.getGraphics();
			g2.setPaint(ColorPanel.color_border);
			g2.setStroke(currentStroke);
		}
		catch (IllegalArgumentException e) {
		}
	}

	public static void undo() {
		if(undoStack.size() > 0) {
			Image undoTop = undoStack.pop();
			saveImageToStack(redoStack,PaintPanel.image);

			loadImageFromStack((BufferedImage) undoTop);
		}
	}

	public static void redo() {
		if(redoStack.size() > 0) {
			Image redoTop = redoStack.pop();
			saveImageToStack(undoStack,PaintPanel.image);
			loadImageFromStack((BufferedImage) redoTop);
		}
	}

	public static void loadImageFromStack(BufferedImage i){
		g2 = (Graphics2D) i.getGraphics();
		g2.setPaint(ColorPanel.color_border);
		g2.setStroke(currentStroke);
		image = i;
	}

	public static void saveImageToStack (ImageStack<Image> stack,BufferedImage i){
		BufferedImage stackCopy = new BufferedImage(i.getWidth(),i.getHeight(),i.getType());
		Graphics2D g2 = stackCopy.createGraphics();
		g2.drawImage(i, 0, 0, null);
		stack.push(stackCopy);
	}
	
}



