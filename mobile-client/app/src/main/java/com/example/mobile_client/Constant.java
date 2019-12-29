package com.example.mobile_client;

public class Constant {
    public static final String IntentID = "StdId";
    public static final int CAMERA_PERMISSION_CODE = 0;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final String PrefID = "MySavedID";
    public static final String AttendanceURL = "http://104.154.52.199:3000/attendance";
    public static final String RegisterURL = "http://104.154.52.199:3000/register";
    public static final String LoginURL = "http://104.154.52.199:3000/login";

    public static boolean CheckID(String TempID){
        if (TempID.length() == 7) {     //  xxPxxxx
            if (TempID.charAt(0) >= '0' && TempID.charAt(0) <= '9'){
                if (TempID.charAt(1) >= '0' && TempID.charAt(1) <= '9'){
                    if (TempID.charAt(2) == 'P' || TempID.charAt(2) == 'p'){
                        if (TempID.charAt(3) >= '0' && TempID.charAt(3) <= '9'){
                            if (TempID.charAt(4) >= '0' && TempID.charAt(4) <= '9'){
                                if (TempID.charAt(5) >= '0' && TempID.charAt(5) <= '9'){
                                    if (TempID.charAt(6) >= '0' && TempID.charAt(6) <= '9'){
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean CheckName(String TempName){
        if (TempName.length() > 3) {
            if (TempName.indexOf(' ') != -1){
                return true;
            }
        }
        return false;
    }
}
