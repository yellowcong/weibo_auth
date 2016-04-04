package com.yellowcong.weibo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;

/**
 * Servlet Filter implementation class SinaAuthFilter
 */
public class SinaAuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SinaAuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		//获取token
		try {
			String code = request.getParameter("code");
			if(code != null && !"".equals(code)){
				Oauth oauth = new Oauth();
				AccessToken token = oauth.getAccessTokenByCode(code);
				//获取TOKEN
				String tokenStr = token.getAccessToken();
				
				//token
				System.out.println("获取的token"+tokenStr);
				
				//获取用户的UID
				Account account = new Account(tokenStr);
				String uid = account.getUid().getString("uid").toString();
				
				//通过token 来获取用户的 userid
//				User user = new User(request);
				
				//获取用户对象
				Users users = new Users(tokenStr);
				User user = users.showUserById(uid);
				
				//获取的数据
				System.out.println("用户名称：\t"+user.getScreenName());
				System.out.println("用户性别：\t"+user.getGender());
				System.out.println("所在城市id：\t"+user.getCity());
				System.out.println("所在头像：\t"+user.getAvatarLarge());
				System.out.println("所居住城市:\t"+user.getLocation());
				System.out.println("博客地址:\t"+user.getUrl());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
