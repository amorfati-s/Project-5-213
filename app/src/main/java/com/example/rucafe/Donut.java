package com.example.rucafe;

import java.util.ArrayList;

/**
 * This class extends menuitem that encapsulates the datafields and method for a donut order.
 *
 * @author Siddhi Kasera, Sonal Madhok
 */
public class Donut extends MenuItem implements Customizable {

    private final String flavor;
    private double price;
    protected final double donutPrice = 1.39;
    private int quantity = 0;
    private ArrayList<Integer> quantityList;


    /**
     * Two parameter constructor for donut
     * @param flavor flavor of donut
     * @param quantityList quantity of donut selected
     */
    public Donut(String flavor, ArrayList<Integer> quantityList){
        super();
        this.flavor = flavor;
        this.quantityList = quantityList;
    }

    /**
     * Calculates the quantity inside the quantityList
     * @param quantityList passes in to determine quantity
     */
    public void qtyListCount(ArrayList<Integer> quantityList){
        for (Integer integer : quantityList) {
            quantity = integer;
        }
    }


    /**
     * Calculates the price for donuts.
     */
    public void itemPrice(){
        qtyListCount(quantityList);
        price = donutPrice * quantity;

    }

    /**
     * Getter method for the price of the donut
     * @return price of donut
     */
    public double getDonutPrice(){
        return price;
    }

    /**
     * Adds the quantity of donuts in a list
     * @param obj to be added in the list
     * @return boolean if add finishes
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Integer){
            quantityList.add((Integer) obj);
            quantity = (Integer) obj;
        }
        return true;
    }

    /**
     * removes the quantity of donuts in a list
     * @param obj to be added in the list
     * @return returns true if successfully added.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Integer){
            quantityList.remove((String) obj);
            quantity = (Integer) obj;
        }
        return true;
    }

    /**
     * Returns string form of the object
     * @return string
     */
    @Override
    public String toString(){
        return ( this.flavor + " " + this.quantity);
    }
}