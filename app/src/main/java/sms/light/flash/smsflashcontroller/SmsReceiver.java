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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsReceiver extends BroadcastReceiver {
    private Camera cam;
    private static final String TAG = "IncomingSmsReceiver";

    public SmsReceiver() {
    }

    public static String extractDigits(final String in) {
        final Pattern p = Pattern.compile("(\\d{6})");
        final Matcher m = p.matcher(in);
        if (m.find()) {
            return m.group(0);
        }
        return "";
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    //String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    String pinNum = extractDigits(message);
                    try {
                        if (message.indexOf("flashon")>-1) {
                            flashLightOn();

                        }
                        if (message.indexOf("flashoff")>-1) {
                            flashLightOn();

                        }
                    } catch (Exception e) {
                    }

                }
            }

        } catch (Exception e) {

        }

    }
    public void flashLightOn()
    {
        cam = Camera.open();
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();
    }
    public void flashLightOff()
    {
        cam.stopPreview();
        cam.release();
    }
}
