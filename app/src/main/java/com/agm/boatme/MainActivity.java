package com.agm.boatme;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mapbox.mapboxsdk.Mapbox;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends VoiceActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;

    TextToSpeech tts;

    private static final Integer ID_PROMPT_QUERY = 0;
    private static final Integer ID_PROMPT_INFO = 1;
    private long startListeningTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_main);

        // Init speacher
        initSpeechInputOutput(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.speachButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
                speak("Hola", ID_PROMPT_QUERY);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_route, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Initialize points
        XmlResourceParser parser = getResources().getXml(R.xml.points);
        MarinePoints.getInstance().parseData(parser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void showRecordPermissionExplanation() {
        Toast.makeText(getApplicationContext(), "BoatMe necesita acceder al micrófono para funcionar correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordAudioPermissionDenied() {
        Toast.makeText(getApplicationContext(), "BoatMe no funcionará correctamente sin micrófono.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void processAsrResults(ArrayList<String> nBestList, float[] nBestConfidences) {
        if(nBestList!=null){
            if(nBestList.size()>0){
                String bestResult = nBestList.get(0);
                try {
                    RecognitionResponse answer = RecognitionManager.getInstance().getAnswer(bestResult);
                    if (answer.success) {
                        speak(answer.response, ID_PROMPT_INFO);

                        if (answer.continueTalking) {
                            startListening();
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.toString());
                }
            }
        }
    }

    @Override
    public void processAsrReadyForSpeech() {}

    @Override
    public void processAsrError(int errorCode) {
        Toast.makeText(getApplicationContext(), "error code: " + errorCode, Toast.LENGTH_SHORT);
    }

    @Override
    public void onTTSDone(String uttId) {
        if(uttId.equals(ID_PROMPT_QUERY.toString())) {
            runOnUiThread(new Runnable() {
                public void run() {
                    startListening();
                }
            });
        }
    }

    @Override
    public void onTTSError(String uttId) {

    }

    @Override
    public void onTTSStart(String uttId) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    public boolean deviceConnectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
    }

    private void startListening() {

        if (deviceConnectedToInternet()) {
            try {
                startListeningTime = System.currentTimeMillis();
                listen(Locale.ENGLISH, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM, 1); //Start listening
            } catch (Exception e) {
                this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });

                try {
                    speak("ERROR", "EN", ID_PROMPT_INFO);
                } catch (Exception ex) {
                }

            }
        } else {
            this.runOnUiThread(new Runnable() { //Toasts must be in the main thread
                public void run() {
                    Toast.makeText(getApplicationContext(), "Comprobando conexión a internet...", Toast.LENGTH_SHORT).show();
                }
            });
            try {
                speak("Dispositivo sin conexión a internet", "EN", ID_PROMPT_INFO);
            } catch (Exception ex) {
            }
        }
    }
}
