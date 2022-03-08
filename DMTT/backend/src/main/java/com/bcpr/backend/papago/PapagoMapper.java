package com.bcpr.backend.papago;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bcpr.backend.ocr.domain.Media_Trans;

@Mapper
public interface PapagoMapper {
	@Select("select count(*) from translation")
	int getCountTranslation();

	@Select("select * from translation")
	List<Translation> getTranslation();
	
	@Select("select * from translation where email = #{email}")
	List<Translation> getTranslation(@Param("email") String email);
	
	
	@Insert("insert into translation(email,trans_date,kind,input,output) "
			+"values(#{email},#{trans_date},#{kind},#{input,jdbcType=VARCHAR},#{output})")
	int insertTranslation_TranslaterContent(
			@Param("email") String email,
			@Param("trans_date") LocalDateTime trans_date,
			@Param("kind") String kind,
			@Param("input") String input,
			@Param("output") String output);
		
	
		
		@Delete("delete from translation where email = #{email} and translation_no = #{translation_no}")
		int deleteTranslation_TranslaterContent(
				@Param("email") String email,
		@Param("translation_no ") int translation_no );



}
