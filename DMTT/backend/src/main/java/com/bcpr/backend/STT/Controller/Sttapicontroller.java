package com.bcpr.backend.STT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcpr.backend.STT.Helper.SttKorHelper;
import com.bcpr.backend.STT.Mapper.SttMapper;
import com.bcpr.backend.STT.util.FileSaveHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class Sttapicontroller {
    private SttMapper mapper;

    @Autowired
    public void setMapper(SttMapper mapper) {
        this.mapper = mapper;
    }
    @PostMapping("/Stt/Kor")
    public String getKorStt(@RequestParam(value="file", required=false) MultipartFile file,
                            HttpServletRequest request) throws IOException{
        FileSaveHelper fsh=new FileSaveHelper(request.getServletContext().getRealPath("resources"));

        String url= fsh.tempSave(file);
        SttKorHelper SKH=new SttKorHelper();
        String out= SKH.Sttkor(url);
        System.out.println("out :"+ out);
        fsh.delete(url);

        return out;
    }
}
