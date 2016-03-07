package com.project4;

import java.util.ArrayList;
import java.util.List;

public class ComplexStructure extends HouseComponent {
	
	
	List<HouseComponent> components = new ArrayList<HouseComponent>();
	
	@Override
	public void build() {
		for (HouseComponent component : components) {
			component.build();
		}
	}
	
	@Override
	public void addComponent(HouseComponent component) {
		components.add(component);
	}

}
