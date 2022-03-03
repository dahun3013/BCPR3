package com.bcpr.backend.ocr.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.bcpr.backend.security.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//테이블 명시
@Entity
//getter, setter 자동 생성
@Data
//생성자 자동생성
@NoArgsConstructor @AllArgsConstructor
//media_trans 테이블 자동 생성을 위한 Class
//테이블이 없다면 application.properties = create 로 실행 필요
public class Media_Trans {
	//pk 지정 및 auto increment 설정
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long media_no;
    private String email;
    private Date trans_date;
    private String kind;
    private String input;
    //TEXT 타입 지정
    @Lob
    private String output;
}

