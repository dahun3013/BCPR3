package com.bcpr.backend.TTS.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bcpr.backend.TTS.vo.ttsVO;

@Mapper
public interface ttsMapper {
	@Select("SELECT * from tts")
	List<ttsVO> findAll();


}
