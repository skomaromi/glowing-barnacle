package com.github.skomaromi.inspiringpeople;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private InspiringPerson[] peopleArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<InspiringPerson> peopleList = InspiringPerson.getArrayListFromFile("people.json", this);

        peopleArray = new InspiringPerson[peopleList.size()];
        peopleList.toArray(peopleArray);

        ListAdapter la = new ListAdapter(this,  peopleArray);

        mListView = findViewById(R.id.lvMain);
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(la);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<String> quotes = peopleArray[position].getmQuotes();

        Toast t;
        String q;
        Random r = new Random();

        q = quotes.get(r.nextInt(quotes.size()));

        t = Toast.makeText(view.getContext(), q, Toast.LENGTH_LONG);

        t.show();
    }
}
