package jp.co.android.pingdroid;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class StartPinging implements OnClickListener {

	private final MainActivity mainActivity;
	Context context;

	public StartPinging(Context context, MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		// IP��z�X�g���Ȃǂ��w��
		Inet4Address inetAddress = null;
		final int TIMEOUT = 2000;
		String ip = mainActivity.destinationAddress.toString();
		String remarks = mainActivity.remarks.toString();

		// SQLite�֊Ǘ��pID�A�ڑ���ip�A���l�̏�������
		// SQL���̍쐬
		String sql = "insert into pingHistory_table"
				+ " (pingHistory_ipAddress,pingHistory_remarks)" + " values('"
				+ ip + "','" + remarks + "')";

		// �f�[�^�x�[�X�փf�[�^��}��
		mainActivity.pingHistoryDB.execSQL(sql);

		try {
			inetAddress = (Inet4Address) Inet4Address.getByName(ip);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		Boolean res = false;
		try {
			res = inetAddress.isReachable(TIMEOUT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (res = true) {
			// Toast.makeText(context, "��������", Toast.LENGTH_LONG).show();
			AppNotification.updateNotice(context, 1);
		} else if (res = false) {
			// Toast.makeText(context, "�����Ȃ�", Toast.LENGTH_LONG).show();
			AppNotification.updateNotice(context, 2);
		}
	}
}
