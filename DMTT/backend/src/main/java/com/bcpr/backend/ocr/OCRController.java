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
	 public String tojson(	
		@RequestParam("text") String text,
			HttpServletRequest request) throws IOException{
		
		OCRHelper oh = new OCRHelper();
		String out = oh.forJSON();
		
		log.info("test : {}",out);
		
	    return out;
	}
}
