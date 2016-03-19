import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class PaintMenu extends JMenuBar{

	private static final long serialVersionUID = 1L;
	JMenu fileMenu;
	JMenuItem newMI, openMI, saveMI, quitMI;

	JMenu saveAsMenu;
	JMenuItem jpegSave, pngSave;
	
	JMenu editMenu;
	JMenuItem undoMI,redoMI;

	public PaintMenu() {
		
		fileMenu = new JMenu("File");

		newMI = new JMenuItem("New");
		newMI.setToolTipText("Create a new piece of art");
		newMI.setMnemonic(KeyEvent.VK_N);


		openMI = new JMenuItem("Open...");
		openMI.setToolTipText("Open existing piece of art");
		openMI.setMnemonic(KeyEvent.VK_O);


		saveMI = new JMenuItem("Save...");
		saveMI.setToolTipText("Save current piece of art");
		saveMI.setMnemonic(KeyEvent.VK_S);

		quitMI = new JMenuItem("Quit");


		saveAsMenu = new JMenu("Save As"); 

		
		jpegSave = new JMenuItem("Save As JPEG");
		jpegSave.setToolTipText("Save current piece of art as a JPEG");
		jpegSave.setMnemonic(KeyEvent.VK_J);

		
		pngSave = new JMenuItem("Save As PNG");
		pngSave.setToolTipText("Save current piece of art as a PNG");
		pngSave.setMnemonic(KeyEvent.VK_P);

		
		editMenu = new JMenu("Edit");

		
		undoMI = new JMenuItem("Undo");
		undoMI.setToolTipText("Undo last action");
		undoMI.setMnemonic(KeyEvent.VK_U);

		
		redoMI = new JMenuItem("Redo");
		redoMI.setToolTipText("Redo last undo");
		redoMI.setMnemonic(KeyEvent.VK_R);

		
		MenuListener ML = new MenuListener();

		newMI.addActionListener(ML);
		openMI.addActionListener(ML);
		saveMI.addActionListener(ML);
		quitMI.addActionListener(ML);

		jpegSave.addActionListener(ML);
		pngSave.addActionListener(ML);
		
		undoMI.addActionListener(ML);
		redoMI.addActionListener(ML);

		fileMenu.add(newMI);
		fileMenu.add(openMI);
		fileMenu.add(saveMI);
		fileMenu.add(saveAsMenu);
		fileMenu.addSeparator();
		fileMenu.add(quitMI);
		
		saveAsMenu.add(jpegSave);
		saveAsMenu.add(pngSave);
		
		editMenu.add(undoMI);
		editMenu.add(redoMI);

		add(fileMenu);
		add(editMenu);

	}

	private class MenuListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {

			if (e.getSource() == newMI) {
				PaintPanel.clear();
			}

			if (e.getSource() == openMI) {
				try {
					JFileChooser FC = new JFileChooser();
					FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
					FC.showOpenDialog(Main.paint);
					File file = FC.getSelectedFile();
					PaintPanel.load(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

			if (e.getSource() == saveMI) {
				try{
					JFileChooser FC = new JFileChooser();
					FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
					FC.showSaveDialog(Main.paint);
					File file = FC.getSelectedFile();
					file = new File(file+".art");

					BufferedWriter bufferedWriter = null;

					bufferedWriter = new BufferedWriter(new FileWriter(file));
					bufferedWriter.close();


					PaintPanel.save(file); 
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if (e.getSource() == jpegSave) {
				try{
					JFileChooser FC = new JFileChooser();
					FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
					FC.showSaveDialog(Main.paint);
					File file = FC.getSelectedFile();
					file = new File(file+".jpg");

					BufferedWriter bufferedWriter = null;

					bufferedWriter = new BufferedWriter(new FileWriter(file));
					bufferedWriter.close();


					PaintPanel.saveJpg(file); 
				}
				catch (IOException e1) {
					//e1.printStackTrace();
				}

			}
			if (e.getSource() == pngSave) {
				try{
					JFileChooser FC = new JFileChooser();
					FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
					FC.showSaveDialog(Main.paint);
					File file = FC.getSelectedFile();
					file = new File(file+".png");

					BufferedWriter bufferedWriter = null;

					bufferedWriter = new BufferedWriter(new FileWriter(file));
					bufferedWriter.close();


					PaintPanel.savePng(file); 
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			

			if(e.getSource() == quitMI) {
				Main.paint.dispose();
				System.exit(0);
			}
			
			if(e.getSource() == undoMI) {
				PaintPanel.undo();
			}
			
			if(e.getSource() == redoMI) {
				PaintPanel.redo();
			}

			Main.paint.repaint();
		}

	}

}
