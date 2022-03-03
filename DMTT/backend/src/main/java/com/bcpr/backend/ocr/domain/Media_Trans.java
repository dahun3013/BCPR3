package com.bcpr.backend.ocr.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Media_Trans {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long media_no;
    private String email;
    private Date trans_date;
    private String kind;
    private String input;
    @Lob
    private String output;
}

