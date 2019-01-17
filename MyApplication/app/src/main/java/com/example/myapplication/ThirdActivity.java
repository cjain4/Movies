package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private Spinner genre;
    private EditText year;
    private EditText IMDB;
    private TextView rating;
    private SeekBar seekBar;
    private int min = 0;
    private int max = 5;
    private int step = 1;
    private Button save_button;
    private int seekvalue;
    private Double double_year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        name = findViewById(R.id.editTextName);
        description = findViewById(R.id.editTextDescription);
        year = findViewById(R.id.editTextYear);
        IMDB = findViewById(R.id.editTextIMDB);
        seekBar = (SeekBar) findViewById(R.id.seekBarRating);
        rating = findViewById(R.id.textViewRatingg);
        genre = findViewById(R.id.spinnerGenre);
        save_button = findViewById(R.id.buttonSaveChanges);
        seekBar.setMax((max - min) / step);
        Intent intent_edit = getIntent();
        AddMovie getarray = intent_edit.getExtras().getParcelable(MainActivity.EDIT_MOVIE_ARRAY);
        final int position = intent_edit.getExtras().getInt(MainActivity.EDIT_MOVIE_WHICH);

        name.setText(getarray.name1);
        description.setText(getarray.desc);
        year.setText(getarray.yearr);
        IMDB.setText(getarray.imdb);
        Log.d("movie", "onCreate: " + getarray.movie_rating);
        seekBar.setProgress(Integer.parseInt(getarray.movie_rating));
        rating.setText(getarray.movie_rating + "");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekvalue = min + (progress * step);
                rating.setText(seekvalue + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ArrayAdapter myAdap = (ArrayAdapter) genre.getAdapter();
        int genre_position = myAdap.getPosition(getarray.movie_category);
        genre.setSelection(genre_position);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String desc = description.getText().toString();
                String yearr = (year.getText().toString());
                String imdb = IMDB.getText().toString();
                String movie_rating = Integer.toString(seekBar.getProgress());
                String movie_category = genre.getSelectedItem().toString();


                if (name1.matches("") || desc.matches("") || yearr.matches("") || imdb.matches("")) {
                    Toast.makeText(name.getContext(), "Please Enter all the details", Toast.LENGTH_LONG).show();
                } else if (movie_category.matches("Select")) {
                    Toast.makeText(name.getContext(), "Please select a genre ", Toast.LENGTH_LONG).show();
                } else {
                    double_year = Double.parseDouble(yearr);
                    boolean url = Patterns.WEB_URL.matcher(imdb).matches();
                    if ((double_year == 0) || (double_year > 2018)) {
                        Toast.makeText(name.getContext(), "Please Enter a valid year ", Toast.LENGTH_LONG).show();
                    } else if(url == false){
                        Toast.makeText(name.getContext(), "Please Enter a valid imdb ", Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent3_1 = new Intent(ThirdActivity.this, MainActivity.class);

                        intent3_1.putExtra(MainActivity.EDITMOVIE_KEY, new AddMovie(name1, desc, yearr, imdb, movie_rating, movie_category));
                        intent3_1.putExtra(MainActivity.EDITWHICH_KEY, position);
                        setResult(RESULT_OK, intent3_1);

                        finish();
                    }
                }

            }


        });
    }
}
