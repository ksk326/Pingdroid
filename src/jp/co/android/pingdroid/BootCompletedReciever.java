package jp.co.android.pingdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		String action = intent.getAction();
		
		if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
			// プリファレンスの設定を確認する
			boolean checked = true;
			if (checked){
				AppNotification.putNotice(context);
			}
		}
	}
}
