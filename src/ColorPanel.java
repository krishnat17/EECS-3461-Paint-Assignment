import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.geom.*;
import java.awt.image.*;

public class ColorPanel extends JPanel implements MouseListener,ActionListener{//color panel

	private static final long serialVersionUID = 1L;
	private	JPanel jPanel_color0[]=new JPanel[5];
	private	JPanel jPanel_color1[]=new JPanel[28];
	private	JPanel jPanel_color2[]=new JPanel[28];

	private BufferedImage bufImg = new BufferedImage(12 ,12,BufferedImage.TYPE_3BYTE_BGR) ,bufImg2 = new BufferedImage(12 ,12,BufferedImage.TYPE_3BYTE_BGR);
	private JLabel jlbImg=new JLabel() ,jlbImg2=new JLabel();
	public static Paint color_border,color_inside;
	private	int i;



	private	int rgb[][]={
			{0,255,128,192,128,255,128,255,  0,  0,  0,  0,  0,  0,128,255,128,255,  0,  0,  0,128,  0,128,128,255,128,255,255,255,255,255},
			{0,255,128,192,  0,  0,128,255,128,255,128,255,  0,  0,  0,  0,128,255, 64,255,128,255, 64,128,  0,  0, 64,128,255,255,255,255},
			{0,255,128,192,  0,  0,  0,  0,  0,  0,128,255,128,255,128,255, 64,128, 64,128,255,255,128,255,255,128,  0, 64,255,255,255,255}
	};
	public ColorPanel(){//the panel

		this.setBackground(Color.DARK_GRAY);
		addMouseListener(this);
		jlbImg.setIcon(new ImageIcon(bufImg));
		jlbImg2.setIcon(new ImageIcon(bufImg2));


		this.setLayout(null);
		color_border=new Color(0,0,0);//black
		color_inside=new Color(255,255,255);//white

		for(i=0;i<jPanel_color0.length;i++){
			jPanel_color0[i]=new JPanel();
			if(i<=2){
				jPanel_color0[i].setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
				jPanel_color0[i].setLayout(null);
			}
			else{
				jPanel_color0[i].setBackground(new Color(rgb[0][i-3],rgb[1][i-3],rgb[2][i-3]));
				jPanel_color0[i].setLayout(new GridLayout(1,1));
				jPanel_color0[i-2].add(jPanel_color0[i]);
			}
		}
		for(i=0;i<jPanel_color2.length;i++){
			jPanel_color2[i]=new JPanel();
			jPanel_color2[i].setLayout(new GridLayout(1,1));
			jPanel_color2[i].setBounds(new Rectangle(2, 2, 12, 12));
			jPanel_color2[i].setBackground(new Color(rgb[0][i],rgb[1][i],rgb[2][i]));

		}

		for(i=0;i<jPanel_color1.length;i++){
			jPanel_color1[i]=new JPanel();
			jPanel_color1[i].setLayout(null);
			jPanel_color1[i].add(jPanel_color2[i]);
			this.add(jPanel_color1[i]);
			if(i%2==0){jPanel_color1[i].setBounds(new Rectangle(32+i/2*16, 0, 16, 16));}
			else{jPanel_color1[i].setBounds(new Rectangle(32+i/2*16, 16, 16, 16));}
			jPanel_color1[i].setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		}

		jPanel_color0[3].add(jlbImg);
		jPanel_color0[4].add(jlbImg2);

		Graphics2D g2d = bufImg2.createGraphics();
		g2d.setPaint( Color.white );
		g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
		g2d.setPaint( Color.red ); 
		g2d.draw( new Line2D.Double( 0, 0, 12, 12 ) );
		g2d.draw( new Line2D.Double( 11, 0, 0, 11 ) );
		repaint();

		this.add(jPanel_color0[1]);
		this.add(jPanel_color0[2]);
		this.add(jPanel_color0[0]);

		jPanel_color0[0].setBounds(new Rectangle(0, 0, 32, 32));
		jPanel_color0[1].setBounds(new Rectangle(4, 4, 16, 16));
		jPanel_color0[2].setBounds(new Rectangle(12,12,16, 16));
		jPanel_color0[3].setBounds(new Rectangle(2, 2, 12, 12));
		jPanel_color0[4].setBounds(new Rectangle(2, 2, 12, 12));

	}
	
	public void actionPerformed( ActionEvent e ){}

	public Dimension getPreferredSize(){
		return new Dimension( 300, 32 );
	}
	public void mouseClicked( MouseEvent e ){}
	public void mousePressed( MouseEvent e ){
		Graphics2D g2d;
		if(e.getX()>=5 && e.getX()<=20 && e.getY()>=5 && e.getY()<=20){
			g2d = bufImg.createGraphics();
			color_border = JColorChooser.showDialog( new Frame(), "Choose border color", (Color)color_border );
			g2d.setPaint(color_border);
			g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
			repaint();
		}
		else if(e.getX()>=13 && e.getX()<=28 && e.getY()>=13 && e.getY()<=28){
			g2d = bufImg2.createGraphics();
			color_inside = JColorChooser.showDialog( new Frame(), "Choose inside color", (Color)color_inside );
			g2d.setPaint(color_inside);
			g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
			repaint();
		}

		if(!(e.getX()>=32 && e.getX()<=256)) return;
		int choose = (e.getX()-32)/16*2+e.getY()/16;

		if(e.getButton()==1)//check whether fill the edge line or the shape inside
			g2d = bufImg.createGraphics();
		else
			g2d = bufImg2.createGraphics();

		g2d.setPaint(new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]));
		g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
		repaint();

		if(e.getButton()==1)
			color_border=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
		else
			color_inside=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
		
		

	}

	public void mouseReleased( MouseEvent e ){}
	public void mouseEntered( MouseEvent e ){}
	public void mouseExited( MouseEvent e ){}
}