package jp.co.android.pingdroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class PingHistoryDBHelper extends SQLiteOpenHelper {

	 public PingHistoryDBHelper(Context context, String name,
	 CursorFactory factory, int version) {
	 super(context, name, factory, version);
	 // TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	 }

	// public PingHistoryDBHelper(Context context) {
	// super(context, null, null, 1);
	// // TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	// }

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// �e�[�u��pingHistory_table�̒�`
		String sql = "create table pingHistory_table"
				+ "(pingHistory_id integer primary key autoincrement,"
				+ " pingHistory_ipAddress text,"
				+ " pingHistory_remarks text)";
		// �e�[�u���̍쐬
		arg0.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

}
