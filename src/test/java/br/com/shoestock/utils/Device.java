package br.com.shoestock.utils;

public class Device {

	private String _deviceName;
	private String _version;
	private String _udid;
	private String _platform;
	private String _environment;

	public Device(String pDeviceName, String pVersion, String pUdid, String pPlatform, String pEnvironment) {
		this._deviceName = pDeviceName;
		this._version = pVersion;
		this._udid = pUdid;
		this._platform = pPlatform;
		this._environment = pEnvironment;
	}

	public String getDeviceName() {
		return _deviceName;
	}

	public void setDeviceName(String pDeviceName) {
		this._deviceName = pDeviceName;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String pVersion) {
		this._version = pVersion;
	}

	public String getUdid() {
		return _udid;
	}

	public void setUdid(String pUdid) {
		this._udid = pUdid;
	}

	public String getPlatform() {
		return _platform;
	}

	public void setPlatform(String pPlatform) {
		this._platform = pPlatform;
	}

	public String getEnvironment() {
		return _environment;
	}

	public void setEnvironment(String pEnvironment) {
		this._environment = pEnvironment;
	}

	public String[] toArray() {
		return new String[] { _deviceName, _version, _udid, _platform, _environment };
	}

}
