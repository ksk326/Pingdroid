package jp.co.android.pingdroid;

public class PingHistoryData {
	// int or String
	String id;
	String	ipAddress;
	String remarks;

	public PingHistoryData(String id, String ipAddress, String remarks) {
		// �n���ꂽ�l��ϐ��Ɋi�[
		this.id = id;
		this.ipAddress = ipAddress;
		this.remarks = remarks;
	}
}
