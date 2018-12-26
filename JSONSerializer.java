package com.jhonisaac.todolist;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {

    private String mFileName;
    private Context mContext;

    public JSONSerializer(String fileName , Context context) {
        this.mFileName = fileName;
        this.mContext = context;
    }

    public void saved (List<Note> notes) throws IOException , JSONException {
        //Array JSONObject
        JSONArray jArray = new JSONArray();
        //convert every Note into a JSONObjects and save them into a JSONArray
        for (Note n : notes) {
            jArray.put(n.convertNoteToJson());
        }
        //Need to use a Writer to save the JSONObjects
        Writer writer = null;
        try {
            //OutputStream, open the file where all save
            OutputStream out = mContext.openFileOutput(mFileName, mContext.MODE_PRIVATE);
            //Set the writer container
            writer = new OutputStreamWriter(out);
            //Writer writes into the memory all the array transform like String
            writer.write(jArray.toString());
        } finally {
            //Is important to close the writer.
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Note> load() throws IOException , JSONException {
        //Array objects Note in JAVA
        ArrayList<Note> notes = new ArrayList<>();
        //BufferedReader to read the file
        BufferedReader reader = null;
        try {
            //Open the file JSON to read and process
            InputStream in = mContext.openFileInput(mFileName);
            //reader already know where to read the data from JSON files
            reader = new BufferedReader(new InputStreamReader(in));
            //read the string from JSON files with a StringBuilder
            StringBuilder jsonString = new StringBuilder();
            //variable to read the currentLine
            String currentLine = null;
            //now read all the JSON file
            //while the currentLine don't be null
            while ((currentLine = reader.readLine())!= null) {
                //added a new line
                jsonString.append(currentLine);
            }
            //in this point I already read the JSON file and convert into a long String with the Note Objects.
            //From array String to a array JSONObject
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                //getting the array notes with all the objects from note class.
                notes.add(new Note(jsonArray.getJSONObject(i)));
            }
        }catch (FileNotFoundException e) {

        }
        finally {
            if (reader != null) {
                reader.close();
            }
        }
        return notes;
    }
    
}
