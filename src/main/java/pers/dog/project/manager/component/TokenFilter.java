package pers.dog.project.manager.component;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;
import pers.dog.project.manager.component.context.SecurityContext;

import javax.servlet.*;
import java.io.IOException;

/**
 * 令牌拦截
 *
 * @author 废柴 2020/12/24 16:29
 */
@Component
public class TokenFilter implements Filter {
    private final SecurityContext securityContext;

    public TokenFilter(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            if (servletRequest instanceof RequestFacade) {
                securityContext.storeAccessToken(((RequestFacade) servletRequest).getHeader("Authorization"));
            }
        } finally {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
