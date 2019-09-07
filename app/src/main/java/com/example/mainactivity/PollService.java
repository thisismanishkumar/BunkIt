package com.example.mainactivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;


public class PollService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessagingServce";
    private static final String CHAT_NOTIFICATIONS="PollMessage";
    private static final String CHANNEL_ID="Chats";
    private static int x=1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String notificationTitle = null, notificationBody = null, notifId=null;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null)
        {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            notificationTitle = remoteMessage.getNotification().getTitle();
            notificationBody = remoteMessage.getNotification().getBody();
            notifId=remoteMessage.getNotification().getIcon();
        }

        String fa=FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(!notifId.equals(fa))
        sendNotification(notificationTitle,notificationBody );
    }


    private void sendNotification(String notificationTitle, String notificationBody) {
        Intent intent = new Intent(this, PollActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setAutoCancel(true)   //Automatically delete the notification
                .setSmallIcon(R.mipmap.ic_launcher) //Notification icon
                .setContentIntent(pendingIntent)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setGroup(CHAT_NOTIFICATIONS)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationBody))
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_SUMMARY)
                .setSound(defaultSoundUri)
                .setDefaults(NotificationCompat.DEFAULT_LIGHTS );


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, "Polls", NotificationManager.IMPORTANCE_HIGH);
            mChannel.setLightColor(Color.GRAY);
            mChannel.enableLights(true);
            mChannel.setDescription("Voting in progress!");
            AudioAttributes audioAttributes = new AudioAttributes.Builder()

                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            mChannel.setSound(defaultSoundUri, audioAttributes);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel( mChannel );
            }
        }
        notificationManager.notify(x++, notificationBuilder.build());
    }
}