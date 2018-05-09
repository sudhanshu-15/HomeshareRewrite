package edu.indiana.soic.homeshare.homeshare.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import edu.indiana.soic.homeshare.homeshare.view.HomeActivity;
import edu.indiana.soic.homeshare.homeshare.R;

public class HomeshareMessagingService extends FirebaseMessagingService {

    private static final String TAG = "HomeshareMessagingServi";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getNotification() != null){
            Log.d(TAG, "onMessageReceived: "+ remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = "homeshare_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Homeshare Notification")
                .setContentText(messageBody)
                .setAutoCancel(false)
                .setSound(defaultSoundUri)
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}