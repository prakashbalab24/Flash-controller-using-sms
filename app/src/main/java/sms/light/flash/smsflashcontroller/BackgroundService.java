package sms.light.flash.smsflashcontroller;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by prakash-bala on 21/11/16.
 */

public class BackgroundService extends Service {
    private SmsReceiver smsReceiver;
    private IntentFilter filter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Background SMS Monitoring Started",Toast.LENGTH_LONG).show();
        smsReceiver = new SmsReceiver();
        filter = new IntentFilter();
        filter.addAction("android.provider.Telephony.SMS_RECEIVED");
        filter.setPriority(2147483647);
        this.registerReceiver(smsReceiver, filter);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Background SMS Monitoring Stopped",Toast.LENGTH_LONG).show();
        this.unregisterReceiver(smsReceiver);
        CameraService.CameraInstance(this).destroyCamera();
    }
}
