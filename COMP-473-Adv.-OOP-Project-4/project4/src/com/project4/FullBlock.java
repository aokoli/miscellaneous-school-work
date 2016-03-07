package com.project4;

public class FullBlock extends HouseComponent {

	@Override
	public void build() {
		if (isTerminal() == true) {
			System.out.println("|___|");
		} else {
			System.out.print("|___|");
		}
	}

}
