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

    InspiringPerson() {}

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

    public String getmLifespan() {
        SimpleDateFormat spf = new SimpleDateFormat("yyyy/MM/DD");

        String l1 = mL1 == null ? "..." : spf.format(mL1);
        return spf.format(mL0) + " - " + l1;
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/DD");

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
