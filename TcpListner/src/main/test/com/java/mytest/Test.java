package com.java.mytest;
public class Test {

	public static void main(String[] args) {

		Balloon red = new Balloon("Red"); //memory reference 50
		Balloon blue = new Balloon("Blue"); //memory reference 100
		
		swap(red, blue);
		System.out.println("........Swap........"); 
		System.out.println("red color="+red.getColor());
		System.out.println("blue color="+blue.getColor());
		
		swap2(red, blue);
		System.out.println("........Swap2........");
		System.out.println("red color="+red.getColor());
		System.out.println("blue color="+blue.getColor());
		
		
		
		//foo(blue);
		//System.out.println("blue color="+blue.getColor());
		
	}

	private static void foo(Balloon balloon) { //baloon=100
		balloon.setColor("Red"); //baloon=100
		balloon = new Balloon("Green"); //baloon=200
		balloon.setColor("Blue"); //baloon = 200
	}

	//Generic swap method
	public static void swap(Object o1, Object o2){
		Object temp = o1;
		o1=o2;
		o2=temp;
	}
	
	//Generic swap method
		public static void swap2(Balloon red, Balloon blue){
			red.setColor("blue");
			blue.setColor("red");
		}
}