package com.spreadtrum.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept (ActionInvocation invocation) throws Exception{
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		String loginResult = (String) session.get("userName");
		
		if (loginResult != null && loginResult.equals("success"))
		{
			System.out.println("if"+loginResult);
			return invocation.invoke();
		}
		else {
			System.out.println("else"+loginResult);
			session.put("loginFail","您还没有登录了！");
			return "loginFail";
		}	
}

}