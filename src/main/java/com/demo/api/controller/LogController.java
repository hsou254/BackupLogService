package com.demo.api.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.api.FileUploadResponse;
import com.demo.api.FileUploadUtil;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/*
 * There are three log file settings in the application.properties file:
 * 1. Max File Size
 * 2. Max Request Size
 * 3. Enable Multipart 
 **/

@RestController
public class LogController {
	@Autowired
	private HttpServletRequest request;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<FileUploadResponse> uploadFile (
			@RequestParam("file") MultipartFile multipartFile) throws IOException {
		
		String strReceivedTimestamp = getCurentTimestampInText();
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		long size = multipartFile.getSize();		
		long startTime = System.nanoTime(); // This is the most accurate because it is not based upon system clock.
		String clientIpAddress = request.getRemoteAddr();
		String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;		
		
		String strElapsedTimeInMinutesAndSeconds = getElapsedTimeInMinutesSeconds(timeElapsed);
		
		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(fileName);
		response.setSize(size);
		response.setDownloadUri("/downloadFile/" + filecode);
		response.setElapsedTime(strElapsedTimeInMinutesAndSeconds);
		response.setClientIpAddress(clientIpAddress);
		response.setReceivedTimestamp(strReceivedTimestamp);
		System.out.println("Elaped Time: " + strElapsedTimeInMinutesAndSeconds);
		
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	private String getElapsedTimeInMinutesSeconds(long elapsedTimeInNanoseconds) {
		long milliseconds = elapsedTimeInNanoseconds / 1000000;
		long minutes = (milliseconds / 1000) / 60;
		long seconds = (milliseconds / 1000) % 60;
		milliseconds = milliseconds % 1000;
		return minutes + " minutes, " + seconds + " seconds and " + milliseconds + " milliseconds.";
	}
	
	private String getCurentTimestampInText() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy hh:mm:ss aa");
		String strReceivedTimestamp = dateFormat.format(timestamp);
		
		return strReceivedTimestamp;
	}	
}
