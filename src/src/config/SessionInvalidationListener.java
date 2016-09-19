package src.config;

import java.security.Principal;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jboss.security.CacheableManager;

@WebListener
public class SessionInvalidationListener implements HttpSessionListener{
	
	@Inject
	private Principal principal;
	
	@Resource(name = "java:jboss/jaas/securedDomain/authenticationMgr")
    private CacheableManager<?, Principal> authenticationManager;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		authenticationManager.flushCache(principal);
		
	}

}
