import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class OCRAPITest {
	public final String URL = "https://dakj3dl6zh.apigw.ntruss.com/custom/v1/14192/76e38e6cfacf0c4bdc5f9ca5d9b1b36d09f196b345d859b568020c6729d2c972/general";
	public final String KEY = "TklYTGpycXh2d2lXVGRFR2JQZ1ppcURXdFhDeHNiYkc=";
	private JSONObject data;
	
	OCRAPITest(String img){
		data = new JSONObject();
		data.put("version", "V1");
		data.put("requestId", "sample_id");
		data.put("timestamp", 0);
		data.put("lang", "ko");
		
		JSONArray images = new JSONArray(); // 0~1 만 허용중 20220210 기준
		JSONObject image = new JSONObject();
		image.put("format","jpg"); //확장자
		image.put("name","test"); //이름 아무거나
		image.put("url",img); // url
		//image.put("data", file); base64 인코딩 이미지 바이트
		//url 또는 data 있어야함
		images.add(image);
		
		data.put("images",images);
		
		/*
		 *	request 속성
			"lineBreak": true 줄바꿈
		*/
	}
	
	public String getDataToString(){
		return data.toJSONString();
	}
	
	public static void main(String args[]) throws IOException, ParseException {
		CloseableHttpClient httpClient = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		
		try {
			httpClient = HttpClientBuilder.create().build();
			OCRAPITest ocr = new OCRAPITest("https://i.postimg.cc/mgWy2hJw/image.jpg");
			HttpPost request = new HttpPost(ocr.URL);
			request.addHeader("Content-Type","application/json");
			request.addHeader("X-OCR-SECRET",ocr.KEY);
			StringEntity postingString = new StringEntity(ocr.getDataToString(),ContentType.APPLICATION_JSON);
			request.setEntity(postingString);
			// 실행
			CloseableHttpResponse httpresponse = httpClient.execute(request);

			// 결과 수신
			InputStream inputStream = (InputStream)httpresponse.getEntity().getContent();

			String inputLine = null;
			in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

			while((inputLine = in.readLine()) != null) {
				result.append(inputLine);
				System.out.println(inputLine);
			}
			System.out.println(result.toString());
			
			JSONParser jsonParser = new JSONParser();
			//JSON데이터를 넣어 JSON Object 로 만들어 준다.
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
             
            //images의 배열을 추출
            JSONArray imageInfoArray = (JSONArray) jsonObject.get("images");
 
            System.out.println("* images *");
 
            for(int i=0; i<imageInfoArray.size(); i++){
 
                System.out.println("=images_"+i+" ===========================================");
                JSONObject fields = (JSONObject) imageInfoArray.get(i);
                JSONArray fieldInfoArray = (JSONArray) fields.get("fields");
                //System.out.println(fields.toJSONString());
                for(int j=0; j<fieldInfoArray.size(); j++) {
                	//배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                    JSONObject fieldObject = (JSONObject) fieldInfoArray.get(j);
                    //fieldInfoArray.getBoolean("lineBreak") == false;
                    System.out.println(fieldObject);
                    //System.out.println(fieldObject.get("inferText"));
                    //if(fieldObject.get("lineBreak").equals("true")) {
                    //	System.out.println("");
                    //}
                }
 
            }
		}catch(IOException ex) {
			throw ex;
		}finally {
			if(in != null) {
		          try {
		              in.close();
		          } catch(IOException ex) { throw ex; }
		      }
		}
		
	}
}
