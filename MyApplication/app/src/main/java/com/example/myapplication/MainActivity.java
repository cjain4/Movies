package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.R.layout.select_dialog_singlechoice;

public class MainActivity extends AppCompatActivity {
    private Button btnAdd;
    private Button btnEdit;
    private Button btnDelete;
    private Button btnYear;
    private Button btnRating;
    public  ArrayList<AddMovie> movielist = new ArrayList<AddMovie>();
    public  ArrayList<AddMovie> templist = new ArrayList<AddMovie>();
    public static String EDIT_MOVIE_WHICH = "EDITMOVIEWHICH";
    public static String EDIT_MOVIE_ARRAY = "EDITMOVIEARRAY";
    public static String LIST_BY_YEAR = "LISTBYYEAR";
    public static String LIST_BY_RATING = "LISTBYRATING";
    public static final int Req_Code_add = 100;
    public static final int Req_Code_edit = 90;
    public static String ADDMOVIE_KEY = "ADDMOVIE";
    public static String EDITMOVIE_KEY = "EDITMOVIE";
    public static String EDITWHICH_KEY = "EDITWHICH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //adding items to list
        movielist.add(new AddMovie("Fantastic Four", "Transported to an alternate universe, four young outsiders gain superhuman powers as they alter their physical form in shocking ways. Reed Richards becomes Mr. Fantastic, able to stretch and twist his body at will, while pal Ben Grimm gains immense strength as the Thing.", "2015", "https://www.imdb.com/title/tt1502712/", "4", "Animation"));
        movielist.add(new AddMovie("Mission Impossible", "Ethan Hunt and the IMF team join forces with CIA assassin August Walker to prevent a disaster of epic proportions. Arms dealer John Lark and a group of terrorists known as the Apostles plan to use three plutonium cores for a simultaneous nuclear attack on the Vatican, Jerusalem and Mecca, Saudi Arab", "2018", "https://www.imdb.com/title/tt0117060/", "3", "Action"));
        movielist.add(new AddMovie("The Godfather", "Widely regarded as one of the greatest films of all time, this mob drama, based on Mario Puzo's novel of the same name, focuses on the powerful Italian-American crime family of Don Vito Corleone (Marlon Brando). When the don's youngest son, Michael (Al Pacino), reluctantly joins the Mafia", "1972", "https://www.imdb.com/title/tt0068646/?ref_=", "1", "Crime"));
        movielist.add(new AddMovie("Titanic", "ames Cameron's \"Titanic\" is an epic, action-packed romance set against the ill-fated maiden voyage of the R.M.S. Titanic; the pride and joy of the White Star Line and, at the time, the largest moving object ever built. She was the most luxurious liner of her era -- the \"ship of dreams\"", "1997", "https://www.imdb.com/title/tt0120338/", "5", "Horror"));

        btnAdd = findViewById(R.id.buttonAdd);
        btnEdit = findViewById(R.id.buttonEdit);
        btnDelete = findViewById(R.id.buttonDelete);
        btnYear = findViewById(R.id.buttonYear);
        btnRating = findViewById(R.id.buttonRating);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent_add, MainActivity.Req_Code_add);
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> movie_names = new ArrayList<>();
                for (int i = 0; i < movielist.size(); i++) {
                    Log.d("movielist", "onCreate: " + movielist);
                    movie_names.add(movielist.get(i).name1);
                }
                Log.d("INSIDE EDIT",movie_names.toString());
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, movie_names);

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pick a Movie");
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("chirag", "" + Integer.toString(which));
                        Intent intent_edit = new Intent(MainActivity.this, ThirdActivity.class);
                        AddMovie m = movielist.get(which);
                        intent_edit.putExtra(EDIT_MOVIE_WHICH, which);
                        intent_edit.putExtra(EDIT_MOVIE_ARRAY, m);
                        Log.d("mval", "onClick: " + which);
                        startActivityForResult(intent_edit, MainActivity.Req_Code_edit);
                    }
                });
                builder.create();
                builder.show();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> movie_names = new ArrayList<>();
                for (int i = 0; i < movielist.size(); i++) {
                    movie_names.add(movielist.get(i).name1);
                }

                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, movie_names);
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pick a Movie");
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        movielist.remove(which);
                        movie_names.remove(which);
                    }
                });
                builder.create().show();
            }
        });

        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("shit", "onClick: " + movielist);
                Collections.sort(movielist, new Comparator<AddMovie>() {
                    @Override
                    public int compare(AddMovie o1, AddMovie o2) {
                        return (Integer.parseInt(o1.yearr) - Integer.parseInt(o2.yearr));
                    }
                });
                Log.d("CJJJJJ", "onClick: " + movielist);
                Intent intent = new Intent("com.example.myapplication.intent.action.VIEW");
                intent.addCategory(intent.CATEGORY_DEFAULT);
                intent.putParcelableArrayListExtra(LIST_BY_YEAR, (ArrayList) movielist);
                startActivity(intent);

            }
        });

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(movielist, new Comparator<AddMovie>() {
                    @Override
                    public int compare(AddMovie o1, AddMovie o2) {
                        return (Integer.parseInt(o2.movie_rating) - Integer.parseInt(o1.movie_rating));
                    }
                });
                Intent intent = new Intent(MainActivity.this, FourthActivity.class);
                intent.putParcelableArrayListExtra(LIST_BY_RATING, (ArrayList) movielist);
                startActivity(intent);


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Req_Code_add) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                AddMovie addMovie = bundle.getParcelable(MainActivity.ADDMOVIE_KEY);
                movielist.add(addMovie);
                data.removeExtra(MainActivity.ADDMOVIE_KEY);
            }
        }

        if(requestCode == Req_Code_edit){
            if (resultCode == RESULT_OK) {
                Bundle b = data.getExtras();
                AddMovie addMovie = b.getParcelable(MainActivity.EDITMOVIE_KEY);
                int position = b.getInt(MainActivity.EDITWHICH_KEY);
                movielist.set(position,addMovie);
            }
        }

    }


}
