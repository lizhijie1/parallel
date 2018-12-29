package interfaceTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class Test2 {
	public static void main(String[] args) {
		String url ="http://www.baidu.com";
		
		HttpClient client = new DefaultHttpClient();
		
		HttpPost post = new HttpPost(url); 
		
		JSONObject json = new JSONObject();  
		json.put("name", "admin");
		json.put("pass", "123456");        
        try {
			StringEntity se = new StringEntity(json.toJSONString(),"UTF-8");
			
			se.setContentEncoding("UTF-8");    			
			se.setContentType("applicaton/json");
			
			System.out.println(se);
			
			post.setEntity(se);
			
			System.out.println(post.getURI());
			
            HttpResponse response=client.execute(post);
            
            System.out.println(response.getStatusLine());
            
            int code = response.getStatusLine().getStatusCode();
            
			if(code == 302){
				//302
				Header header = response.getFirstHeader("location");
			    String newuri = header.getValue();
			    System.out.println(newuri);
			}
            
            System.out.println("×´Ì¬Âë_______£º"+code);
            
            HttpEntity entity = response.getEntity();
            
            String res = EntityUtils.toString(entity,"utf-8");
            
            System.out.println(res);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
