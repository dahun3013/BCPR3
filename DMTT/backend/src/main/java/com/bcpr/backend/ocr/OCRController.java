package com.bcpr.backend.ocr;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcpr.backend.ocr.domain.Media_Trans;
import com.bcpr.backend.utill.FileSaveHelper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class OCRController {
	private OCRMapper mapper;
	
	@Autowired
	public void setMapper(OCRMapper mapper) {
		this.mapper = mapper;
	}
	
	@PostMapping("/ocr")
	 public String getOCR(	
		@RequestParam(value="file", required=false) MultipartFile file,
		HttpServletRequest request) throws IOException{
		//https://kr.object.ncloudstorage.com/ocr-ci-test/sample/1.jpg
		FileSaveHelper fsh = new FileSaveHelper(request.getServletContext().getRealPath("resources"));
		String url = fsh.tempSave(file);
		//log.info("test : {}",url);
		OCRHelper oh = new OCRHelper();
		String out = oh.forFile(url);
		fsh.delete(url);
		
		//log.info("test : {}",out);
		
	    return out;
	}
	
	@PostMapping("/ocr/upload")
	public int upload(
			@RequestParam("email") String email,
			@RequestParam("trans_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime trans_date,
			@RequestParam("kind") String kind,
			@RequestParam(value="input", required=false) MultipartFile input,
			@RequestParam("output") String output,
			HttpServletRequest request)throws IOException {

		//log.info("test: {}",request.getServletContext().getRealPath("resources"));
		FileSaveHelper fsh = new FileSaveHelper(request.getServletContext().getRealPath("resources"));
		String url = fsh.media_transSave(email, kind, trans_date, input);
		//log.info("test: {},{},{},{},{}",email,trans_date,kind,url,output);
		return mapper.insertMedia_TransContent(email,trans_date,kind,url,output);
	}
	
	@GetMapping("/ocr/download")
	public List<Media_Trans> download(
			@RequestParam("email") String email){
		//log.info("test : {}",test.get(0));
		return mapper.getMedia_Trans(email);
	}
	
	@PostMapping("/ocr/delete")
	public int delte(
			@RequestParam("email") String email,
			@RequestParam("media_no") int media_no) {
		return mapper.deleteMedia_TransContent(email,media_no);
	}
}
