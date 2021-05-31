package com.example.rucafe;

import android.content.Context;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.text.DecimalFormat;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class processes the GUI from the activity_coffee_ordering.xml in order to
 * allow the user to add a coffee order
 *
 * @author Siddhi Kasera, Sonal Madhok
 **/
public class CoffeeOrdering extends AppCompatActivity {
    private static String selectedFromList;
    private static int quantity;
    private int numAddOns;
    private int dynamicNumAddOns;
    protected double currentTotal = 0.00;
    private static final double SHORT_PRICE = 1.99;
    private static final double TALL_PRICE = 2.49;
    private static final double GRANDE_PRICE = 2.99;
    private static final double VENTI_PRICE = 3.49;
    private static final double ADD_IN_COST = 0.20;
    private static ArrayList<String> arrayListQty = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    protected ArrayList<String> addInsList = new ArrayList<String>();
    protected Coffee coffeeOrder;
    TextView subtotalView;
    CheckBox cream;
    CheckBox caramel;
    CheckBox milk;
    CheckBox syrup;
    CheckBox whippedCream;
    Spinner qtySpinner;
    DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_ordering);

        //sizeSpinner functionality in gui
        final Spinner spinner = findViewById(R.id.sizeSpinner);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("Short", "Tall", "Grande", "Venti"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
                selectedFromList = spinner.getSelectedItem().toString();
                subtotalView = findViewById(R.id.subtotalView);
                calculateSizeSubTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                subtotalView.setText("");

            }
        });

        //functionality for quantity spinner in gui
        qtySpinner = findViewById(R.id.qtySpinnerDonut);
        ArrayAdapter<String> arrayAdapterQty = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListQty);
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
                subtotalView.setText("");

            }
        });

        //dealing with subtotal view and checkboxes listeners
        subtotalView = findViewById(R.id.subtotalView);
        cream =findViewById(R.id.cream);
        caramel = findViewById(R.id.caramel);
        milk = findViewById(R.id.milk);
        syrup = findViewById(R.id.syrup);
        whippedCream = findViewById(R.id.whippedCream);

        cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    currentTotal += ADD_IN_COST;
                    dynamicNumAddOns++;
                } else {
                    currentTotal -= ADD_IN_COST;
                    dynamicNumAddOns--;
                }
                subtotalView.setText(df.format(currentTotal));
            }
        });

        caramel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    currentTotal += ADD_IN_COST;
                    dynamicNumAddOns++;
                } else {
                    currentTotal -= ADD_IN_COST;
                    dynamicNumAddOns--;
                }
                subtotalView.setText(df.format(currentTotal));
            }
        });

        milk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    currentTotal += ADD_IN_COST;
                    dynamicNumAddOns++;
                } else {
                    currentTotal -= ADD_IN_COST;
                    dynamicNumAddOns--;
                }
                subtotalView.setText(df.format(currentTotal));
            }
        });

        syrup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    currentTotal += ADD_IN_COST;
                    dynamicNumAddOns++;
                } else {
                    currentTotal -= ADD_IN_COST;
                    dynamicNumAddOns--;
                }
                subtotalView.setText(df.format(currentTotal));
            }
        });

        whippedCream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()) {
                    currentTotal += ADD_IN_COST;
                    dynamicNumAddOns++;
                } else {
                    currentTotal -= ADD_IN_COST;
                    dynamicNumAddOns--;
                }
                subtotalView.setText(df.format(currentTotal));
            }
        });

    }

    /**
     * Calculates a dynamic subtotal and displays in subtotalView with data from the size spinner
     */
    public void calculateSizeSubTotal() {
        subtotalView = findViewById(R.id.subtotalView);

        if (selectedFromList.equals("Short")) {
            currentTotal = quantity * SHORT_PRICE + (dynamicNumAddOns * ADD_IN_COST);
            subtotalView.setText(df.format(currentTotal));
        }
        if (selectedFromList.equals("Tall")) {
            currentTotal = quantity * TALL_PRICE + (dynamicNumAddOns * ADD_IN_COST);
            subtotalView.setText(df.format(currentTotal));
        }
        if (selectedFromList.equals("Venti")) {
            currentTotal = quantity * VENTI_PRICE + (dynamicNumAddOns * ADD_IN_COST);
            subtotalView.setText(df.format(currentTotal));
        }
        if (selectedFromList.equals("Grande")) {
            currentTotal = quantity * GRANDE_PRICE + (dynamicNumAddOns * ADD_IN_COST);
            subtotalView.setText(df.format(currentTotal));
        }
    }

    /**
     * Calculates a dynamic subtotal and displays in subtotalView with data from the quantity spinner
     */
    public void calculateQtyTotal() {
        subtotalView = findViewById(R.id.subtotalView);

        for (int i = 1; i < arrayListQty.size() - 1; i++) {
            if (quantity == i) {
                if (selectedFromList.equals("Short")) {
                    currentTotal = SHORT_PRICE * i;
                    subtotalView.setText(df.format(currentTotal));
                }
                if (selectedFromList.equals("Tall")) {
                    currentTotal = TALL_PRICE * i;
                    subtotalView.setText(df.format(currentTotal));
                }
                if (selectedFromList.equals("Grande")) {
                    currentTotal = GRANDE_PRICE * i;
                    subtotalView.setText(df.format(currentTotal));
                }
                if (selectedFromList.equals("Venti")) {
                    currentTotal = VENTI_PRICE * i;
                    subtotalView.setText(df.format(currentTotal));
                }
                subtotalView.setText(df.format(currentTotal));
            }
        }
    }

    /**
     * Checks if add-in checkbox is checked then adds it to the coffee order object
     */
    private void addAddOns() {
        cream = findViewById(R.id.cream);
        caramel = findViewById(R.id.caramel);
        milk = findViewById(R.id.milk);
        syrup = findViewById(R.id.syrup);
        whippedCream = findViewById(R.id.whippedCream);

        if (caramel.isChecked()) {
            numAddOns++;
            coffeeOrder.add("Caramel");
        }
        if (milk.isChecked()) {
            numAddOns++;
            coffeeOrder.add("Milk");
        }
        if (cream.isChecked()) {
            numAddOns++;
            coffeeOrder.add("Cream");
        }
        if (whippedCream.isChecked()) {
            numAddOns++;
            coffeeOrder.add("Whipped Cream");
        }
        if (syrup.isChecked()) {
            numAddOns++;
            coffeeOrder.add("Syrup");
        }
    }

    /**
     * Allows user to add a coffee order on button click
     * @param view provides the view in which the user has clicked
     */
    public void addCoffeeOrder(View view) {
        coffeeOrder = new Coffee(selectedFromList, quantity, addInsList, numAddOns); //create Coffee object
        addAddOns();
        coffeeOrder.setNumAddOn(numAddOns);
        coffeeOrder.itemPrice();
        ArrayList<MenuItem> coffeeOrdered = new ArrayList<>();
        coffeeOrdered.add(coffeeOrder);
        Order order = new Order(coffeeOrdered);
        CurrentOrder.addMainOrder(order);
        Intent intent = new Intent(this, CurrentOrder.class);
        startActivity(intent);
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