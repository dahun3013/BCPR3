package com.bcpr.backend.STT.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document_Trans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long document_no;
    private String email;
    private Date trans_date;
    private String kind;
    private String input;
    @Lob
    private String output;
}
