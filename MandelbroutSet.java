import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import java.math.*;

public class MandelbroutSet  {
	double left;
	double right;
	double up;
	double down;
	int definition;//the default is 10
	int[][] matrix;
	int MaxIter;
		
	public MandelbroutSet(double left, double right, double up, double down, int definition) {
		super();
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.definition = definition;
		
		this.matrix = new int[definition][definition];
		this.calculate_matrix();
		
	}
	
	public MandelbroutSet(int definition, int mi) {
		super();
		MaxIter = mi;
		this.left = -2.0;
		this.right = 2.0;
		this.up = 2.0;
		this.down = -2.0;
		this.definition = definition;
		
		this.matrix = new int[definition][definition];
		this.calculate_matrix();
		
	}
	/*
	public Color get_color(float num){
		float hue = (num /MaxIter + 0.6f)%1f;
		float saturation = 1.0f;
		float brightness = 0.8f;
		return Color.getHSBColor(hue, saturation, brightness);
	}
	*/
	public int get_RGBcolor(float num){
		float hue = (float)(Math.sin((num)/(MaxIter/9)));//(float)(num/MaxIter);
		float saturation = 1.0f;
		float brightness = 0.8f;
		return Color.getHSBColor(hue, saturation, brightness).getRGB();
	}
	
	public int calculate_color(double a, double b){
		
		//return -1 if it is probably in the set. i.e. can't decide after this MaxIter steps
		double x =0.0;
		double y = 0.0;
		double temp = 0.0;
		
		int iter = 0;
		while (iter < MaxIter && x*x + y*y < 2*2){
			 temp = x*x - y*y + a;
			 y = 2*x*y +b;
			 x = temp;
			 iter++;
			 //System.out.println("Iter"+iter+ " "+ x+" "+y);
		 }
		//System.out.println("the index of"+a+" and "+b + "has an color of"+ iter+"\n");
		//if (iter == MaxIter) return 0;//depth represent the number of iteration. 
		
		return iter;
	}
	
	public double get_location_x(int i){
		return this.left + (right-left)/definition * i;
	}
	
	public double get_location_y(int j){
		return this.down + (this.right - this.left)/definition * j;
	}
	
	public void calculate_matrix(){
		//the step before display
		for (int i = 0; i < this.definition; i++){
			for (int j =0; j < this.definition; j++){
				matrix[i][j] = 
						calculate_color(this.get_location_x(i), this.get_location_y(j));
			}
			//System.out.println(i);
		}
	}
	
	public int[][] get_matrix(){//provide interface for swing
		calculate_matrix();
		return matrix;
	}
	
	public void zoom_in(){
		//calculate the centre part
		double new_left = (left + right)/2 - (right-left)/2*0.8;
		double new_right = (left + right)/2 + (right-left)/2*0.8;
		
		double new_up = (down + up)/2 + (up-down)/2*0.8;
		double new_down = (down + up)/2 - (up-down)/2*0.8;
		this.up = new_up;
		this.down = new_down;
		this.left = new_left;
		this.right = new_right;
		calculate_matrix();
	}
	
	public void zoom_out(){
		//calculate the centre part
		double new_left = (left + right)/2 - (right-left)/2*1.2;
		double new_right = (left + right)/2 + (right-left)/2*1.2;
		
		double new_up = (down + up)/2 + (up-down)/2*1.2;
		double new_down = (down + up)/2 - (up-down)/2*1.2;
		this.up = new_up;
		this.down = new_down;
		this.left = new_left;
		this.right = new_right;
		calculate_matrix();
	}
	
	public void move_left(){
		//calculate the centre part
		double new_left = left - (right-left)*0.2;
		double new_right = right - (right-left)*0.2;

		this.left = new_left;
		this.right = new_right;
		calculate_matrix();
	}
	
	public void move_right(){
		//calculate the centre part
		double new_left = left + (right-left)*0.2;
		double new_right = right + (right-left)*0.2;

		this.left = new_left;
		this.right = new_right;
		calculate_matrix();
		
	}
	
	public void move_up(){
		double new_up = up - (up-down)*0.2;
		double new_down = down - (up-down)*0.2;

		this.up = new_up;
		this.down = new_down;
		calculate_matrix();
	}
	
	public void move_down(){
		double new_up = up + (up-down)*0.2;
		double new_down = down + (up-down)*0.2;

		this.up = new_up;
		this.down = new_down;
		calculate_matrix();
	}
	public String toString(){
		for (int i = 0; i < this.definition; i++){
			for (int j =0; j < this.definition; j++){
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		return null;
		
	}
}
