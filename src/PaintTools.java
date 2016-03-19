import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PaintTools extends JPanel{

	private static final long serialVersionUID = 1L;

	private JButton penBtn,eraserBtn,rectBtn,circleBtn,lineBtn,textBtn;
	private ImageIcon pen, eraser, rect, circle, line, text;

	public PaintTools() {

		setBackground(Color.DARK_GRAY);
		setLayout(new GridLayout(0,2,1,1));

		pen = new ImageIcon(getClass().getResource("assets/pen.png"));
		eraser = new ImageIcon(getClass().getResource("assets/eraser.png"));
		rect = new ImageIcon(getClass().getResource("assets/rect.png"));
		circle = new ImageIcon(getClass().getResource("assets/oval.png"));
		line = new ImageIcon(getClass().getResource("assets/line.png"));
		text = new ImageIcon(getClass().getResource("assets/text.png"));

		penBtn = new JButton();		
		penBtn.setIcon(pen);
		eraserBtn = new JButton();
		eraserBtn.setIcon(eraser);
		rectBtn = new JButton();
		rectBtn.setIcon(rect);
		circleBtn = new JButton();
		circleBtn.setIcon(circle);
		lineBtn = new JButton();
		lineBtn.setIcon(line);
		textBtn = new JButton();
		textBtn.setIcon(text);

		ButtonListener BL = new ButtonListener();

		penBtn.addActionListener(BL);
		eraserBtn.addActionListener(BL);
		rectBtn.addActionListener(BL);
		circleBtn.addActionListener(BL);
		lineBtn.addActionListener(BL);
		textBtn.addActionListener(BL);		

		add(penBtn);
		add(eraserBtn);
		add(rectBtn);
		add(circleBtn);
		add(lineBtn);	
		add(textBtn);

	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == penBtn) {
				fillFalse();
				fontFalse();
				PaintPanel.currentTool = "Pen";


			}

			if (e.getSource() == eraserBtn) {
				fillFalse();
				fontFalse();

				PaintPanel.currentTool = "Eraser"; 
			}

			if (e.getSource() == rectBtn) {
				fillTrue();
				fontFalse();

				PaintPanel.currentTool = "Rect";

			}

			if (e.getSource() == circleBtn) {
				fillTrue();
				fontFalse();

				PaintPanel.currentTool = "Circle";

			}

			if (e.getSource() == lineBtn) {
				fillFalse();
				fontFalse();

				PaintPanel.currentTool = "Line";
			}
			if (e.getSource() == textBtn) {
				fillFalse();
				fontTrue();

				PaintPanel.currentTool = "Text";

			}
		}
	}

	private void fillTrue() {
		ColorSizeBar.fillBtn.setVisible(true);
	}

	private void fillFalse() {
		ColorSizeBar.fillBtn.setVisible(false);
	}

	private void fontTrue() {
		ColorSizeBar.fontBox.setVisible(true);
		ColorSizeBar.fontSizeBox.setVisible(true);
		ColorSizeBar.boldBox.setVisible(true);
		ColorSizeBar.italicBox.setVisible(true);
	}

	private void fontFalse() {
		ColorSizeBar.fontBox.setVisible(false);
		ColorSizeBar.fontSizeBox.setVisible(false);
		ColorSizeBar.boldBox.setVisible(false);
		ColorSizeBar.italicBox.setVisible(false);
	}
	
}
