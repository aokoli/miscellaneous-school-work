package com.project4;

public class PartialBlock extends HouseComponent {
	
	@Override
	public void build() {
		if (isTerminal() == true) {
			System.out.println("___|");
		} else {
			System.out.print("___|");
		}
	}

}
