package com.bcpr.backend.STT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcpr.backend.STT.Helper.SttHelper;
import com.bcpr.backend.STT.Mapper.SttMapper;
import com.bcpr.backend.STT.domain.Document_Trans;
import com.bcpr.backend.STT.util.FileSaveHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Sttapicontroller {
    private SttMapper mapper;

    @Autowired
    public void setMapper(SttMapper mapper) {
        this.mapper = mapper;
    }
    @PostMapping("/Stt")
    public String getKorStt(@RequestParam(value="file", required=false) MultipartFile file,
                            @RequestParam(value="lang") String lang,
                            HttpServletRequest request) throws IOException{
        FileSaveHelper fsh=new FileSaveHelper(request.getServletContext().getRealPath("resources"));
        System.out.println("lang"+ lang);
        String url= fsh.tempSave(file);
        SttHelper SH=new SttHelper();
        String out= SH.getSTThelper(url,lang);
        System.out.println("out :"+ out);
        fsh.delete(url);

        return out;
    }

    @PostMapping("/Stt/upload")
    public int upload(
            @RequestParam("email") String email,
            @RequestParam("trans_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime trans_date,
            @RequestParam("kind") String kind,
            @RequestParam(value="input", required=false) MultipartFile input,
            @RequestParam("output") String output,
            HttpServletRequest request)throws IOException {

        FileSaveHelper fsh = new FileSaveHelper(request.getServletContext().getRealPath("resources"));
        String url = fsh.document_transSave(email, kind, trans_date, input);
        return mapper.insertMedia_TransContent(email,trans_date,kind,url,output);
    }
    @GetMapping("/Stt/download")
    public List<Document_Trans> download(
            @RequestParam("email") String email){
        //log.info("test : {}",test.get(0));
        return mapper.getDocument_Trans(email);
    }
}
