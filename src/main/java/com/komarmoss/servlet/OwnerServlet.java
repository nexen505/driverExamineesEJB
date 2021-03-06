package com.komarmoss.servlet;

import com.komarmoss.bean.OwnerService;
import com.komarmoss.config.CustomObjectMapper;
import com.komarmoss.model.vo.OwnerVO;
import com.komarmoss.model.vo.WebResponseVO;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ownerServlet", urlPatterns = {"/rest/owners"})
public class OwnerServlet extends HttpServlet {

    private final CustomObjectMapper mapper = CustomObjectMapper.getInstance();
    @EJB(beanName = "ownerService")
    private OwnerService ownerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter out = resp.getWriter();
        final String id = req.getParameter("id");
        final WebResponseVO webResponseVO = new WebResponseVO(id != null ? ownerService.findOwner(Integer.valueOf(id)) : ownerService.findOwners());
        out.print(mapper.writeValueAsString(webResponseVO));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter out = resp.getWriter();
        final OwnerVO ownerVO = mapper.readValue(req.getReader(), OwnerVO.class);
        final WebResponseVO webResponseVO = new WebResponseVO(ownerService.saveOrUpdateOwner(ownerVO));
        out.print(mapper.writeValueAsString(webResponseVO));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter out = resp.getWriter();
        final String id = req.getParameter("id");
        if (id != null) {
            final WebResponseVO webResponseVO = new WebResponseVO(ownerService.removeOwner(Integer.valueOf(id)));
            out.print(mapper.writeValueAsString(webResponseVO));
        }
    }
}
