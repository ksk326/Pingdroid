package jp.co.android.pingdroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AppNotification {

	// 重複するとどうなるか
	public static final int NOTIFICATION_ID = 1;

	// TODO:icon, tickerTextを動的に変化させる
	static int icon;
	static CharSequence tickerText;
	static String pendingIntentStatus;

	private static Notification createNotification(Context context) {
		// TODO：電卓の数字を押すたびにアイコン切替　四則記号表示
		// TODO：計算ログをSQLiteに　RoundRobinDB　古いのから勝手に消す　RRD for J

		icon = R.drawable.ic_stat_20110412_170759_hdpi;
		tickerText = "サーバへping開始";

		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);

		PendingIntent pi = PendingIntent.getActivity(context, 0, // requestCode
				new Intent(context, MainActivity.class), 0 // Default
				// flags
				);

		notification.setLatestEventInfo(context, context
				.getString(R.string.app_name), context
				.getString(R.string.start_pinging), pi);

		// フラグの種類と意味
		notification.flags = notification.flags | Notification.FLAG_NO_CLEAR
				| Notification.FLAG_ONGOING_EVENT;
		notification.number = 0;
		return notification;
	}

	// 通知
	public static void putNotice(Context context) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = createNotification(context);
		nm.notify(AppNotification.NOTIFICATION_ID, notification);
	}

	// 削除
	public static void removeNotice(Context context) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(NOTIFICATION_ID);
	}

	// 通知の更新処理
	private static Notification updateNotification(Context context,
			int operationFlag) {
		// TODO:icon, tickerTextを動的に変化させる
		CharSequence tickerText = null;

		// 受け取った識別子の値に応じてicon、tickerTextを変更する
		switch (operationFlag) {
		case 1:
			tickerText = "サーバから応答あり";
			pendingIntentStatus = context.getString(R.string.server_alived);
			icon = R.drawable.ic_stat_20110412_170606_hdpi;
			break;
		case 2:
			tickerText = "サーバから応答なし";
			pendingIntentStatus = context.getString(R.string.server_dead);
			icon = R.drawable.ic_stat_20110412_170752_hdpi;
			break;
		}

		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);

		PendingIntent pi = PendingIntent.getActivity(context, 0, // requestCode
				new Intent(context, MainActivity.class), 0 // Default
				// flags
				);

		notification.setLatestEventInfo(context, context
				.getString(R.string.app_name), pendingIntentStatus, pi);

		// フラグの種類と意味
		notification.flags = notification.flags | Notification.FLAG_NO_CLEAR
				| Notification.FLAG_ONGOING_EVENT;
		notification.number = 0;
		return notification;
	}

	// 更新
	public static void updateNotice(Context context, int operationFlag) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = updateNotification(context, operationFlag);
		nm.notify(AppNotification.NOTIFICATION_ID, notification);
	}
}