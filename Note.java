package com.jhonisaac.todolist;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {
    //Variables
    private String mTitle;
    private String mDescription;
    private boolean mIdea;
    private boolean mTodo;
    private boolean mImportant;

    private static final String JSON_TITLE = "title";
    private static final String JSON_DESCRIPTION = "description";
    private static final String JSON_IDEA = "idea";
    private static final String JSON_TODO = "todo";
    private static final String JSON_IMPORTANT = "important";

    public Note(){

    }

    //Constructor to created a note from JSONObject
    public Note (JSONObject jo) throws JSONException {
        mTitle = jo.getString(JSON_TITLE);
        mDescription = jo.getString(JSON_DESCRIPTION);
        mIdea = jo.getBoolean(JSON_IDEA);
        mTodo = jo.getBoolean(JSON_TODO);
        mImportant = jo.getBoolean(JSON_IMPORTANT);
    }

    //Serialize the variables from Note and convert to a JsonObject.

    public JSONObject convertNoteToJson() throws JSONException {

        JSONObject jo = new JSONObject();

        jo.put(JSON_TITLE, mTitle);
        jo.put(JSON_DESCRIPTION, mDescription);
        jo.put(JSON_IDEA, mIdea);
        jo.put(JSON_TODO, mTodo);
        jo.put(JSON_IMPORTANT, mImportant);

        return jo;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isIdea() {
        return mIdea;
    }

    public void setIdea(boolean idea) {
        mIdea = idea;
    }

    public boolean isTodo() {
        return mTodo;
    }

    public void setTodo(boolean todo) {
        mTodo = todo;
    }

    public boolean isImportant() {
        return mImportant;
    }

    public void setImportant(boolean important) {
        mImportant = important;
    }

}
