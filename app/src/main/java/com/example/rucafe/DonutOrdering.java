package com.example.rucafe;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class processes the GUI from the activity_donut_ordering.xml in order for
 * the user to choose a quantity of donut and place their donut order
 *
 * @author Siddhi Kasera, Sonal Madhok
 **/
public class DonutOrdering extends AppCompatActivity {

    private final static ArrayList<Integer> quantityList = new ArrayList<>();
    private final static ArrayList<String> arrayListQty = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    private int quantity;
    protected double currentTotal = 0.00;
    protected static final double DONUT_PRICE = 1.39;
    protected Donut donutOrder;
    TextView flavorPrint;
    TextView subtotalDonutOrder;
    Spinner qtySpinner;
    DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_ordering);

        //getting flavor name from DonutFlavorSelection activity with Intent
        String flavor = getIntent().getStringExtra("flavorname");
        flavorPrint = findViewById(R.id.flavorPrint);
        flavorPrint.setText(flavor);

        //quantity spinner
        qtySpinner = findViewById(R.id.qtySpinnerDonut);
        ArrayAdapter<String> arrayAdapterQty = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListQty);
        arrayAdapterQty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtySpinner.setAdapter(arrayAdapterQty);
        qtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
                quantity = Integer.parseInt(qtySpinner.getSelectedItem().toString());
                calculateQtyTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Allows user to add a donut order on button click
     *
     * @param view provides the view in which the user has clicked
     */
    public void addDonutOrder(View view) {
        quantityList.add(quantity);
        donutOrder = new Donut(flavorPrint.getText().toString(), quantityList);
        donutOrder.itemPrice();

        ArrayList<MenuItem> donutOrdered = new ArrayList<>();
        donutOrdered.add(donutOrder);
        Order order = new Order(donutOrdered);
        CurrentOrder.addMainOrder(order);
        Intent intent = new Intent(this, CurrentOrder.class);
        startActivity(intent);
    }

    /**
     * Calculates the price of the total amount of donuts by the donut price and updates subtotal
     */
    public void calculateQtyTotal() {
        subtotalDonutOrder = findViewById(R.id.subtotalDonutOrder);
        for (int i = 1; i <= arrayListQty.size(); i++) {
            if (quantity == i) {
                currentTotal = DONUT_PRICE * i;
                subtotalDonutOrder.setText(df.format(currentTotal));
            }
        }
    }

    /**
     * Returns user to Main Menu screen on button click
     *
     * @param view provides the view in which the user has clicked
     */
    public void returnToMainMenu(View view) {
        final Context context = this;
        Intent intent = new Intent(context, MainMenu.class);
        startActivity(intent);
    }
}