package app;
import java.lang.management.*;
import java.util.Scanner;

import doubleHash.DoubleHash;

public class MainApp {

	public static void main(String[] args) 
	{
		
		
		int menu = 0;
		
		do 
		{
			//MENU
			System.out.println("============== MAIN MENU ============");
			System.out.println("1. Linear Probing");
			System.out.println("2. Double Hashing");
			Scanner sc = new Scanner (System.in);
			menu = sc.nextInt();
			
			
			switch (menu)
			{
			case 1: //CALL METHOD FOR LINEAR PROBING
				break;
			
			case 2: 
				DoubleHash.DoubleHashApp(); //START DOUBLE HASH
				break;				
			
			}
			
			
		}while (menu != 0);
	}
}
