package com.project4;

public class Client {

	public static void main(String[] args) {
		
		// ************* COMPLEX COMPONENTS/STRUCTURES *****************
		
		HouseComponent house = new ComplexStructure();
		HouseComponent midPortion = new ComplexStructure();
		HouseComponent lowerPortion = new ComplexStructure();
		
		// ************* LEAVES ****************************************
		
		// Roof
		HouseComponent roof = new Roof();
		
		// Blocks
		HouseComponent fullBlock = new FullBlock();
		HouseComponent fullBlockTerminal = new FullBlock();
		fullBlockTerminal.setTerminal(true);
		
		HouseComponent partialBlock = new PartialBlock();
		HouseComponent partialBlockTerminal = new PartialBlock();
		partialBlockTerminal.setTerminal(true);
		
		// Door
		HouseComponent doorUpper = new Blank();
		HouseComponent doorHandle = new DoorHandle();
		HouseComponent doorBottom = new Platform();

		// ************* START CONSTRUCTING HOUSE **********************
		
		// Construct mid portion of house
		midPortion.addComponent(fullBlock);
		midPortion.addComponent(partialBlock);
		midPortion.addComponent(partialBlockTerminal);
		
		midPortion.addComponent(fullBlock);
		midPortion.addComponent(partialBlock);
		midPortion.addComponent(partialBlockTerminal);
		
		midPortion.addComponent(fullBlock);
		midPortion.addComponent(partialBlock);
		midPortion.addComponent(partialBlockTerminal);
		
		
		// Construct lower portion of the house (includes door)
		lowerPortion.addComponent(fullBlock);
		lowerPortion.addComponent(doorUpper);
		lowerPortion.addComponent(fullBlockTerminal);
		
		lowerPortion.addComponent(fullBlock);
		lowerPortion.addComponent(doorHandle);
		lowerPortion.addComponent(fullBlockTerminal);
		
		lowerPortion.addComponent(fullBlock);
		lowerPortion.addComponent(doorBottom);
		lowerPortion.addComponent(fullBlockTerminal);

		
		// Construct complete house
		house.addComponent(roof);
		house.addComponent(midPortion);
		house.addComponent(lowerPortion);

		house.build();
		
	}
	
	

}
