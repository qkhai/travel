package cn.it.controller;

import cn.it.SysLog;
import cn.it.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date startTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法

    @Before("execution(* cn.it.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime=new Date();
        //获取访问的类
        executionClass = jp.getTarget().getClass();
        //获取访问的方法的名称
        String methodName = jp.getSignature().getName();
        //获取访问方法的参数
        Object[] args = jp.getArgs();
        if (args==null||args.length==0){
            executionMethod = executionClass.getMethod(methodName);
        }else {
            //有参数，就将args中所有元素遍历，获取对应的Class，装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            executionMethod = executionClass.getMethod(methodName,classArgs);
        }
    }

    @After("execution(* cn.it.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        if (executionClass!=SysLogController.class){
            //获取类上的@RequestMapping对象
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            if (classAnnotation !=null){
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String url = "";//它的值应该是类上的@RequesMapping的value+方法上的@RequestMapping的value
                    url = classAnnotation.value()[0]+methodAnnotation.value()[0];

                    SysLog sysLog = new SysLog();
                    Long executionTime = new Date().getTime() - startTime.getTime();

                    sysLog.setExecutionTime(executionTime);

                    sysLog.setUrl(url);

                    String ip = request.getRemoteAddr();
                    sysLog.setIp(ip);

                    //通过securutyContext获取，也可以从request.getSession获取
                    SecurityContext context = SecurityContextHolder.getContext();
                    String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名]"+executionClass.getName()+"[方法名]"+executionMethod.getName());
                    sysLog.setVisitTime(startTime);
                    sysLogService.save(sysLog);
                }
            }
        }

    }



}
