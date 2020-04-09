package com.qiniu.pili.droid.streaming;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/3 15:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/3$
 * @updateDes ${TODO}
 */

public class StreamingEnvKit {
    private static boolean init;

    public static void init(Context context) {
        StreamingEnv.init(context);
        if (!init) {
//            StreamingEnv.init(getApplicationContext());

            init = true;
        }
    }

    /**
     *  检查是否有前置摄像头
     * @param facing
     * @return
     */
    private static boolean checkCameraFacing(final int facing) {
        if (getSdkVersion() < Build.VERSION_CODES.GINGERBREAD) {
            return false;
        }
        final int cameraCount = Camera.getNumberOfCameras();
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, info);
            if (facing == info.facing) {
                return true;
            }
        }
        return false;
    }
    public static boolean hasBackFacingCamera() {
        final int CAMERA_FACING_BACK = 0;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }
    public static boolean hasFrontFacingCamera() {
        final int CAMERA_FACING_BACK = 1;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }
    public static int getSdkVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }
}
