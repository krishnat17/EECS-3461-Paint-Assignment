import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorSizeBar extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public JLabel slideLabel;
	public JSlider lineThickness;
	public static JCheckBox boldBox, italicBox, fillBtn;
	public static JComboBox<Object> fontBox;
	public static JComboBox<Object> fontSizeBox;
	public ColorPanel colorPanel;

	public ColorSizeBar () {

		setBackground(Color.DARK_GRAY);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		ToolBarListener TBL = new ToolBarListener();
		ComboBoxListener CBL = new ComboBoxListener();
		
		slideLabel = new JLabel("Line Thickness");
		slideLabel.setBackground(Color.DARK_GRAY);
		slideLabel.setForeground(Color.WHITE);

		lineThickness = new JSlider(SwingConstants.HORIZONTAL, 1, 20, 1);
		lineThickness.setPaintTicks(true);
		lineThickness.setMajorTickSpacing(1);
		lineThickness.setBackground(Color.DARK_GRAY);
		lineThickness.setForeground(Color.WHITE);

		lineThickness.addChangeListener(TBL);

		colorPanel = new ColorPanel();

		fillBtn = new JCheckBox("Fill");

		fillBtn.setBackground(Color.DARK_GRAY);
		fillBtn.setForeground(Color.WHITE);

		fillBtn.setVisible(false);

		fillBtn.addChangeListener(TBL);

		fontBox = new JComboBox<Object>();
		fontSizeBox = new JComboBox<Object>();
		boldBox = new JCheckBox("BOLD");
		italicBox = new JCheckBox("Italic");
		
		fontBox.addActionListener(CBL);
		fontSizeBox.addActionListener(CBL);
		
		boldBox.addChangeListener(TBL);
		italicBox.addChangeListener(TBL);
		
		boldBox.setBackground(Color.DARK_GRAY);
		boldBox.setForeground(Color.WHITE);
		italicBox.setBackground(Color.DARK_GRAY);
		italicBox.setForeground(Color.WHITE);
		
		setFonts();
		
		fontSizeBox.setSelectedItem(28);
		fontBox.setSelectedItem("Times New Roman");

		fontBox.setVisible(false);
		fontSizeBox.setVisible(false);
		boldBox.setVisible(false);
		italicBox.setVisible(false);

		add(colorPanel);
		add(slideLabel);
		add(lineThickness);
		add(fillBtn);
		add(fontBox);
		add(fontSizeBox);
		add(boldBox);
		add(italicBox);

	}
	private class ToolBarListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {

			if(e.getSource() == lineThickness) {
				PaintPanel.currentStroke = new BasicStroke(lineThickness.getValue());
			}
			
			if(e.getSource() == boldBox) {
				if(boldBox.isSelected()) {
					PaintPanel.bold = 1;
				}
				else
					PaintPanel.bold = 0;
			}
			
			if(e.getSource() == italicBox) {
				if(italicBox.isSelected()) {
					PaintPanel.italic = 2;
				}
				else
					PaintPanel.italic = 0;
			}
			
			if(e.getSource() == fillBtn) {
				if(fillBtn.isSelected()) {
					PaintPanel.fill = true;
				}
				else
					PaintPanel.fill = false;
			}
		}

	}
	
	private class ComboBoxListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == fontBox) {
				PaintPanel.fontName = (String)fontBox.getSelectedItem();
			}
			
			if(e.getSource() == fontSizeBox) {
				PaintPanel.fontSize = (int)fontSizeBox.getSelectedItem();
			}
			
		}
	}

	private void setFonts() {
		String fontstrings[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for ( int i = 0; i < fontstrings.length; i++ )
		{
			fontBox.addItem(fontstrings[i]);
			if ((i>7)&&(i<=12))
				fontSizeBox.addItem(i);
			else if( (i>12)&&(i<=28)&&(i%2==0) )
				fontSizeBox.addItem(i);
			else if( i==36 || i==48 || i==72)
				fontSizeBox.addItem(i);
		}
	}

}
