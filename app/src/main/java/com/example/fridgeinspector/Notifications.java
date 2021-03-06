package com.example.fridgeinspector;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class Notifications extends Service {
    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        String foodname = intent.getExtras().get("NAME").toString();
        String timeDiffString = intent.getExtras().get("TIME_DIFF").toString();
        long timeDiff = Long.parseLong(timeDiffString);
        createNewTimer(foodname, timeDiff, true);
        createNewTimer(foodname, timeDiff, false);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
        stopTimer();
        super.onDestroy();
    }

    public void createNewTimer(String foodName, long expirationDateDiff, boolean expired) {
        timer = new Timer();
        createNewTimerTask(foodName, expired);

        if (expired) {
            timer.schedule(timerTask, expirationDateDiff);
        } else {
            expirationDateDiff = expirationDateDiff - (1000 * 60 * 60 * 24 * 3); //reduce Timer by 3 days
            if (expirationDateDiff < 0 && expirationDateDiff > -1000) {
                expirationDateDiff = 0;
            }
            if (expirationDateDiff >= 0) {
                timer.schedule(timerTask, expirationDateDiff);
            }
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void createNewTimerTask(String foodName, boolean expired) {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(() -> createNewNotification(foodName, expired));
            }
        };
    }

    private void createNewNotification(String foodName, boolean expired) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "default");

        if (expired) {
            mBuilder.setContentTitle("Food expired!");
            mBuilder.setContentText(foodName + " just expired!");
        } else {
            mBuilder.setContentTitle("Food expires soon!");
            mBuilder.setContentText(foodName + " will expire in three days!");
        }

        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
        mBuilder.setAutoCancel(true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH);
            mBuilder.setChannelId("10001");
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        assert mNotificationManager != null;
        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }

}