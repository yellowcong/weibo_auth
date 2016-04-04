package weibo4j.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.junit.Test;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class DemoTest {

	
	@Test
	public void test1() throws Exception{
		
		 Oauth oauth = new Oauth();
		 String str = oauth.authorize("code");
		 System.out.println("访问路径"+str);
		    BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		    String code = br.readLine();
		    System.out.println(code);
		    try{
		        System.out.println(oauth.getAccessTokenByCode(code));
		    } catch (WeiboException e) {
		        if(401 == e.getStatusCode()){
		        }else{
		            e.printStackTrace();
		        }
		    }
	}
}
