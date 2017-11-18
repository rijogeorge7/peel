package com.example.rijogeorge.peel.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rijogeorge.peel.R;
import com.example.rijogeorge.peel.model.domain.Content;
import com.example.rijogeorge.peel.model.domain.PlayList;
import com.example.rijogeorge.peel.model.domain.Preroll;
import com.example.rijogeorge.peel.model.domain.Video;
import com.example.rijogeorge.peel.model.mock.ContentJson;
import com.example.rijogeorge.peel.utilities.PlaylistHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Content content;
    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = new Gson().fromJson(ContentJson.contentJson,Content.class);
        attachUI();
    }

    private void attachUI() {
        Spinner contentSpinner = (Spinner) findViewById(R.id.contentSpinner);
        Spinner countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        ArrayAdapter<CharSequence> contentAdapter = ArrayAdapter.createFromResource(this,
                R.array.content_names, android.R.layout.simple_spinner_item);
        contentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this,
                R.array.countries, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        contentSpinner.setAdapter(contentAdapter);
        countrySpinner.setAdapter(countryAdapter);
        output = (TextView) findViewById(R.id.output);
        Button submitButton = (Button) findViewById(R.id.submitButton);
        //used lambda , need to set source compatibility to java 8
        submitButton.setOnClickListener( v -> displayPlayLists(contentSpinner.getSelectedItem().toString(),countrySpinner.getSelectedItem().toString()));
    }

    private void displayPlayLists(String contentName, String country) {
        List<PlayList> playLists = PlaylistHelper.getPlayLists(contentName, country);
        StringBuilder outputStr = new StringBuilder();
        if(playLists.size()>0){
            int playListCount = 1;
            for(PlayList pl : playLists){
                outputStr.append("Playlist "+playListCount+"\n");
                playListCount++;
                outputStr.append("[ ");
                for(Video video : pl.getPrerollVideos()) {
                    outputStr.append(video.getName());
                    outputStr.append(" , ");
                }
                outputStr.append(pl.getContentVideo().getName());
                outputStr.append(" ]\n\n");
            }
        } else {
            outputStr.append(getString(R.string.noPlaylistText));
        }
        output.setText(outputStr.toString());
    }



}
