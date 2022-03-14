package com.bcpr.backend.ocr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class OCRController {
	private OCRMapper mapper;
	
	@Autowired
	public void setMapper(OCRMapper mapper) {
		this.mapper = mapper;
	}
	
	//ocr api 사용
	@PostMapping("/ocr")
	 public String getOCR(	
		@RequestParam(value="file", required=false) MultipartFile file,
		HttpServletRequest request) throws IOException{
		FileSaveHelper fsh = new FileSaveHelper(request.getServletContext().getRealPath("resources"));
		String url = fsh.tempSave(file);
		System.out.println(url);
		OCRHelper oh = new OCRHelper();
		String out = oh.forFile(url);
		fsh.delete(url);
		
	    return out;
	}
	
	//media_trans 보관함에 저장
	@PostMapping("/ocr/upload")
	public int upload(
			@RequestParam("email") String email,
			@RequestParam("trans_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime trans_date,
			@RequestParam("kind") String kind,
			@RequestParam(value="input", required=false) MultipartFile input,
			@RequestParam("output") String output,
			HttpServletRequest request)throws IOException {

		FileSaveHelper fsh = new FileSaveHelper(request.getServletContext().getRealPath("resources"));
		String url = fsh.media_transSave(email, kind, trans_date, input);
		return mapper.insertMedia_TransContent(email,trans_date,kind,url,output);
	}
	
	//media_trans 보관함 아이템 불러오기 email 기준 전부
	@GetMapping("/ocr/list/{email}")
	public List<Media_Trans> getList(
			@PathVariable("email") String email){
		//log.info("test : {}",test.get(0));
		return mapper.getMedia_TransListByEmail(email);
	}
	
	@GetMapping("/ocr/download/{email}/{media_no}/{kind}")
	public void download(
			@PathVariable("email") String email,
			@PathVariable("media_no") int media_no,
			@PathVariable("kind") String kind,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Media_Trans mt = mapper.getMedia_Trans(email, media_no);
		
		String path = "";
		path = request.getServletContext().getRealPath("resources")
				+"\\media_trans\\"
				+ mt.getEmail()
				+ "\\";
		
		if(kind.equals("input")) {
			path += mt.getInput(); // 경로에 접근할 때 역슬래시('\') 사용
			
		}else {
	        path += mt.getTrans_date()+"-output.txt";
	        try{ 
	            // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
	            BufferedWriter fw = new BufferedWriter(new FileWriter(path, true));
	            // 파일안에 문자열 쓰기
	            fw.write(mt.getOutput());
	            fw.flush();
	            // 객체 닫기
	            fw.close();        
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		}
		File file = new File(path);
		byte[] fileByte = FileUtils.readFileToByteArray(file);
		
		response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(file.getName(), "UTF-8")+"\"");
	    response.setHeader("Content-Transfer-Encoding", "binary");

	    response.getOutputStream().write(fileByte);
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	    
	    if(kind.equals("output") && file.exists() ){ 
			if(file.delete()){ 
			}
		}
    }
	
	//media_trans 보관함 아이템 삭제 email, no 기준(협의필요)
	@PostMapping("/ocr/remove")
	public int remove(
			@RequestParam("email") String email,
			@RequestParam("media_no") int media_no) {
		System.out.println("test: "+media_no);
		return mapper.deleteMedia_TransContent(email,media_no);
	}
}
