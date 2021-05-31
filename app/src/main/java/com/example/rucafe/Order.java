package com.example.rucafe;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Encapsulates the data fields and methods for order class.
 * @author Siddhi Kasera and Sonal Madhok
 */
public class Order implements Customizable, Serializable {
    protected static int orderCount=0;
    private int orderNumber;
    private double totalPrice;

    private ArrayList<MenuItem> orderlist;

    /**
     * One parameter constructor for order.
     * @param orderlist of menuitems
     */
    public Order(ArrayList<MenuItem> orderlist){
        this.orderlist = orderlist;
        this.orderNumber = orderCount;
        this.totalPrice = 0;
    }

    /**
     * increments the order number.
     */
    public static void setIncrement(){
        orderCount++;
    }

    /**
     * return the amount of orders
     * @return order number integer
     */
    public static int getOrderCount(){
        return orderCount;
    }

    /**
     * returns the order number for the specific order
     * @return integer of order number
     */
    public int getOrderNumber(){
        return this.orderNumber;
    }

    /**
     * Sets the order number to the specific order
     * @param orderCount sets integer value as order count
     */
    public void setOrderCount(int orderCount){
        this.orderNumber = orderCount;
    }

    /**
     * returns the total price for an order
     * @return prive for an order
     */
    public double getTotalPrice(){
        return totalPrice;
    }

    /**
     * sets the total price of the total price
     * @param totalPrice of the order
     */
    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }


    /**
     * gets the item in the list.
     * @return array list of items
     */
    public ArrayList<MenuItem> getItems(){
        return orderlist;
    }

    /**
     *  adds the menu items to the order list.
     * @param obj to be added in the list
     * @return true if successfully added.
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Coffee || obj instanceof Donut){
            orderlist.add((MenuItem) obj);
            return true;
        }
        return false;
    }

    /**
     * removes the menu items from the list
     * @param obj to be removed
     * @return true if successfully removed.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            orderlist.remove(obj);
            return true;
        }
        return false;
    }

    /**
     * Returns the string representation of an order
     * @return  string
     */
    @Override
    public String toString(){
        return "This order contains"+ "\n" +this.orderlist+ "\n" + this.totalPrice + "\n" + this.orderNumber;
    }



}