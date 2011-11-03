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
		// �^�C�g���̔�\��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		this
				.getWindow()
				.setSoftInputMode(
						android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setContentView(R.layout.main);
		
		// �X�e�[�^�X�o�[�̃A�C�R�����폜 ���v���t�@�����X�̐ݒ�l���`�F�b�N
		AppNotification.putNotice(this);


		// �f�[�^�x�[�X�w���p�[�̐���
		PingHistoryDBHelper pingHistoryDBHelper = new PingHistoryDBHelper(this,
				"pingHistory_db", null, 1);

		// �f�[�^�x�[�X�̐���
		pingHistoryDB = pingHistoryDBHelper.getWritableDatabase();

		// ���X�g�r���[��ϐ�listViewPingHistory�ɑ��
		listViewPingHistory = (ListView) findViewById(R.id.PingHistoryList);

		// �A�_�v�^�[�̍쐬
		adapter = new PingHistoryAdapter(this, R.layout.pinghistorylist);

		// ping�����̕\��
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
		// �A�_�v�^�[�̃N���A
		adapter.clear();

		// �J�[�\���̐錾
		Cursor cursor = null;

		// SQL���̍쐬
		String sql = "select pingHistory_id, pingHistory_ipAddress, pingHistory_remarks"
				+ " from pingHistory_table" + " order by pingHistory_id";

		// SQL�������s���A���ʂ��J�[�\���ɑ��
		cursor = pingHistoryDB.rawQuery(sql, null);

		// �J�[�\����擪�Ɉړ�
		cursor.moveToFirst();

		// pingHistory_db�̒����擾
		CharSequence[] list = new CharSequence[cursor.getCount()];

		// �P�����f�[�^�����o��
		for (int i = 0; i < list.length; i++) {
			// �P�����̃f�[�^�����o��
			PingHistoryData pingHistoryData = new PingHistoryData(cursor
					.getString(0), cursor.getString(1), cursor.getString(2));

			// �₢���킹���ʂ��A�_�v�^�[�ɒǉ�
			adapter.add(pingHistoryData);

			// TODO
			// �J�[�\�����ɐi�߂�
			cursor.moveToNext();
		}
		// �A�_�v�^�[�����X�g�r���[�ɃZ�b�g
		listViewPingHistory.setAdapter(adapter);
		// TODO ??
		cursor.close();
	}
}