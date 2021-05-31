package com.example.rucafe;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * This class processes the GUI from the activity_main_menu.xml in order for
 *  the user to choose what they would like to do in the app
 *
 * @author Siddhi Kasera, Sonal Madhok
 **/
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //coffee image button
        ImageButton coffeeButton = findViewById(R.id.orderCoffee);
        final Context context = this;
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CoffeeOrdering.class);
                startActivity(intent);
            }

        });

        //donut image button
        ImageButton donutButton = findViewById(R.id.orderDonut);
        donutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DonutFlavorSelection.class);
                startActivity(intent);
            }

        });

        //current order button
        ImageButton yourOrderButton = findViewById(R.id.yourOrder);
        yourOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CurrentOrder.class);
                startActivity(intent);
            }

        });

        //store order button
        ImageButton storeOrderButton = findViewById(R.id.storeOrder);
        storeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoreOrdering.class);
                startActivity(intent);
            }

        });

    }

    }