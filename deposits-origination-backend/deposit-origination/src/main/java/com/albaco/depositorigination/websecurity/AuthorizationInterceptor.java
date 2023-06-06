package com.albaco.depositorigination.websecurity;

import com.albaco.depositorigination.common.Constants;
import com.albaco.depositorigination.common.UrlConstants;
import com.albaco.depositorigination.common.utils.CommonUtils;
import com.albaco.depositorigination.weborigination.api.v1.model.SessionRedisModel;
import com.albaco.depositorigination.weborigination.api.v1.redisrepo.SessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod)
        {

            HandlerMethod handlerMethod = ((HandlerMethod) handler);
            String controllerMethod = handlerMethod.toString();
            AuthorizationTypes auth = handlerMethod.getMethodAnnotation(AuthorizationTypes.class);
            if (auth.openToUnAuthenticatedSessionUsers()) {
                String sessionId = request.getHeader(Constants.UNAUTHENTICATED_API_AUTH_HEADER_KEY);
                if (CommonUtils.isStringEmpty(sessionId)) {
                    request.getRequestDispatcher(UrlConstants.ACCESS_DENIED_URL).forward(request, response);
                    return false;
                }

                Optional<SessionRedisModel> sessionRedisModel = sessionRepository.findById(sessionId);
                if(sessionRedisModel.isEmpty()) {
                    request.getRequestDispatcher(UrlConstants.ACCESS_DENIED_URL).forward(request, response);
                    return false;
                }
                sessionRepository.save(sessionRedisModel.get());
                request.setAttribute(Constants.UNAUTHENTICATED_SESSION_OBJECT_KEY, sessionRedisModel.get());
            }
            else if (auth.openToAll()) {
                return true;
            }
            else if (auth.internalForward()) {
                return true;
            }

            return true;

        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
