package com.example.projectweb1.demo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        SessionFlashMapManager manager = new SessionFlashMapManager();
        FlashMap map = new FlashMap();
        map.put("success_handler", authentication.getName() + " te has logado con éxito te dice el handler");
        manager.saveOutputFlashMap(map, request, response);
//        super.setAlwaysUseDefaultTargetUrl(false); // By default is false
        super.setAlwaysUseDefaultTargetUrl(true);
        // Define has to get the target from the target parameter in the request
        super.setTargetUrlParameter("target");
        // Always go to /clients
        super.setDefaultTargetUrl("/clients");
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
