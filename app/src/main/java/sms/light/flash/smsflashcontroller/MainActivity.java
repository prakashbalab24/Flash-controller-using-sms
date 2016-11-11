package sms.light.flash.smsflashcontroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button turnon,turnoff;
    private CameraService cameraService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turnon = (Button) findViewById(R.id.turnon);
        turnoff = (Button) findViewById(R.id.turnoff);
        cameraService = new CameraService(MainActivity.this);

        turnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cameraService.turnOnFlashLight();
            }
        });

        turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraService.turnOffFlashLight();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cameraService.camAvail()) {
            cameraService.destroyCamera();
        }

    }
}
