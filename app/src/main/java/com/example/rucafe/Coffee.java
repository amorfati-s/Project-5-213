package com.example.rucafe;

import java.util.ArrayList;

/**
 * This class extends menuitem that encapsulates the datafields and method for a coffee order.
 * @author Siddhi Kasera, Sonal Madhok
 */
public class Coffee extends MenuItem implements Customizable {

    private String size;
    private double price;
    private int numAddOn;
    protected int qty;
    private static final double SHORT_PRICE = 1.99;
    private static final double TALL_PRICE = 2.49;
    private static final double GRANDE_PRICE = 2.99;
    private static final double VENTI_PRICE = 3.49;
    private static final double ADD_IN_COST = 0.20;
    private ArrayList<String> addInsList;

    /**
     * A four parameter coffee constructor.
     * @param size       of the coffee
     * @param qty        of the coffee ordered
     * @param addInsList list of add in list
     * @param numAddOn   total num of adds nos.
     */
    public Coffee(String size, int qty, ArrayList<String> addInsList, int numAddOn) {
        super();
        this.size = size;
        this.qty = qty;
        this.addInsList = addInsList;
        this.numAddOn = numAddOn;
    }

    /**
     * Setter method to set the num of addons for a coffee.
     * @param numAddOn num of add ins selected by the customer
     */
    public void setNumAddOn(int numAddOn) {
        this.numAddOn = numAddOn;
    }

    /**
     * getter method that returns the price of a coffee.
     * @return price of coffee
     */
    public double getCoffeePrice() {
        return price;
    }

    /**
     * Calculates the price of coffee
     */
    public void itemPrice() {

        price = numAddOn * ADD_IN_COST;

        if (size.equals("Short")) {
            price = (price + SHORT_PRICE) * qty;// 1.99
        } else if (size.equals("Tall")) {
            price = (price + TALL_PRICE) * qty;
        } else if (size.equals("Grande")) {
            price = (price + GRANDE_PRICE) * qty;
        } else if (size.equals("Venti")) {
            price = (price + VENTI_PRICE) * qty;
        }

    }

    /**
     * Adds addins to a list for a coffee
     * @param obj addIns
     * @return true if the item is added
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof String) {
            addInsList.add((String) obj);
            numAddOn++;
        }
        return true;
    }

    /**
     * removes addins to a list for a coffee
     * @param obj addIns
     * @return true if the item is removed
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof String) {
            addInsList.remove((String) obj);
            numAddOn--;
        }
        return true;
    }

    /**
     * Returns a string representation of this coffee order
     * @return string representation of this coffee order
     */
    @Override
    public String toString() {
        return ("Size " + this.size + "\n " +
                " Quantity " + this.qty + "\n " + " addins " + this.addInsList);
    }

}