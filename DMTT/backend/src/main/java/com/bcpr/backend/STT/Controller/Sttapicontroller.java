package com.bcpr.backend.STT.Controller;

import com.bcpr.backend.ocr.domain.Media_Trans;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bcpr.backend.STT.Helper.SttHelper;
import com.bcpr.backend.STT.Mapper.SttMapper;
import com.bcpr.backend.STT.domain.Document_Trans;
import com.bcpr.backend.STT.util.FileSaveHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
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
        return mapper.insertDocument_TransContent(email,trans_date,kind,url,output);
    }
    @GetMapping("/Stt/list/{email}")
    public List<Document_Trans> getList(
            @PathVariable("email") String email){
        //log.info("test : {}",test.get(0));
        return mapper.getDocument_TransListByEmail(email);
    }

    @GetMapping("/Stt/download/{email}/{document_no}/{kind}")
    public void download(
            @PathVariable("email") String email,
            @PathVariable("document_no") int document_no,
            @PathVariable("kind") String kind,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Media_Trans mt = mapper.getDocument_Trans(email, document_no);

        String path = "";
        path = request.getServletContext().getRealPath("resources")
                +"\\document_no\\"
                + mt.getEmail()
                + "\\";

        if(kind.equals("input")) {
            path += mt.getInput(); // 경로에 접근할 때 역슬래시('\') 사용

        }else {
            path += mt.getTrans_date()+"-output.txt";
            try{
                // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
                BufferedWriter fw = new BufferedWriter(new FileWriter(path, true));
                // 파일안에 문자열 쓰기
                fw.write(mt.getOutput());
                fw.flush();
                // 객체 닫기
                fw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        File file = new File(path);
        byte[] fileByte = FileUtils.readFileToByteArray(file);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(file.getName(), "UTF-8")+"\"");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();

        if(kind.equals("output") && file.exists() ){
            if(file.delete()){
            }
        }
    }
}
