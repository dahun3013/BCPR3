package com.bcpr.backend.ocr;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class OCRController {
	@PostMapping("/ocr/json")
	 public String forURL(	
		@RequestParam("url") String url,
			HttpServletRequest request) throws IOException{
		//https://kr.object.ncloudstorage.com/ocr-ci-test/sample/1.jpg
		OCRHelper oh = new OCRHelper();
		String out = oh.forJSON(url);
		
		log.info("test : {}",out);
		
	    return out;
	}
}
