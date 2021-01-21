//package com.goodeggs.android.firbasesetup;
//
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//import androidx.core.app.NotificationCompat;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//import com.goodeggs.android.ApplicationController;
//import com.goodeggs.android.utils.AppPreferences;
//import com.goodeggs.android.utils.Constants;
//
//import org.json.JSONObject;
//
//import java.util.Map;
//
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    private static final String TAG = "MyFirebaseMsgService";
//    String title = "", serviceType = "";
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//        // Check if message contains a notification payload.
//        Map<String, String> params = remoteMessage.getData();
//        if (params.size() > 0) {
//            JSONObject finalJsonObject = new JSONObject(params);
//
//            if (finalJsonObject.length() > 0) {
//                serviceType = finalJsonObject.optString(Constants.NOTIFICATION_SERVICE_TYPE, "");
//                title = finalJsonObject.optString("title", "");
//
//                if (AppPreferences.INSTANCE.isLogin()) {
//                    Log.e("notificationObj", finalJsonObject.toString());
////                    sendNormalNotification(ApplicationController.Companion.getInstance().getApplicationContext(), finalJsonObject);
//
//                }
//
//            }
//        }
//
//    }
//
//
//    public void sendNormalNotification(Context context, JSONObject jsonObject) {
//        try {
//            String title = "CelebKonect";
//            String messageBody = jsonObject.optString("body", "");
//            String serviceType = jsonObject.optString(Constants.NOTIFICATION_SERVICE_TYPE, "");
////            if (serviceType != null && serviceType.equalsIgnoreCase(Constants.NOTIFICATION_APP_UPDATE)) {
//                title = "CelebKonect Update";
////            }
//            //
//            Intent intent = new Intent(context, ServiceChatNotificationClick.class);
//            intent.putExtra(Constants.NOTIFICATION_SERVICE_TYPE, serviceType);
////            intent.putExtra(Constants.NOTIFICATION_OBJECT, jsonObject.toString());
//            intent.putExtra("isSelf", true);
//            //
//            int uniqueID = 0;
//            PendingIntent pendingIntentMessage = PendingIntent.getService(context, uniqueID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            NotificationCompat.Builder messageBuilder = createNotificationBuilder(context, title, messageBody, pendingIntentMessage);
//            showNotification(context, messageBuilder.build(), uniqueID);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
