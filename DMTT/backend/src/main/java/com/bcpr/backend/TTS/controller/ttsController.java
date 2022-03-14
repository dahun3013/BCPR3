package com.bcpr.backend.TTS.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcpr.backend.TTS.domain.Voice_Trans;
import com.bcpr.backend.TTS.helper.ttsHelper;
import com.bcpr.backend.TTS.mapper.ttsMapper;
import com.bcpr.backend.TTS.util.FileSaveHelper;

@RestController
@RequestMapping("/api")
public class ttsController {
	private ttsMapper mapper;
	
	@Autowired
	public void setMapper(ttsMapper mapper) {
		this.mapper = mapper;
	}
	
	//tts api 사용
	@PostMapping("/tts")
    public  String getTTShelper(
    		@RequestBody HashMap<String, Object> requestJsonHashMap,
    		HttpServletRequest request)  throws IOException {
	HashMap<String, Object> rtnMap = new HashMap<String, Object>();
	rtnMap.put("text", requestJsonHashMap.get("data1"));
	rtnMap.put("voice", requestJsonHashMap.get("data2"));
	rtnMap.put("speed", requestJsonHashMap.get("data3")); 
	rtnMap.put("volume", requestJsonHashMap.get("data4")); 
	String tts = (String) rtnMap.get("text");
	String voice = (String) rtnMap.get("voice");
	String speed = (String) rtnMap.get("speed");
	String volume = (String) rtnMap.get("volume");
	System.out.println(rtnMap);
	ttsHelper TH = new ttsHelper();
	String out = (String) TH.getTTShelper(tts,voice,speed,volume);
	System.out.println("out : " + out );
	
	 return out;
	}
	
//	//voice_trans 보관함에 저장
//	@PostMapping("/tts/upload")
//	public int upload(
//			@RequestParam("email") String email,
//			@RequestParam("trans_date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime trans_date,
//			@RequestParam("kind") String kind,
//			@RequestParam(value="input", required=false) String input,
//			@RequestParam("output") String output,
//			HttpServletRequest request)throws IOException {
//
//		FileSaveHelper fsh = new FileSaveHelper(request.getServletContext().getRealPath("resources"));
//		String url = fsh.voice_transSave(email, kind, trans_date, input);
//		return mapper.insertVoice_TransContent(email,trans_date,kind,url,output);
//	}
//	
	//voice_trans 보관함 아이템 불러오기 email 기준 전부
	@GetMapping("/tts/list/{email}")
	public List<Voice_Trans> getList(
			@PathVariable("email") String email){
		//log.info("test : {}",test.get(0));
		return mapper.getVoice_TransListByEmail(email);
	}
	
	@GetMapping("/tts/download/{email}/{voice_no}/{kind}")
	public void download(
			@PathVariable("email") String email,
			@PathVariable("voice_no") int voice_no,
			@PathVariable("kind") String kind,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Voice_Trans mt = mapper.getVoice_Trans(email, voice_no);
		
		String path = "";
		path = request.getServletContext().getRealPath("resources")
				+"\\voice_trans\\"
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
	
	//voice_trans 보관함 아이템 삭제 email, no 기준(협의필요)
	@PostMapping("/tts/remove")
	public int remove(
			@RequestParam("email") String email,
			@RequestParam("voice_no") int voice_no) {
		System.out.println("test: "+voice_no);
		return mapper.deleteVoice_TransContent(email,voice_no);
	}
}
