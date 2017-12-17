package com.komarmoss.servlet;

import com.komarmoss.config.CustomObjectMapper;
import com.komarmoss.model.vo.VehicleVO;
import com.komarmoss.model.vo.WebResponseVO;
import com.komarmoss.bean.VehicleService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ownerServlet", urlPatterns = {"/vehicles"})
public class VehicleServlet extends HttpServlet {
    @EJB
    private VehicleService vehicleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter out = resp.getWriter();
        final String id = req.getParameter("id");
        final WebResponseVO webResponseVO = new WebResponseVO(id != null ? vehicleService.findVehicle(Integer.valueOf(id)) : vehicleService.findVehicles());
        out.print(CustomObjectMapper.getInstance().writeValueAsString(webResponseVO));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter out = resp.getWriter();
        final CustomObjectMapper mapper = CustomObjectMapper.getInstance();
        final VehicleVO vehicleVO = mapper.readValue(req.getReader(), VehicleVO.class);
        final WebResponseVO webResponseVO = new WebResponseVO(vehicleService.saveOrUpdateVehicle(vehicleVO));
        out.print(mapper.writeValueAsString(webResponseVO));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final PrintWriter out = resp.getWriter();
        final String id = req.getParameter("id");
        if (id != null) {
            final WebResponseVO webResponseVO = new WebResponseVO(vehicleService.removeVehicle(Integer.valueOf(id)));
            out.print(CustomObjectMapper.getInstance().writeValueAsString(webResponseVO));
        }
    }
}
