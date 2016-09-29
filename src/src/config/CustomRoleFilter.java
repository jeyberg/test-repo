package src.config;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

public class CustomRoleFilter extends RolesAuthorizationFilter {

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;
		
		if(rolesArray == null || rolesArray.length == 0){
			return true;
		}
		
		for(String role : rolesArray){
			if(subject.hasRole(role)){
				return true;
			}
		}
		
		return false;
	}
}
