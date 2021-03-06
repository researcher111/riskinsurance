package com.riskIt.implObjects;

import java.util.ArrayList;

import com.riskIt.interfaces.CalcImplInterface;
import com.riskIt.interfaces.TypeWageDataStructureInterface;
import com.riskIt.util.Factory;


/**
 * YearlyImpl.java
 * Purpose: Implementation class to allow calculation class
 * 	to introduce late binding and state to calculate values
 * 	based on yearly income for a given value in the database.
 * 
 * @author Bryan Angone
 * @version 1.0 7-12-09
 */

public class YearlyImpl implements CalcImplInterface{

	public void addToArrayList(ArrayList<TypeWageDataStructureInterface> typeWageAL, String type, int weekWage, int workWeeks){
		if (type != null && weekWage > 0 && workWeeks > 0) {
			typeWageAL.add(Factory.getTypeWageDataStructure(type, weekWage, workWeeks));
		}
	}
	
	public double calculateAverage(String type, TypeWageDataStructureInterface i){
		if(type != null && type.equalsIgnoreCase("all")){
			return (i.getWeekWage() * i.getWorkWeeks());
		} else if (i.getType() != null && type != null && i.getType().trim().equalsIgnoreCase(type)){
			return (i.getWeekWage() * i.getWorkWeeks());
		} else {
			return 0;
		}
	}
}
