package com.example.rucafe;

import java.util.ArrayList;
/**
 * Encapsulates the data fields and methods for store orders
 * @author Siddhi Kasera and Sonal Madhok
 */
public class StoreOrders implements Customizable {


    ArrayList<Order> listOfOrders;

    /**
     * Constructor for store orders
     */
    public StoreOrders(){
        listOfOrders = new ArrayList<Order>();

    }

    /**
     *Getter method to get the list of orders
     * @return a list
     */
    public ArrayList<Order> getListOfOrders(){
        return this.listOfOrders;
    }

    /**
     * Adds order objects to a list
     * @param obj order object
     * @return true if added false other wise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            listOfOrders.add((Order)obj);
            return true;
        }
        return false;
    }


    /**
     * removes order objects from a  list
     * @param obj order object
     * @return true if removed false other wise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            listOfOrders.remove((Order)obj);
            return true;
        }
        return false;
    }

    /**
     * returns a string representation of a store orders object
     * @return  string
     */
    @Override
    public String toString(){
        return this.listOfOrders.toString();
    }
}