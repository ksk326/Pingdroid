package jp.co.android.pingdroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class PingHistoryDBHelper extends SQLiteOpenHelper {

	 public PingHistoryDBHelper(Context context, String name,
	 CursorFactory factory, int version) {
	 super(context, name, factory, version);
	 // TODO 自動生成されたコンストラクター・スタブ
	 }

	// public PingHistoryDBHelper(Context context) {
	// super(context, null, null, 1);
	// // TODO 自動生成されたコンストラクター・スタブ
	// }

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// テーブルpingHistory_tableの定義
		String sql = "create table pingHistory_table"
				+ "(pingHistory_id integer primary key autoincrement,"
				+ " pingHistory_ipAddress text,"
				+ " pingHistory_remarks text)";
		// テーブルの作成
		arg0.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
