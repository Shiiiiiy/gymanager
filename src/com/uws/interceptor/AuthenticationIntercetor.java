package com.uws.interceptor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;


public class AuthenticationIntercetor implements HandlerInterceptor {

	  private final Logger log = LoggerFactory.getLogger(AuthenticationIntercetor.class);  

	  private PathMatcher pathMatcher = new AntPathMatcher();
	  private List<String> allowUrl;
	  
	  
	  public static final String LAST_PAGE = "com.alibaba.lastPage";

	    /**  
	     * 在业务处理器处理请求之前被调用  
	     * 如果返回false  
	     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
	     * 如果返回true  
	     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
	     *    再执行被拦截的Controller  
	     *    然后进入拦截器链,  
	     *    从最后一个拦截器往回执行所有的postHandle()  
	     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
	     */    
	    @Override    
	    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{
	    	
	        log.info("==============执行顺序: 1、preHandle================");    
	        String requestUri = request.getRequestURI();
	        String contextPath = request.getContextPath();  
	        

            return true;
            
            /**
	        for (String url : this.allowUrl) {
	        	 if (requestUri.indexOf(contextPath+url) == 0) {
                    return true;  
                }
	        }
	        
	  
	        User user =  (User)request.getSession().getAttribute(DefaultValue.SESSION_KEY);   
	        if(user == null){  
	            log.info("Interceptor：跳转到login页面！"); 
//	            System.out.println(request.getContextPath() + "/login.do");
	            
	            
	            
            	response.setCharacterEncoding("UTF-8");
            	//response.sendError(HttpStatus.UNAUTHORIZED.value(),"您已经太长时间没有操作,请刷新页面"); 
	            response.sendRedirect(contextPath + "/login.do"); 
	            
	            
	            return false;  
	        }else  
	            return true;     
	            
	            
	            **/
	    }    
	    
	    /** 
	     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
	     * 可在modelAndView中加入数据，比如当前时间 
	     */  
	    @Override    
	    public void postHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler,    
	            ModelAndView modelAndView) throws Exception {     
	        log.info("==============执行顺序: 2、postHandle================");    
	        if(modelAndView != null){  //加入当前时间    
	            modelAndView.addObject("var", "测试postHandle");    
	        }    
	    }    
	    
	    /**  
	     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
	     *   
	     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
	     */    
	    @Override    
	    public void afterCompletion(HttpServletRequest request,    
	            HttpServletResponse response, Object handler, Exception ex)    
	            throws Exception {    
	        log.info("==============执行顺序: 3、afterCompletion================");    
	    }

		public List<String> getAllowUrl() {
			return allowUrl;
		}

		public void setAllowUrl(List<String> allowUrl) {
			this.allowUrl = allowUrl;
		}
		
		
		
		
		
}