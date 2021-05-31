package com.example.rucafe;



import android.content.Intent;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class processes the GUI from the activity_store_orders.xml in order for
 *  the user to view all store orders and remove an order
 *
 * @author Siddhi Kasera, Sonal Madhok
 **/
public class StoreOrdering extends AppCompatActivity {

    private static StoreOrders mainOrder = new StoreOrders();
    private static ArrayList<Order> orderItems = new ArrayList<>();
    private static ArrayList<Integer> orderNumList = new ArrayList<>();
    private double price;
    private int orderNumber;
    Order orderSelected;
    Order orderNumSelected;
    DecimalFormat df = new DecimalFormat("0.00");
    ListView allOrderList;
    TextView totalView;
    Spinner qtySpinner;
    ArrayAdapter<Integer> arrayAdapterQty;
    ArrayAdapter<Order> arrayAdptSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        //find views by ID
        totalView = findViewById(R.id.totalView);
        allOrderList = findViewById(R.id.storeOrdersListView);
        qtySpinner = findViewById(R.id.orderNumSpinner);

        //get the orders from current order page
        mainOrder = CurrentOrder.getAllOrders();

        orderItems.clear();
        orderNumList.clear();

        //populating order number list
        for (int i = 0; i < mainOrder.getListOfOrders().size(); i++) {
            orderNumList.add(mainOrder.getListOfOrders().get(i).getOrderNumber());
        }

        //array adapters for order number list and listview order items
        arrayAdapterQty = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumList);
        arrayAdptSelected = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderItems);
        arrayAdapterQty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtySpinner.setAdapter(arrayAdapterQty);

        qtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
                orderNumber = Integer.parseInt(qtySpinner.getSelectedItem().toString());
                orderNumSelected = findOrder(orderNumber);
                orderItems.clear();
                totalView.setText(df.format(price));
                orderItems.add(orderNumSelected);
                arrayAdptSelected.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //dealing with list view
        allOrderList.setAdapter(arrayAdptSelected);
        allOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //on click in list view
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                orderSelected = (Order) allOrderList.getItemAtPosition(position);
                Context context = getApplicationContext();

                CharSequence text = "Please click longer to delete the item";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

        allOrderList = findViewById(R.id.storeOrdersListView);
        allOrderList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                orderSelected = (Order)  allOrderList.getItemAtPosition(position);
                final int itemPosition = position;
                new AlertDialog.Builder(StoreOrdering.this).setTitle("Are you sure ?")
                        .setMessage("Do you want to delete this order")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                orderItems.remove(itemPosition);
                                int orderNumber = orderSelected.getOrderNumber();
                                removeOrder(orderNumber);
                                removeOrderNumber();
                                totalView.setText("");
                                arrayAdptSelected.notifyDataSetChanged();
                                arrayAdapterQty.notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }

        });

    }

    /**
     * Finds the specific order number and sets the price for that order number
     * @param orderNumber integer value of the order number to be found
     * @return instance of Order
     */
    private Order findOrder(int orderNumber){
        for(int i = 0; i< mainOrder.getListOfOrders().size(); i++)
        {
            if(mainOrder.getListOfOrders().get(i).getOrderNumber() == orderNumber){
                price =  mainOrder.getListOfOrders().get(i).getTotalPrice();
                return mainOrder.getListOfOrders().get(i);
            }
        }
        return null;
    }

    /**
     * Removes an order with a specific order number
     * @param orderNumber contains the order number to be removed
     */
    private void removeOrder(int orderNumber){
        for(int i =0; i< mainOrder.getListOfOrders().size(); i++)
        {
            if(mainOrder.getListOfOrders().get(i).getOrderNumber() == orderNumber){
                mainOrder.remove(mainOrder.getListOfOrders().get(i));
            }
        }
    }


    /**
     * Updates order number list after an order is removed
     */
    private void removeOrderNumber(){
        orderNumList.clear();
        for (int i = 0; i < mainOrder.getListOfOrders().size(); i++) {
            orderNumList.add(mainOrder.getListOfOrders().get(i).getOrderNumber());
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