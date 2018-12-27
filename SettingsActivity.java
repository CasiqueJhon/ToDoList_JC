package com.jhonisaac.todolist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class
SettingsActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private boolean mSound;

    public static final int FAST = 0;
    public static final int SLOW = 1;
    public static final int NONE = 2;

    private int animationOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
            //Instantiating the SharedPreferences and the SharedPreferences.Editor
            mPrefs = getSharedPreferences("To Do List" , MODE_PRIVATE);
            mEditor = mPrefs.edit();

            //Logic to set sound on or off
            //Get the value of mSound by default
        mSound = mPrefs.getBoolean("sound" , true);

        //Setting the listener
        CheckBox checkBoxSound = findViewById(R.id.sound_cb);
        if (mSound){
            checkBoxSound.setChecked(true);
        }else {
            checkBoxSound.setChecked(false);
        }

        checkBoxSound.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        //if is on, turn off the music and equals is off
                        mSound = !mSound;
                        //Then apply SharedPreferences.Editor
                        mEditor.putBoolean("sound" , mSound);
                    }
                }
        );

        //logic to change type of animations
        //First I get the current value
        animationOption = mPrefs.getInt("anim_option", FAST);

        //Extract all the RadioGroup
        final RadioGroup radioGroup = findViewById(R.id.radio_gruop);
        //Clear all checked btn
        radioGroup.clearCheck();
        //Find it the buttons on layout
        switch (animationOption) {
            case FAST:
                radioGroup.check(R.id.rb_fast);
                break;
            case SLOW:
                radioGroup.check(R.id.rb_slow);
                break;
            case NONE:
                radioGroup.check(R.id.rb_none);
                break;
        }
        //Setting the listener
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        //Getting the CheckedId of any item the user press
                        RadioButton rb = radioGroup.findViewById(checkedId);
                        //Now setting the orders
                        if (null != rb) {

                            switch (rb.getId()){
                                case R.id.rb_fast:
                                    animationOption = FAST;
                                    break;
                                case R.id.rb_slow:
                                    animationOption = SLOW;
                                    break;
                                case R.id.rb_none:
                                    animationOption = NONE;
                                    break;
                            }
                            //Make the changes into the hard drive
                            mEditor.putInt("anim_option", animationOption);
                        }
                    }
                }
         );
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Apply all the settings when the user change the activity.
        mEditor.apply();
    }
}
