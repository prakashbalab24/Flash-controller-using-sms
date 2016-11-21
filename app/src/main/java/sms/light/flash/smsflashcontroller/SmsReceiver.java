package sms.light.flash.smsflashcontroller;

/**
 * Created by prakash-bala on 10/11/16.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsReceiver extends BroadcastReceiver {

    public SmsReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i("Smsmsg",message);
                    try {
                        if (message.indexOf("flashon")>-1) {
                            CameraService.CameraInstance(context).turnOnFlashLight();

                        }
                        if (message.indexOf("flashoff")>-1) {
                            CameraService.CameraInstance(context).turnOffFlashLight();

                        }
                    } catch (Exception e) {
                    }

                }
            }

        } catch (Exception e) {

        }

    }
}
