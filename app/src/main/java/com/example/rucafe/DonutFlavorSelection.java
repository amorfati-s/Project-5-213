package com.example.rucafe;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


/**
 * This class processes the GUI from the activity_donut_flavor_selection.xml in order for the user
 * to choose a flavor and move to the next activity to place their donut order
 *
 * @author Siddhi Kasera, Sonal Madhok
 **/
public class DonutFlavorSelection extends AppCompatActivity {

    private final static String CHOCOLATE = "Chocolate";
    private final static String VANILLA = "Vanilla";
    private final static String STRAWBERRY = "Strawberry";
    private final static String GLAZED = "Glazed";
    private final static String BLUEBERRY = "Blueberry";
    private static String flavor;
    Button chocButton;
    Button vanillaButton;
    Button strawButton;
    Button glazedButton;
    Button blueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_flavor_selection);

        //when a flavor is chosen, the data is transferred onto the next activity
        chocButton = findViewById(R.id.chocolateButton);
        chocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flavor = CHOCOLATE;
                openNewActivity();
            }
        });

        vanillaButton = findViewById(R.id.vanillaButton);
        vanillaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flavor = VANILLA;
                openNewActivity();

            }
        });

        strawButton = findViewById(R.id.strawberryButton);
        strawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flavor = STRAWBERRY;
                openNewActivity();

            }
        });

        glazedButton = findViewById(R.id.glazedButton);
        glazedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flavor = GLAZED;
                openNewActivity();

            }
        });

        blueButton = findViewById(R.id.blueberryButton);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flavor = BLUEBERRY;
                openNewActivity();
            }
        });

    }

    /**
     * Creates an intent and passes the flavor over to the DonutOrder activity
     */
    public void openNewActivity(){
        Intent intent = new Intent(this, DonutOrdering.class);
        intent.putExtra("flavorname", flavor);
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