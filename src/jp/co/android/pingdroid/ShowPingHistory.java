package jp.co.android.pingdroid;

import android.database.sqlite.SQLiteDatabase;
import android.text.SpannableStringBuilder;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ShowPingHistory {
	public EditText destinationAddressWindow;
	public EditText remarksWindow;
	public SpannableStringBuilder destinationAddress;
	public SpannableStringBuilder remarks;
	public Button startPinging;
	public ListView listViewPingHistory;
	public ArrayAdapter<String> adapter;
	public SQLiteDatabase pingHistoryDB;

	public ShowPingHistory() {
	}
}