package com.bcpr.backend.TTS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bcpr.backend.TTS.mapper.ttsMapper;

@Service
public class ttsService {
	private ttsMapper ttsMapper;

	public ttsService(ttsMapper ttsMapper) {
		this.ttsMapper = ttsMapper;
	}

}
		


