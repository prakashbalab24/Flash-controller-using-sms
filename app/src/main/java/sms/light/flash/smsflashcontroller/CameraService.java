package sms.light.flash.smsflashcontroller;

/**
 * Created by prakash-bala on 11/11/16.
 */


        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.hardware.Camera;
        import android.hardware.camera2.CameraManager;
        import android.media.MediaPlayer;
        import android.os.Build;
        import android.widget.ImageButton;


public class CameraService {

    private CameraManager mCameraManager;
    private String mCameraId;
    private ImageButton mTorchOnOffButton;
    private Boolean isTorchOn;
    private MediaPlayer mp;
    private Context context;
    private Camera cam;
    Camera.Parameters p;

    CameraService(Context context) {
        isTorchOn = false;
        this.context = context;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            try {

                mCameraId = mCameraManager.getCameraIdList()[0];
            } catch (Exception e)

            {
                e.printStackTrace();
            }
        } else {
            if (cam == null) {
                try {
                    cam = Camera.open();
                    p = cam.getParameters();

                } catch (RuntimeException e) {
                    //Log.e("Camera Error. Failed to Open. Error: ", e.getMessage());
                }
            }
        }
    }



    public void turnOffFlashLight() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {


                mCameraManager.setTorchMode(mCameraId, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            p.setFlashMode(p.FLASH_MODE_OFF);
            cam.setParameters(p);
            cam.stopPreview();

        }

    }


    public void turnOnFlashLight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {


                mCameraManager.setTorchMode(mCameraId, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            p.setFlashMode(p.FLASH_MODE_TORCH);
            cam.setParameters(p);
            cam.startPreview();
        }
        }
    public void destroyCamera()
    {
        cam.release();
    }
    public boolean camAvail()
    {
        if(cam!=null)
        {
            return true;
        }
        return false;
    }
}