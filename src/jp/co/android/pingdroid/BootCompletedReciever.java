package jp.co.android.pingdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		String action = intent.getAction();
		
		if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
			// �v���t�@�����X�̐ݒ���m�F����
			boolean checked = true;
			if (checked){
				AppNotification.putNotice(context);
			}
		}
	}
}
