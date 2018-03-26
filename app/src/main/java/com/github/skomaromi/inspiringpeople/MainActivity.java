package com.github.skomaromi.inspiringpeople;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<InspiringPerson> peopleList = InspiringPerson.getArrayListFromFile("people.json", this);

        InspiringPerson[] peopleArray = new InspiringPerson[peopleList.size()];
        peopleList.toArray(peopleArray);

        ListAdapter la = new ListAdapter(this,  peopleArray);

        mListView = findViewById(R.id.lvMain);
        mListView.setAdapter(la);
    }
}
