package com.bcpr.backend.TTS.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcpr.backend.TTS.service.ttsService;
import com.bcpr.backend.TTS.vo.ttsVO;

@RestController
@RequestMapping(value = "api/tts/*")
public class ttsController {
	@Autowired
	private ttsService ttsService;
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
		
	@GetMapping("/server")
	public String getParameter(@RequestParam(value="tts", required=false) String TTS ) throws IOException{ 
		return "index"; 
	}

}
