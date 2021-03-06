package com.github.skomaromi.inspiringpeople;

import android.content.Context;
import android.graphics.drawable.Drawable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InspiringPerson {
    private Drawable mImage;
    private String mName, mBio;
    private Date mL0, mL1;
    private ArrayList<String> mQuotes;

    private InspiringPerson() {
        mQuotes = new ArrayList<>();
    }

    public Drawable getmImage() {
        return mImage;
    }
    public void setmImage(Drawable mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmBio() {
        return mBio;
    }
    public void setmBio(String mBio) {
        this.mBio = mBio;
    }

    public Date getmL0() { return mL0; }
    public void setmL0(Date mL0) { this.mL0 = mL0; }

    public Date getmL1() { return mL1; }
    public void setmL1(Date mL1) { this.mL1 = mL1; }

    public ArrayList<String> getmQuotes() { return mQuotes; }
    public void setmQuotes(ArrayList<String> mQuotes) { this.mQuotes = mQuotes; }

    public String getmLifespan() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        String l1 = mL1 == null ? "..." : sdf.format(mL1);
        return sdf.format(mL0) + " - " + l1;
    }

    public static ArrayList<InspiringPerson> getArrayListFromFile(String file, Context context) {
        ArrayList<InspiringPerson> personArrayList = new ArrayList<>();

        try {
            String jsonFileStringified = loadJSONFromAsset("people.json", context);
            JSONObject json = new JSONObject(jsonFileStringified);
            JSONArray peopleJSONArray = json.getJSONArray("people");

            for (int i = 0; i < peopleJSONArray.length(); i++) {
                InspiringPerson ip = new InspiringPerson();
                JSONObject jsonPerson = peopleJSONArray.getJSONObject(i);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                // decode string to drawable
                String image = jsonPerson.getString("image");
                int id = context.getResources().getIdentifier(
                        image,
                        "drawable",
                        context.getPackageName()
                );
                ip.mImage = context.getDrawable(id);

                ip.mName = jsonPerson.getString("name");
                ip.mBio = jsonPerson.getString("bio");

                // decoding dates
                String l0 = jsonPerson.getString("l0"),
                       l1 = jsonPerson.getString("l1");

                l0 = l0 == "null" ? null : l0;
                l1 = l1 == "null" ? null : l1;

                ip.mL0 = l0 == null ? null : sdf.parse(l0);
                ip.mL1 = l1 == null ? null : sdf.parse(l1);

                // decoding quotes
                JSONArray jaQuotes = jsonPerson.getJSONArray("quotes");
                for (int j = 0; j < jaQuotes.length(); j++) {
                    String q = jaQuotes.getString(j);
                    ip.mQuotes.add(q);
                }

                personArrayList.add(ip);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return personArrayList;
    }

    private static String loadJSONFromAsset(String filename, Context context) {
        String json = null;
        try {
            InputStream i = context.getAssets().open(filename);
            byte[] buf = new byte[i.available()];
            i.read(buf);
            i.close();
            json = new String(buf, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }
}
