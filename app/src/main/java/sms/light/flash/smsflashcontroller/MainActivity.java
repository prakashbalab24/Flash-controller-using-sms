package sms.light.flash.smsflashcontroller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS = 111;
    private Button turnon,turnoff,start,stop;
    private BackgroundService backgroundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turnon = (Button) findViewById(R.id.turnon);
        turnoff = (Button) findViewById(R.id.turnoff);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        checkPermission();
        backgroundService = new BackgroundService();



        turnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CameraService.CameraInstance(MainActivity.this).turnOnFlashLight();
            }
        });

        turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraService.CameraInstance(MainActivity.this).turnOffFlashLight();

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(getBaseContext(), BackgroundService.class));


            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(getBaseContext(), BackgroundService.class));

            }
        });


    }
    public boolean checkPermission()
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) this, Manifest.permission.READ_SMS)) {

                } else {
                    ActivityCompat.requestPermissions((Activity)this, new String[]{Manifest.permission.READ_SMS}, MY_PERMISSIONS);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
