/*
 
Copyright (C) 2014 Alexander Okoli
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>
 
 */

package com.okolialex.alexstringreverser;

import java.util.Scanner;

public class AlexStringReverser {
	
	public static void main(String[] args){
		run();
	}
	
	
	private static void run() {
		System.out.println("Welcome to AlexStringReverser!");
		String prompt = "Please enter the string (with no gaps) to be reversed. (Press 'Q' to quit): ";
		boolean end = false;
		
		Scanner sc = new Scanner(System.in);
		while (end != true) {
			System.out.println(prompt);
			String str = sc.nextLine();
			
			if (str.equalsIgnoreCase("Q")){
				end = true;
				sc.close();
			} else {
				System.out.println("Entered  string: " + str);
				System.out.println("Reversed string: " + reverseString(str));
			}
		}
		
		System.out.println("Goodbye!");
		

	}
	

	/**
	 * Reverses the given string.
	 *
	 * This function reverses the given string, returning in order from
	 * last character to first.
	 *
	 * @param String stringToBeReversed The string to be reversed.
	 * @return String The reversed string.
	 */
	public static String reverseString(String stringToBeReversed){
		
		char[] tokens = stringToBeReversed.toCharArray();
		
		for (int i = 0; i < tokens.length/2; i++){
			char temp = tokens[tokens.length - 1 - i];
			tokens[tokens.length - 1 - i] = tokens[i]; // Swap: "last" = "first"
			tokens[i] = temp; // Swap: "first" = stored last
		}
		
		return new String(tokens);
	}

}
