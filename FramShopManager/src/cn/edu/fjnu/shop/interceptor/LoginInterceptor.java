/**
 * 
 */
package cn.edu.fjnu.shop.interceptor;

import java.util.Map;

import cn.edu.fjnu.shop.action.LoginAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author GaoFei 登录验证拦截
 */
public class LoginInterceptor extends AbstractInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		if (LoginAction.class == invocation.getAction().getClass())
			return invocation.invoke();

		// 如果是请求其他页面，进行拦截
		Map<String, Object> map = invocation.getInvocationContext().getSession();
		if (null == map.get("userID"))
			return "login";
		return  invocation.invoke();
	}

}
