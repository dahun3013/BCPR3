package com.bcpr.backend.STT;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChnSTT {
    @GetMapping("/Stt/Chn/{Test}")
    public ModelAndView Chn(ModelAndView mav) {
        String clientId = "gy9rblfn04";             // Application Client ID";
        String clientSecret = "I1T04u0w0fjkieCBPH3ktkdOuxDmWHEXI5BWJ0a7";     // Application Client Secret";

        try {
            String imgFile = "C:\\Test//EngTest.mp3";//DB에서 받아오는 자료
            File voiceFile = new File(imgFile);

            String language = "Chn";        // 언어 코드 ( Kor, Jpn, Eng, Chn )
            String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream;");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                    mav.addObject("SttTest",response);
                    mav.setViewName("");//지정해야함
                    return mav;
                }
                br.close();
                System.out.println(response.toString());
            } else {
                System.out.println("error !!!");
                mav.setViewName("index");
                return mav;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        mav.setViewName("index");
        return mav;
    }
}
