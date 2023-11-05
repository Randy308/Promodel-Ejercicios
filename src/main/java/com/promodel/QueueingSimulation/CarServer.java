/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel.QueueingSimulation;

/**
 *
 * @author User
 */
import java.util.ArrayList;

class CarServer {
	String wasteType;
	int busyTill, idleTime;
	ArrayList<Integer> higherLimits, serviceValues;
	
	public void addServiceTableRow(int probability, int value) {
		if(higherLimits.isEmpty())
			higherLimits.add(probability);
		else {
			int lastLimit = higherLimits.get(higherLimits.size() - 1);
			higherLimits.add(lastLimit + probability);
		}
		serviceValues.add(value);
	}
	
	public String getType() {
		return wasteType;
	}
	
	public CarServer(String wasteType) {
		this.wasteType = wasteType;
		higherLimits = new ArrayList<>();
		serviceValues = new ArrayList<>();
	}
}