package jp.co.android.pingdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PingHistoryAdapter extends ArrayAdapter<PingHistoryData> {
	// ���C�A�E�g���쐬���邽�߂�inflater��錾
	LayoutInflater inflater;
	// ���C�A�E�g�t�@�C����ID��錾
	int layoutId;

	public PingHistoryAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		// �R���e�L�X�g����inflater���擾
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// ���C�A�E�g�t�@�C����ID���擾
		layoutId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		// return super.getView(position, convertView, parent);
		// �ϐ�view��convertView����
		View view = convertView;
		// �ϐ�view(convertview)������Ȃ�΃��C�A�E�g���쐬
		if (view == null) {
			view = inflater.inflate(layoutId, null);
		}
		// ���C�A�E�g���Number��ϐ�number�ɑ��
		TextView number = (TextView) view.findViewById(R.id.Number);
		// ���C�A�E�g���IPAddress��ϐ�ipAddress�ɑ��
		TextView ipAddress = (TextView) view.findViewById(R.id.IPAddress);
		// ���C�A�E�g���Remarks��ϐ�remarks�ɑ��
		TextView remarks = (TextView) view.findViewById(R.id.Remarks);

		// �I������Ă���PingHistoryData�^�̃f�[�^�����o��
		PingHistoryData pingHistoryData = getItem(position);

		// number�ɔԍ����Z�b�g
		number.setText(pingHistoryData.id);
		// ipAddress��IP�A�h���X���Z�b�g
		ipAddress.setText(pingHistoryData.ipAddress);
		// remarks�ɔ��l���Z�b�g
		remarks.setText(pingHistoryData.remarks);

		// �߂�l�ɕϐ�view��n��
		return view;
	}
}
