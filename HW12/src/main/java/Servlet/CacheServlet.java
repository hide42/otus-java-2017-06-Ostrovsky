package Servlet;

import dbservice.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CacheServlet extends HttpServlet {
    private static final String CACHE_PAGE_TEMPLATE = "cache.html";
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";
    private static final Map<String, Object> PAGE_ACCESS_DENIED;

    static {
        PAGE_ACCESS_DENIED = new HashMap<>();
        PAGE_ACCESS_DENIED.put("hit", "AccessDenied");
        PAGE_ACCESS_DENIED.put("miss", "AccessDenied");
        PAGE_ACCESS_DENIED.put("count", "AccessDenied");
    }
    private DBService dbService;


    public CacheServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String[] status = dbService.getCacheStatus().split(" ");
        pageVariables.put("miss",status[1]);
        pageVariables.put("hit",status[3]);
        pageVariables.put("count",status[5]);

        String login = (String) req.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
        String password = (String) req.getSession().getAttribute(LoginServlet.PASSWORD_PARAMETER_NAME);
        login = (null == login) ? "" : login;
        password = (null == password) ? "" : password;


        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            String page = TemplateProcessor.instance().getPage(CACHE_PAGE_TEMPLATE, pageVariables);
            resp.getWriter().println(page);
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            String page = TemplateProcessor.instance().getPage(CACHE_PAGE_TEMPLATE, PAGE_ACCESS_DENIED);
            resp.getWriter().println(page);
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }

    }
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
