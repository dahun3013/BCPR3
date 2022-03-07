package com.bcpr.backend.papago;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcpr.backend.security.api.UserResource;
import com.bcpr.backend.security.domain.User;
import com.bcpr.backend.security.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class PapagoController {
	
	@PostMapping("/user/papago/array")
	 public String papaForArray(	
			 @RequestParam(value = "text[]") List<String> text,
			 @RequestParam(value = "from_language") String from_language,
			 @RequestParam(value = "to_language") String to_language,
				HttpServletRequest request) throws IOException{
			
		PapagoRepo papagorepo = new PapagoRepo();
		String t = papagorepo.translationForArray(text, from_language, to_language);
		papagorepo.setText(t);
		log.info("test : {}",papagorepo);
	    return papagorepo.getText();
	}
	
	@PostMapping("/user/papago/json")
	 public String papaForJson(	
			 @RequestParam(value = "text") String text,
			 @RequestParam(value = "from_language") String from_language,
			 @RequestParam(value = "to_language") String to_language,
				HttpServletRequest request) throws IOException{
			
		PapagoRepo papagorepo = new PapagoRepo();
		String t = papagorepo.translation(text, from_language, to_language);
		papagorepo.setText(t);
		log.info("test : {}",papagorepo);
		  return papagorepo.getText();
	}
	/*
	@PostMapping("/user/papago")
	 public String papa(	
			 @RequestParam(value="text") String text,
				HttpServletRequest request) throws IOException{
			
			PapagoRepo papagorepo = new PapagoRepo();
			//String t = papagorepo.translation(text);
			//papagorepo.setText(t);
			log.info("testing!!!");
			log.info("test : {}",text);
			//log.info("test : {}",papagorepo);
			
		   
		    return "test";
	
	}
	*/
}
