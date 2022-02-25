package com.bcpr.backend.papago;

import java.io.IOException;
import java.util.HashMap;

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

	@PostMapping("/user/papago")
	 public PapagoRepo papa(	
			 @RequestParam("text") String text,
				HttpServletRequest request) throws IOException{
			
			PapagoRepo papagorepo = new PapagoRepo();
			String t = papagorepo.translation(text);
			papagorepo.setText(t);
			

			log.info("test : {}",papagorepo);
			
		   
		    return papagorepo;
	
	}
	
}
