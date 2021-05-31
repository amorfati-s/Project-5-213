package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.Toast;
import android.content.Context;
import java.text.DecimalFormat;

/**
 * This class processes the GUI from the activity_current_order.xml in order to
 * allow the user to add a coffee order
 *
 * @author Siddhi Kasera, Sonal Madhok
 **/
public class CurrentOrder extends AppCompatActivity {

    private static ArrayList<MenuItem> orderItems = new ArrayList<>();
    private static ArrayList<MenuItem> orders = new ArrayList<>();
    private static Order finalOrder = new Order(orders);
    private static StoreOrders allOrders = new StoreOrders();
    private double tax;
    private static double price;
    private static int orderCount;
    private double subTotal;
    private double totalPrice;
    private final static double TAX_RATE = 0.06625;
    TextView subTotalView;
    TextView taxView;
    TextView totalView;
    MenuItem itemSelected;
    ListView currentOrderListView;
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Getter method to get the list of orders user placed
     * @return instance of StoreOrders
     */
    public static StoreOrders getAllOrders(){
        return allOrders;
    }

    /**
     * Adds menu items to a partial order from either DonutOrdering or CoffeeOrdering
     * @param menuItems instance of Coffee or Donut
     */
    public static void addMainOrder(Order menuItems){
        for(MenuItem item: menuItems.getItems()){
            orderItems.add(item);
            finalOrder.add(item);
            if(item instanceof Coffee){
                price = price +  ((Coffee) item).getCoffeePrice();
            }
            if(item instanceof Donut){
                price = price + ((Donut) item).getDonutPrice();
            }
            finalOrder.setTotalPrice(price);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        settingText(finalOrder);

        //list view for current order
        currentOrderListView = findViewById(R.id.currentOrderListView);
        final ArrayAdapter<MenuItem> arrayAdptSelected = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderItems);
        currentOrderListView.setAdapter(arrayAdptSelected);

        currentOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //on click in list view
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Context context = getApplicationContext();

                CharSequence text = "Please click longer to delete the item";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        //long click listener for removal of menu items
        currentOrderListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = (MenuItem) currentOrderListView.getItemAtPosition(position);
                final int itemPosition = position;
                new AlertDialog.Builder(CurrentOrder.this).setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                orderItems.remove(itemPosition);
                                removeItemOrder(itemSelected);
                                settingText(finalOrder);
                                arrayAdptSelected.notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }

        });
    }

    /**
     * Allows the user to remove a menu item from their order before placing
     * @param menuItem brings in an instance of Coffee or Donut
     */
    public void removeItemOrder(MenuItem menuItem){

        for (int i = 0; i < finalOrder.getItems().size(); i++) {
            finalOrder.remove(menuItem);
            if(menuItem instanceof Coffee){
                price = price -  ((Coffee) menuItem).getCoffeePrice();
            }
            if(menuItem instanceof Donut){
                price = price - ((Donut) menuItem).getDonutPrice();
            }
            finalOrder.setTotalPrice(price);
        }
    }

    /**
     * Sets the text in the subtotal, tax, and total view dynamically for the current order
     * @param order references the order to print the prices for
     */
    public void settingText(Order order){
        subTotalView = findViewById(R.id.subTotalView);
        taxView = findViewById(R.id.taxView);
        totalView = findViewById(R.id.totalView);

        subTotal = order.getTotalPrice();
        tax = subTotal * TAX_RATE;
        totalPrice =  subTotal + tax;
        subTotalView.setText(df.format(subTotal));
        taxView.setText(df.format(tax));
        totalView.setText(df.format(totalPrice));
    }

    /**
     * Calculates the total price with tax and sets the order price
     * @param finalOrder instance of Order
     */
    private void calculatePrice(Order finalOrder){
        subTotal = finalOrder.getTotalPrice();
        tax = subTotal * TAX_RATE;
        totalPrice =  subTotal + tax;
        finalOrder.setTotalPrice(Double.parseDouble(df.format(totalPrice)));

    }

    /**
     * Allows user to place their entire order on button click and navigates to store orders page
     * @param view provides the view in which the user has clicked
     */
    public void placeOrderButton(View view) {
        if(finalOrder.getTotalPrice() != 0){
            subTotalView = findViewById(R.id.subTotalView);
            taxView = findViewById(R.id.taxView);
            totalView = findViewById(R.id.totalView);
            finalOrder.setIncrement();
            orderCount = finalOrder.getOrderCount();
            finalOrder.setOrderCount(orderCount);
            calculatePrice(finalOrder);
            currentOrderListView.setAdapter(null);

            allOrders.add(finalOrder);

            this.finalOrder = new Order(new ArrayList<MenuItem>());

            orderItems.clear();
            subTotalView.setText("");
            taxView.setText("");
            totalView.setText("");
            price = 0;
            Intent intent = new Intent(this, StoreOrdering.class);
            startActivity(intent);
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Please add an Order";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

    }

    /**
     * Returns user to Main Menu screen on button click
     * @param view provides the view in which the user has clicked
     */
    public void returnToMainMenu(View view) {
        final Context context = this;
        Intent intent = new Intent(context, MainMenu.class);
        startActivity(intent);
    }
}