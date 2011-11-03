package jp.co.android.pingdroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PingHistoryAdapter extends ArrayAdapter<PingHistoryData> {
	// レイアウトを作成するためのinflaterを宣言
	LayoutInflater inflater;
	// レイアウトファイルのIDを宣言
	int layoutId;

	public PingHistoryAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO 自動生成されたコンストラクター・スタブ
		// コンテキストからinflaterを取得
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// レイアウトファイルのIDを取得
		layoutId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自動生成されたメソッド・スタブ
		// return super.getView(position, convertView, parent);
		// 変数viewにconvertViewを代入
		View view = convertView;
		// 変数view(convertview)がからならばレイアウトを作成
		if (view == null) {
			view = inflater.inflate(layoutId, null);
		}
		// レイアウト上のNumberを変数numberに代入
		TextView number = (TextView) view.findViewById(R.id.Number);
		// レイアウト上のIPAddressを変数ipAddressに代入
		TextView ipAddress = (TextView) view.findViewById(R.id.IPAddress);
		// レイアウト上のRemarksを変数remarksに代入
		TextView remarks = (TextView) view.findViewById(R.id.Remarks);

		// 選択されているPingHistoryData型のデータを取り出す
		PingHistoryData pingHistoryData = getItem(position);

		// numberに番号をセット
		number.setText(pingHistoryData.id);
		// ipAddressにIPアドレスをセット
		ipAddress.setText(pingHistoryData.ipAddress);
		// remarksに備考をセット
		remarks.setText(pingHistoryData.remarks);

		// 戻り値に変数viewを渡す
		return view;
	}
}
