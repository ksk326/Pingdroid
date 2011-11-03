package jp.co.android.pingdroid;

public class PingHistoryData {
	// int or String
	String id;
	String	ipAddress;
	String remarks;

	public PingHistoryData(String id, String ipAddress, String remarks) {
		// “n‚³‚ê‚½’l‚ğ•Ï”‚ÉŠi”[
		this.id = id;
		this.ipAddress = ipAddress;
		this.remarks = remarks;
	}
}
