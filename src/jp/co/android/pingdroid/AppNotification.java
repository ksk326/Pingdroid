package jp.co.android.pingdroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AppNotification {

	// �d������Ƃǂ��Ȃ邩
	public static final int NOTIFICATION_ID = 1;

	// TODO:icon, tickerText�𓮓I�ɕω�������
	static int icon;
	static CharSequence tickerText;
	static String pendingIntentStatus;

	private static Notification createNotification(Context context) {
		// TODO�F�d��̐������������тɃA�C�R���ؑց@�l���L���\��
		// TODO�F�v�Z���O��SQLite�Ɂ@RoundRobinDB�@�Â��̂��珟��ɏ����@RRD for J

		icon = R.drawable.ic_stat_20110412_170759_hdpi;
		tickerText = "�T�[�o��ping�J�n";

		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);

		PendingIntent pi = PendingIntent.getActivity(context, 0, // requestCode
				new Intent(context, MainActivity.class), 0 // Default
				// flags
				);

		notification.setLatestEventInfo(context, context
				.getString(R.string.app_name), context
				.getString(R.string.start_pinging), pi);

		// �t���O�̎�ނƈӖ�
		notification.flags = notification.flags | Notification.FLAG_NO_CLEAR
				| Notification.FLAG_ONGOING_EVENT;
		notification.number = 0;
		return notification;
	}

	// �ʒm
	public static void putNotice(Context context) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = createNotification(context);
		nm.notify(AppNotification.NOTIFICATION_ID, notification);
	}

	// �폜
	public static void removeNotice(Context context) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(NOTIFICATION_ID);
	}

	// �ʒm�̍X�V����
	private static Notification updateNotification(Context context,
			int operationFlag) {
		// TODO:icon, tickerText�𓮓I�ɕω�������
		CharSequence tickerText = null;

		// �󂯎�������ʎq�̒l�ɉ�����icon�AtickerText��ύX����
		switch (operationFlag) {
		case 1:
			tickerText = "�T�[�o���牞������";
			pendingIntentStatus = context.getString(R.string.server_alived);
			icon = R.drawable.ic_stat_20110412_170606_hdpi;
			break;
		case 2:
			tickerText = "�T�[�o���牞���Ȃ�";
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

		// �t���O�̎�ނƈӖ�
		notification.flags = notification.flags | Notification.FLAG_NO_CLEAR
				| Notification.FLAG_ONGOING_EVENT;
		notification.number = 0;
		return notification;
	}

	// �X�V
	public static void updateNotice(Context context, int operationFlag) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = updateNotification(context, operationFlag);
		nm.notify(AppNotification.NOTIFICATION_ID, notification);
	}
}