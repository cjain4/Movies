package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private Spinner genre;
    private SeekBar seekbar;
    private EditText year;
    private EditText IMDB;
    private Button btnAddMovie;
    private int min = 0;
    private int max = 5;
    private int step = 1;
    private int seekvalue = min;
    private TextView ratingg;
    private Double double_year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.editTextName);
        description = findViewById(R.id.editTextDescription);
        genre = findViewById(R.id.spinnerGenre);
        seekbar = (SeekBar) findViewById(R.id.seekBarRating);
        year = findViewById(R.id.editTextYear);
        IMDB = findViewById(R.id.editTextIMDB);
        btnAddMovie = findViewById(R.id.buttonAddMovie);
        ratingg = findViewById(R.id.textViewRatingg);
        description.setSelection(0);
        seekbar.setMax((max - min) / step);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekvalue = min + (progress * step);
                ratingg.setText(seekvalue+"");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString();
                String desc = description.getText().toString();
                String yearr = (year.getText().toString());
                String imdb = IMDB.getText().toString();
                String movie_rating = Integer.toString(seekbar.getProgress());
                String movie_category = genre.getSelectedItem().toString();
                if(name1.matches("")||desc.matches("")||yearr.matches("") ||imdb.matches(""))
                {
                    Toast.makeText(name.getContext(), "Please Enter all the details", Toast.LENGTH_LONG).show();
                }else if (movie_category.matches("Select")){
                    Toast.makeText(name.getContext(), "Please select a genre ", Toast.LENGTH_LONG).show();
                }
                else {
                    double_year = Double.parseDouble(yearr);
                    boolean url = Patterns.WEB_URL.matcher(imdb).matches();
                    if((double_year == 0) || (double_year > 2018)){
                        Toast.makeText(name.getContext(), "Please Enter a valid year ", Toast.LENGTH_LONG).show();
                    }else if(url == false){
                        Toast.makeText(name.getContext(), "Please Enter a valid imdb ", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent2_1 = new Intent(SecondActivity.this, MainActivity.class);

                        intent2_1.putExtra(MainActivity.ADDMOVIE_KEY, new AddMovie(name1, desc, yearr, imdb, movie_rating, movie_category));
                        setResult(RESULT_OK, intent2_1);

                        finish();
                    }
                }

            }
        });





    }
}
