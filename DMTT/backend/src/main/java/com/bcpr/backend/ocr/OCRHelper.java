package com.bcpr.backend.ocr;
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
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class OCRHelper {
	public final String apiURL = "https://dakj3dl6zh.apigw.ntruss.com/custom/v1/14192/76e38e6cfacf0c4bdc5f9ca5d9b1b36d09f196b345d859b568020c6729d2c972/general";
	public final String secretKey = "TklYTGpycXh2d2lXVGRFR2JQZ1ppcURXdFhDeHNiYkc=";
	
	public String forJSON() {
		String result = "";
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			con.setRequestProperty("X-OCR-SECRET", secretKey);

			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("url", "https://kr.object.ncloudstorage.com/ocr-ci-test/sample/1.jpg"); // image should be public, otherwise, should use data
			// FileInputStream inputStream = new FileInputStream("YOUR_IMAGE_FILE");
			// byte[] buffer = new byte[inputStream.available()];
			// inputStream.read(buffer);
			// inputStream.close();
			// image.put("data", buffer);
			image.put("name", "demo");
			JSONArray images = new JSONArray();
			images.add(image);
			json.put("images", images);
			String postParams = json.toString();

			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			result = response.toString();
			System.out.println(response);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return result;
		}
	}
}
