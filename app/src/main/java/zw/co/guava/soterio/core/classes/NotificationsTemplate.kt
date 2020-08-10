package zw.co.guava.soterio.core.classes

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main.MainActivity


class NotificationsTemplates {

    companion object {
        const val PENDING_WIZARD_REQ_CODE = 10
        const val PENDING_ACTIVITY = 5
        fun getRunningNotification(context: Context, channel: String): Notification {

            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val activityPendingIntent = PendingIntent.getActivity(
                context,
                PENDING_ACTIVITY,
                intent, 0
            )

            val builder = NotificationCompat.Builder(context, channel)
                .setContentTitle(context.getString(R.string.service_ok_title))
                .setContentText(context.getString(R.string.service_ok_body))
                .setTicker(context.getText(R.string.service_ok_body))
                .setStyle(NotificationCompat.BigTextStyle().bigText(context.getText(R.string.service_ok_body)))
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSmallIcon(R.drawable.notification)
                .setContentIntent(activityPendingIntent)
                .setWhen(System.currentTimeMillis())
                .setSound(null)
                .setVibrate(null)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))

            return builder.build()
        }

        fun lackingThingsNotification(context: Context, channel: String): Notification {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("page", 3)

            val activityPendingIntent = PendingIntent.getActivity(
                context, PENDING_WIZARD_REQ_CODE,
                intent, 0
            )

            val builder = NotificationCompat.Builder(context, channel)
                .setContentTitle(context.getString(R.string.service_not_ok))
                .setContentText(context.getString(R.string.service_not_ok_body))
                .setStyle(NotificationCompat.BigTextStyle().bigText(context.getText(R.string.service_not_ok_body)))
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setSmallIcon(R.drawable.notification)
                .setTicker(context.getText(R.string.service_not_ok_body))
                .addAction(
                    R.drawable.ic_notification_settings,
                    context.getString(R.string.service_not_ok_action),
                    activityPendingIntent
                )
                .setContentIntent(activityPendingIntent)
                .setWhen(System.currentTimeMillis())
                .setSound(null)
                .setVibrate(null)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))

            return builder.build()
        }
    }

}
