package com.example.helloworldapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class DecoreEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decore_events);
        GridLayout layout = (GridLayout) findViewById(R.id.decore_event_layout);
        setsingleEvent(layout);
    }

    private void setsingleEvent(GridLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {

            CardView cv = (CardView) layout.getChildAt(i);
            final int finalIntI = i;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalIntI == 0) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.classicaldecore);
                        startActivity(j);

                    } else if (finalIntI == 1) {
                        Intent j = new Intent(DecoreEvents.this,DecoreEventByIntent.class );
                        j.putExtra("resId", R.drawable.youth_sensational_decore);
                        startActivity(j);
                    } else if (finalIntI == 2) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.premiumdecore);
                        startActivity(j);
                    } else if (finalIntI == 3) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.royaldecore);
                        startActivity(j);
                    } else if (finalIntI == 4) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.stunningdecore);
                        startActivity(j);
                    } else if (finalIntI == 5) {
                        Intent j = new Intent(DecoreEvents.this, DecoreEventByIntent.class);
                        j.putExtra("resId", R.drawable.premiumlightedroyaldecore);
                        startActivity(j);
                    }
                }
            });

        }
    }
}