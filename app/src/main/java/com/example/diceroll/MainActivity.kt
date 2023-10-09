package com.example.diceroll

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.graphics.drawable.toDrawable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.rollButton);
        val rollResult: TextView = findViewById(R.id.rollResult);
        val diceImage: ImageView = findViewById(R.id.diceImage);
        val listImage: List<Pair<Int, Drawable?>> = listOf(
            Pair(1, getDrawable(R.drawable.dice_1)),
            Pair(2, getDrawable(R.drawable.dice_2)),
            Pair(3, getDrawable(R.drawable.dice_3)),
            Pair(4, getDrawable(R.drawable.dice_4)),
            Pair(5, getDrawable(R.drawable.dice_5)),
            Pair(6, getDrawable(R.drawable.dice_6)),
        );
        diceImage.setImageDrawable(getDrawable(R.drawable.sei));
        rollButton.setOnClickListener{
            var randomValue: Int = Random.nextInt(1, 7);
            var result: Pair<Int, Drawable?>? = listImage.find { it.first == randomValue };
            diceImage.setImageDrawable(result?.second);
            var scope: CoroutineScope = CoroutineScope(Dispatchers.Main);
            val rolling = scope.launch {
                rollButton.isEnabled = false;
                rollButton.isClickable = false;
                rollButton.setText("Rolling");
                for (i in 1..20) {
                    randomValue = Random.nextInt(1, 7);
                    rollResult.setText(randomValue.toString());
                    result = listImage.find { it.first == randomValue };
                    diceImage.setImageDrawable(result?.second);
                    delay(25);
                }
                rollButton.isEnabled = true;
                rollButton.isClickable = true;
                rollButton.setText("Reroll");
            }
        }

    }
}