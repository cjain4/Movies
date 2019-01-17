package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    private TextView title;
    private TextView desc;
    private TextView genre;
    private TextView rating;
    private TextView year;
    private TextView imdb;
    private Button finish;
    private ImageView first;
    private ImageView last;
    private ImageView next;
    private ImageView previous;
    private TextView movie;
    public  int i=0;
    private ArrayList<AddMovie> getmovieyear;
    private ArrayList<AddMovie> getmovierating;
    private AddMovie abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        title = findViewById(R.id.textViewTitleResult);
        desc = findViewById(R.id.textView16);
        genre = findViewById(R.id.textViewGenreResult);
        rating = findViewById(R.id.textViewRatingResult);
        year = findViewById(R.id.textViewYearResult);
        imdb = findViewById(R.id.textViewIMDBResult);
        finish = findViewById(R.id.button);
        first = findViewById(R.id.imageViewFirst);
        last = findViewById(R.id.imageViewLast);
        next = findViewById(R.id.imageViewNext);
        previous = findViewById(R.id.imageViewPrevious);
        movie = findViewById(R.id.textViewMovies);

        desc.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        if(intent.getExtras().containsKey(MainActivity.LIST_BY_YEAR)){
            getmovieyear = intent.getParcelableArrayListExtra(MainActivity.LIST_BY_YEAR);
            this.searching_Year(i);
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     i=0;
                    searching_Year(i);

                }
            });

            last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i=getmovieyear.size()-1;
                    searching_Year(i);


                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i<getmovieyear.size()-1){
                        i=i+1;
                        searching_Year(i);
                    }


                }
            });

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i>0){
                        i=i-1;
                        searching_Year(i);
                    }

                }
            });

        }

        if(intent.getExtras().containsKey(MainActivity.LIST_BY_RATING)) {

            getmovierating = intent.getParcelableArrayListExtra(MainActivity.LIST_BY_RATING);
            setTitle("Movies by Rating");
            this.searching_Rating(i);


            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i=0;
                    searching_Rating(i);

                }
            });

            last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i=getmovierating.size()-1;
                    searching_Rating(i);


                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i<getmovierating.size()-1){
                        i=i+1;
                        searching_Rating(i);
                    }


                }
            });

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i>0){
                        i=i-1;
                        searching_Rating(i);
                    }

                }
            });


        }

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    public void searching_Year(int i){
        AddMovie abc = getmovieyear.get(i);
        title.setText(abc.name1);
        desc.setText(abc.desc);
        genre.setText(abc.movie_category);
        rating.setText(abc.movie_rating);
        year.setText(abc.yearr);
        imdb.setText(abc.imdb);

    }

    public void searching_Rating(int i){
        AddMovie abc = getmovierating.get(i);
        title.setText(abc.name1);
        desc.setText(abc.desc);
        genre.setText(abc.movie_category);
        rating.setText(abc.movie_rating);
        year.setText(abc.yearr);
        imdb.setText(abc.imdb);

    }
}
