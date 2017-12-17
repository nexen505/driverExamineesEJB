package com.komarmoss.servlet;

import com.komarmoss.bean.OwnerService;
import com.komarmoss.config.CustomObjectMapper;
import com.komarmoss.model.vo.OwnerVO;
import com.komarmoss.model.vo.WebResponseVO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;

@WebServlet(name = "ownerServlet", urlPatterns = {"/owners"})
public class OwnerServlet extends HttpServlet {

    @EJB
    private OwnerService ownerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintStream out = new PrintStream(resp.getOutputStream());
        final String id = req.getParameter("id");
        final WebResponseVO webResponseVO = new WebResponseVO(id != null ? ownerService.findOwner(Integer.valueOf(id)) : ownerService.findOwners());
        out.print(CustomObjectMapper.getInstance().writeValueAsString(webResponseVO));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintStream out = new PrintStream(resp.getOutputStream());
        final CustomObjectMapper mapper = CustomObjectMapper.getInstance();
        final OwnerVO ownerVO = mapper.readValue(req.getReader(), OwnerVO.class);
        final WebResponseVO webResponseVO = new WebResponseVO(ownerService.saveOrUpdateOwner(ownerVO));
        out.print(mapper.writeValueAsString(webResponseVO));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintStream out = new PrintStream(resp.getOutputStream());
        final String id = req.getParameter("id");
        if (id != null) {
            final WebResponseVO webResponseVO = new WebResponseVO(ownerService.removeOwner(Integer.valueOf(id)));
            out.print(CustomObjectMapper.getInstance().writeValueAsString(webResponseVO));
        }
    }
}
