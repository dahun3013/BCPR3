package com.bcpr.backend.papago;



import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PapagoSave {
   String papagosaveDir = "";
   String papagosaveresult ="";
   
   public PapagoSave(String papagosaveDir) {
	   this.papagosaveDir=papagosaveDir;
   }
   public String document_transSave(String email, String kind, LocalDateTime date, MultipartFile file) throws IOException {
       String basePath = File.separator+"document_trans";
       String destPath = File.separator+email+File.separator+kind+"-"+formatDate(date)+"-"+file.getOriginalFilename();
       papagosaveresult = kind+"-"+formatDate(date)+"-"+file.getOriginalFilename();
       save(papagosaveDir+basePath+destPath,file);
       return papagosaveresult;
   }

   //papago api를 위한 임시파일 생성
   public String tempSave(MultipartFile file) throws IOException {
       String basePath = File.separator+"temp";
       String destPath = File.separator+file.getOriginalFilename();
       save(papagosaveDir+basePath+destPath,file);
       return papagosaveDir+basePath+destPath;
   }

   //papago api를 위한 임시파일 삭제
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
