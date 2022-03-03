package com.bcpr.backend.utill;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileSaveHelper {
	String saveDir = "";
	String result = "";
	
	//resources 상대경로 설정
	//HttpServletRequest request 매개변수 필요
	//ex) new FileSaveHelper(request.getServletContext().getRealPath("resources"));
	public FileSaveHelper(String saveDir) {
		this.saveDir = saveDir;
	}
	
	//media_trans 상대 경로 설정
	//ex) ..\DMTT\backend\src\main\webapp\resources\media_trans\이메일\타입-날짜-파일명
	public String media_transSave(String email, String kind, LocalDateTime date, MultipartFile file) throws IOException {
		String basePath = File.separator+"media_trans";
		String destPath = File.separator+email+File.separator+kind+"-"+formatDate(date)+"-"+file.getOriginalFilename();
		result = kind+"-"+formatDate(date)+"-"+file.getOriginalFilename();
		save(saveDir+basePath+destPath,file);
		return result;
	}
	
	//ocr api를 위한 임시파일 생성
	public String tempSave(MultipartFile file) throws IOException{
		String basePath = File.separator+"temp";
		String destPath = File.separator+file.getOriginalFilename();
		save(saveDir+basePath+destPath,file);
		return saveDir+basePath+destPath;
	}
	
	//ocr api를 위한 임시파일 삭제
	public void delete(String path) {
		File file = new File(path); 
		if( file.exists() ){ 
			if(file.delete()){ 
				log.info("임시파일삭제 성공"); 
			}else{
				log.info("임시파일삭제 실패"); 
			} 
		}else{ 
			log.info("임시파일이 존재하지 않습니다."); 
		}
	}
	
	//경로를 받아서 파일생성
	private void save(String path,MultipartFile file) throws IOException {
		File newFileName = new File(path);
		if(!newFileName.exists()) {
			if(newFileName.getParentFile().mkdirs()) {
				newFileName.createNewFile();
			}
		}
		
		try {
			file.transferTo(newFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//업로드시간을 첨부이미지파일 식별을 위한 문자열로 변환하는 함수.
	private String formatDate(LocalDateTime sDate) {
		
		String year = ""+sDate.getYear();
		String month = (sDate.getMonthValue() < 10) ? "0"+sDate.getMonthValue() : ""+sDate.getMonthValue();
		String day = (sDate.getDayOfMonth() < 10) ? "0"+sDate.getDayOfMonth() : ""+sDate.getDayOfMonth();
		String hour = (sDate.getHour() < 10) ? "0"+sDate.getHour() : ""+sDate.getHour();
		String minute = (sDate.getMinute() < 10) ? "0"+sDate.getMinute() : ""+sDate.getMinute();
		String second = (sDate.getSecond() < 10) ? "0"+sDate.getSecond() : ""+sDate.getSecond();
		
		return year+month+day+hour+minute+second;
		
	}
}