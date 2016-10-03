import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MyBoard extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JPanel[][] color;
	JPanel mid;
	DirectDrawDemo p;
	//four buttons
	Button left, right, up, down;
	//two buttons, in out
	Button zoom_in, zoom_out;
	
	MandelbroutSet m;
	int Definition = 500;
	int MaxIter = 300;
	public MyBoard() throws HeadlessException {
		
		super("Robert, are you ready?!");
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		//http://stackoverflow.com/questions/13575224/comparison-method-violates-its-general-contract-timsort-and-gridlayout
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//mid.setOpaque(false);
		setSize(800, 600);
		//this.setResizable(false);
		//addWindowListener(new WindowListener());

		setLayout(new BorderLayout());
		
		left = new Button("Left");
		left.addActionListener(this);
		
		right = new Button("Right");
		right.addActionListener(this);
		
		up = new Button("Up");
		up.addActionListener(this);
		
		down = new Button("Down");
		down.addActionListener(this);
		
		zoom_in = new Button("+");
		zoom_in.addActionListener(this);
		zoom_out = new Button("-");
		zoom_out.addActionListener(this);
		JPanel control = new JPanel(new BorderLayout());
		control.add(left, BorderLayout.WEST);
		control.add(right, BorderLayout.EAST);
		control.add(up, BorderLayout.NORTH);
		control.add(down, BorderLayout.SOUTH);
		
		JPanel central = new JPanel(new GridLayout(2,1));
		
		central.add(zoom_in, BorderLayout.WEST);
		central.add(zoom_out, BorderLayout.WEST);
		
		control.add(central, BorderLayout.CENTER);
		
		add(control, BorderLayout.EAST);
		
		//the main part
		m = new MandelbroutSet(Definition, MaxIter);
		
		//this.mid = this.get_mid_frame();
		add(new JLabel("Hello World"),BorderLayout.NORTH);
		p = new DirectDrawDemo(Definition,Definition,m);
		p.setVisible(true);
		add(this.p, BorderLayout.CENTER);
		pack();
	}
	

	
	public void update_mid_frame(){
		//this.remove(this.mid);
		//System.out.println("what the fuck");
		this.m.calculate_matrix();
		remove(this.mid);
		color = new JPanel[Definition][Definition];
		int[][] matrix = this.m.get_matrix();
		
		this.mid = new JPanel();
		mid.setLayout(new GridLayout(Definition, Definition));
		//this.mid.setOpaque(false);
		//----update mid
		for(int i =0; i < Definition; i++){
			for (int j =0; j< Definition; j++){
				color[i][j] = new JPanel();
				color[i][j].setLayout(new BorderLayout());
				JLabel l = new JLabel(String.valueOf(matrix[i][j]));
				color[i][j].add(l, BorderLayout.CENTER);	
				//int r = (int)(matrix[i][j] * 10);
				//int g = 20;//(int)(matrix[i][j] * 10);
				//int b = 255-(int)(matrix[i][j] *10);
				color[i][j].setBackground(get_color((float)matrix[i][j]));
				//color[i][j].setBackground(new Color(r,g,b));
				this.mid.add(color[i][j]);
			}
			System.out.println(i);
		}
		//pack();
		/*
		this.mid.setSize(Definition, Definition);
		this.mid.setVisible(true);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
		this.mid.revalidate();
		this.mid.repaint();
	*/

		this.p.setSize(Definition, Definition);
		this.p.setVisible(true);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
		this.p.revalidate();
		this.p.repaint();
		this.add(this.p, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public JPanel get_mid_frame(){
		color = new JPanel[Definition][Definition];
		int[][] matrix = m.get_matrix();
		JPanel mid = new JPanel();
		mid.setLayout(new GridLayout(Definition, Definition));
		//----update mid
		for(int i =0; i < Definition; i++){
			for (int j =0; j< Definition; j++){
				color[i][j] = new JPanel();
				color[i][j].setLayout(new BorderLayout());
				JLabel l = new JLabel(String.valueOf(matrix[i][j]));
				color[i][j].add(l, BorderLayout.CENTER);	
				//int r = (int)(matrix[i][j] * 10);
				//int g = 20;//(int)(matrix[i][j] * 10);
				//int b = 255-(int)(matrix[i][j] *10);
				color[i][j].setBackground(get_color((float)matrix[i][j]));
				//color[i][j].setBackground(new Color(r,g,b));
				mid.add(color[i][j]);
			}
			System.out.println(i);
		}
		return mid;
	}

	public Color get_color(float num){
		float hue = (num /23 + 0.6f)%1f;
		float saturation = 1.0f;
		float brightness = 0.8f;
		return Color.getHSBColor(hue, saturation, brightness);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.up) {
			System.out.println("Up button was clicked.\n");
			this.m.move_up();
			
		}
		else if(e.getSource() == this.down) {
			System.out.println("Down button was clicked.\n");
			this.m.move_down();
			
		}
		if(e.getSource() == this.left) {
			System.out.println("Left button was clicked.\n");
			this.m.move_left();
			
		}
		if(e.getSource() == this.right) {
			System.out.println("Right button was clicked.\n");
			this.m.move_right();
			
		}
		if(e.getSource() == this.zoom_in) {
			System.out.println("Zoom in button was clicked.\n");
			this.m.zoom_in();;
		
		}
		if(e.getSource() == this.zoom_out) {
			System.out.println("Zoom out button was clicked.\n");
			this.m.zoom_out();;
			
		}
		update_painting();
		//update_mid_frame();
		//System.out.println(this.m.toString());
	}



	private void update_painting() {
		m.calculate_matrix();
		this.p.paint_canvas(Definition, Definition, m);
		this.p.setSize(Definition, Definition);
		this.p.setVisible(true);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
		this.p.revalidate();
		this.p.repaint();
		this.add(this.p, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
}
