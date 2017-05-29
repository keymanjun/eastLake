package com.framework.security.interceptor;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
/**
 * �����
 * @author dafei
 * <p>
 * </p>
 */
public class CustomAccessDecisionManagerBean implements AccessDecisionManager
{
	public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException 
    {
        if(configAttributes == null){
            return ;
        }
        System.out.println(object.toString());  //object is a URL.
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext())
        {
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){
                if(needRole.equals(ga.getAuthority())){  //ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

}
