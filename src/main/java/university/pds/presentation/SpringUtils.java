package university.pds.presentation;

import javax.faces.context.FacesContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public class SpringUtils {

		public static <T> T getBean(Class<T> beanClass){
			
			FacesContext jsfContext = FacesContext.getCurrentInstance();
			
			WebApplicationContext springContext = FacesContextUtils.getWebApplicationContext(jsfContext);
			
			return springContext.getBean(beanClass);
			
		}
	
}