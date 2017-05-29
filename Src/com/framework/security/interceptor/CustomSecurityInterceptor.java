package com.framework.security.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.framework.FrameConstant;
/**
 * @author dafei
 * 
 */
public class CustomSecurityInterceptor extends AbstractSecurityInterceptor implements Filter 
{
	private FilterInvocationSecurityMetadataSource customSecurityMetadataSource;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	throws IOException, ServletException 
	{   
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        setUserSession(fi);
        invoke(fi);
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() 
    {
        return this.customSecurityMetadataSource;
    }

    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    public void invoke(FilterInvocation fi) throws IOException,ServletException 
    {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    public SecurityMetadataSource obtainSecurityMetadataSource() 
    {
        return this.customSecurityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource) 
    {
        this.customSecurityMetadataSource = newSource;
    }

    /**
     * @param filter
     * @throws Exception
     */
    private void setUserSession(FilterInvocation filter)
	{
    	try{
    		SecurityContext ctx = SecurityContextHolder.getContext();  
    		Object obj=ctx.getAuthentication().getPrincipal();
    		FilterInvocation filterInvocation = (FilterInvocation) filter;
    		if(obj instanceof User){
    			User user=(User)obj; 
    			HttpSession hs=filterInvocation.getHttpRequest().getSession();
    			hs.setAttribute(FrameConstant.SESSION_ACEGI_ACCOUNT_KEY, user.getUsername());	
    			hs.setAttribute(FrameConstant.SESSION_ACEGI_USER_KEY, user);		
    			ctx=null;
    		}
    		obj=null;
    		ctx=null;
    		filterInvocation=null;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
    
    
    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
