/**
 * 
 */
package com.demo.api;

/**
 * 
 */
public class FileUploadResponse {
	private String fileName;
	private String downloadUri;
	private String elapsedTime;
	private String receivedTimestamp;
	private String clientIpAddress;
	private long size;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the downloadUri
	 */
	public String getDownloadUri() {
		return downloadUri;
	}
	/**
	 * @param downloadUri the downloadUri to set
	 */
	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getReceivedTimestamp() {
		return receivedTimestamp;
	}
	public void setReceivedTimestamp(String receivedTimestamp) {
		this.receivedTimestamp = receivedTimestamp;
	}
	public String getClientIpAddress() {
		return clientIpAddress;
	}
	public void setClientIpAddress(String clientIpAddress) {
		this.clientIpAddress = clientIpAddress;
	}	
	
}
