package com.example.fridgeinspector;
import android.app.NotificationChannel ;
import android.app.NotificationManager ;
import android.app.Service ;
import android.content.Intent ;
import android.os.Handler ;
import android.os.IBinder ;
import androidx.core.app.NotificationCompat;
import android.util.Log ;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer ;
import java.util.TimerTask ;

public class Notifications extends Service {
    Timer timer ;
    TimerTask timerTask ;
    String TAG = "Timers" ;
    int Your_X_SECS = 5;
    final Handler handler = new Handler() ;

    @Override
    public IBinder onBind (Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent , int flags , int startId) {
        super .onStartCommand(intent , flags , startId) ;
        String foodname = intent.getExtras().get("NAME").toString();
        String expirationDateString = intent.getExtras().get("EXPIRATION_DATE").toString();
        Date expirationDate = new Date();
        try {
            expirationDate = new SimpleDateFormat("dd//MM/yyyy").parse(expirationDateString);
        }
        catch(Exception e){

        }
        createNewTimer(foodname, expirationDate, true);
        createNewTimer(foodname, expirationDate, false);
        return START_STICKY ;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        stopTimer() ;
        super .onDestroy() ;
    }

    public void createNewTimer(String foodName, Date expirationDate, boolean expired) {
        timer = new Timer() ;
        createNewTimerTask(foodName, expired);
        if(expired){
            timer.schedule( timerTask , expirationDate); //TODO: does not work yet, Notification shows instantly regardless of scheduled Date.  Maybe use time difference in Miliseconds instead?
        }
        else{
            expirationDate.setDate(expirationDate.getDay()-3);
            timer.schedule(timerTask, expirationDate); //TODO: does not work yet, Notification shows instantly regardless of scheduled Date. Maybe use time difference in Miliseconds instead?
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel() ;
            timer=null;
        }
    }

    public void createNewTimerTask (String foodName, boolean expired) {
        timerTask=new TimerTask() {
            public void run () {
                handler.post( new Runnable() {
                    public void run() {
                        createNewNotification(foodName, expired);
                    }
                }) ;
            }
        } ;
    }
    private void createNewNotification(String foodName, boolean expired) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext() , "default") ;
        if(expired){
            mBuilder.setContentTitle( "Food expired!" );
            mBuilder.setContentText(foodName + " just expired!");
        }
        else{
            mBuilder.setContentTitle( "Food expires soon!" );
            mBuilder.setContentText(foodName + " will expire in three days!");
        }
        mBuilder.setSmallIcon(R.drawable. ic_launcher_foreground );
        mBuilder.setAutoCancel(true);
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH) ;
            mBuilder.setChannelId("10001") ;
            assert mNotificationManager!=null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify((int) System.currentTimeMillis (), mBuilder.build()) ;
    }

}