package jp.co.android.pingdroid;

import jp.co.android.pingdroid.R;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	public EditText destinationAddressWindow;
	public EditText remarksWindow;
	public SpannableStringBuilder destinationAddress;
	public SpannableStringBuilder remarks;
	public Button startPinging;
	public ListView listViewPingHistory;
	public PingHistoryAdapter adapter;
	public SQLiteDatabase pingHistoryDB;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// タイトルの非表示
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		this
				.getWindow()
				.setSoftInputMode(
						android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.main);
		
		// ステータスバーのアイコンを削除 ※プリファレンスの設定値をチェック
		AppNotification.putNotice(this);


		// データベースヘルパーの生成
		PingHistoryDBHelper pingHistoryDBHelper = new PingHistoryDBHelper(this,
				"pingHistory_db", null, 1);

		// データベースの生成
		pingHistoryDB = pingHistoryDBHelper.getWritableDatabase();

		// リストビューを変数listViewPingHistoryに代入
		listViewPingHistory = (ListView) findViewById(R.id.PingHistoryList);

		// アダプターの作成
		adapter = new PingHistoryAdapter(this, R.layout.pinghistorylist);

		// ping履歴の表示
		showPingHistory();

		destinationAddressWindow = (EditText) findViewById(R.id.destinationAddressWindow);
		destinationAddress = (SpannableStringBuilder) destinationAddressWindow
				.getText();

		remarksWindow = (EditText) findViewById(R.id.remarksWindow);
		remarks = (SpannableStringBuilder) remarksWindow.getText();

		startPinging = (Button) findViewById(R.id.startPinging);
		startPinging.setOnClickListener(new StartPinging(
				getApplicationContext(), this));
	}

	private void showPingHistory() {
		// アダプターのクリア
		adapter.clear();

		// カーソルの宣言
		Cursor cursor = null;

		// SQL文の作成
		String sql = "select pingHistory_id, pingHistory_ipAddress, pingHistory_remarks"
				+ " from pingHistory_table" + " order by pingHistory_id";

		// SQL文を実行し、結果をカーソルに代入
		cursor = pingHistoryDB.rawQuery(sql, null);

		// カーソルを先頭に移動
		cursor.moveToFirst();

		// pingHistory_dbの長さ取得
		CharSequence[] list = new CharSequence[cursor.getCount()];

		// １件ずつデータを取り出す
		for (int i = 0; i < list.length; i++) {
			// １件分のデータを取り出す
			PingHistoryData pingHistoryData = new PingHistoryData(cursor
					.getString(0), cursor.getString(1), cursor.getString(2));

			// 問い合わせ結果をアダプターに追加
			adapter.add(pingHistoryData);

			// TODO
			// カーソルを先に進める
			cursor.moveToNext();
		}
		// アダプターをリストビューにセット
		listViewPingHistory.setAdapter(adapter);
		// TODO ??
		cursor.close();
	}
}