package com.bcpr.backend.papago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TRANSLATION_NO ;
    private String email;
    private Date trans_date;
    @Lob
    private String input;
    @Lob
    private String output;
}
