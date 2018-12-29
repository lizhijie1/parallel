package interfaceTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {
		//String url = "http://127.0.0.1/TeleComApi/GetCurrentInfo";
		
		String url = "http://59.56.74.153:9009/clwpt";
		
		HttpClient httpclient = new DefaultHttpClient();
		
		HttpPost post = new HttpPost(url);
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		
		JSONObject params = new JSONObject();
		
		try {
			//formparams.add(new BasicNameValuePair("LoginID","123456"));
			
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,"UTF-8");
			
			post.setEntity(entity);
			
			System.out.println("post...."+post);
			
			HttpResponse response = httpclient.execute(post);
			
			//response.getStatusLine().getStatusCode()==200
			int code = response.getStatusLine().getStatusCode();
			System.out.println(code);
			String r = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			System.out.println(r);
			
			JSONArray arr =(JSONArray) ((JSONObject)JSON.parse(r)).get("body");
			System.out.println(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
