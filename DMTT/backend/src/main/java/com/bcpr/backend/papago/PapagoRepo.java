package com.bcpr.backend.papago;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class PapagoRepo {

	private String text;
	public String translationForArray(List<String> trans) {
		String result = "";
		
		for(int i=0; i<trans.size(); i++) {
			String temp1  = trans.get(i);
			String temp2 = translation(temp1);
			result += temp2;			
			log.info("test {}",result);
		}
		
		return result;
	}

		public String translation(String trans){
			String clientId = "SaCm2YYueh7Q0fyGTlXB";//애플리케이션 클라이언트 아이디값";
			String clientSecret = "N3gbaDledg";//애플리케이션 클라이언트 시크릿값";
			//파파고 API 서버 주소
			String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
			String text;
			
			  try {
				  text = URLEncoder.encode(trans, "UTF-8");
				  Map<String, String> requestHeaders = new HashMap<>();

				  requestHeaders.put("X-Naver-Client-Id", clientId);
				  requestHeaders.put("X-Naver-Client-Secret", clientSecret);

				  String responseBody = post(apiURL, requestHeaders, text);
                  
				  //System.out.println(responseBody);
				  

				  JSONParser jsonParser = new JSONParser(); 
				  
				  JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody); 
				  
				  JSONObject objMessage = (JSONObject) jsonObject.get("message"); 
				  
				  JSONObject objResult= (JSONObject) objMessage.get("result"); 
				   
				  String translatedText = (String) objResult.get("translatedText"); 
				  System.out.println(translatedText);
					  
				  return translatedText;

			  } catch (Exception e) {

				  throw new RuntimeException("인코딩 실패", e);

			  }
			  
			

			 
			  


		}
		
		private  String post(String apiUrl, Map<String, String> requestHeaders, String text){

			HttpURLConnection con = connect(apiUrl);

			
			
			
			String postParams = "source=ko&target=en&text=" + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)

			 try {
				 con.setRequestMethod("POST");
				 for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
					 con.setRequestProperty(header.getKey(), header.getValue());

				 }
				 
				  con.setDoOutput(true);
				  try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
					  wr.write(postParams.getBytes());
					  wr.flush();
				  }
				  
				  int responseCode = con.getResponseCode();
				  if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
					  return readBody(con.getInputStream());
				   } else {  // 에러 응답
					   return readBody(con.getErrorStream());
				   }
				  
			   } catch (IOException e) {
				   throw new RuntimeException("API 요청과 응답 실패", e);
			   } finally {
				      con.disconnect();
			   }
	    }
		
		
		
		
		private  HttpURLConnection connect(String apiUrl){

			try { 
				URL url = new URL(apiUrl); 
				return (HttpURLConnection)url.openConnection(); 
				} catch (MalformedURLException e) { 
					throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e); 
					} catch (IOException e) { 
						throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
						}
			}
		
		private  String readBody(InputStream body){
			InputStreamReader streamReader = new InputStreamReader(body);

			try (BufferedReader lineReader = new BufferedReader(streamReader)) { 
				StringBuilder responseBody = new StringBuilder();
				
				String line; 
				while ((line = lineReader.readLine()) != null) { 
					responseBody.append(line);
					}
				
				return responseBody.toString(); 
				} catch (IOException e) { 
					throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e); 
					} 
			} 
		}

	


