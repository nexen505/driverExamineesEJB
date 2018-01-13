package com.komarmoss.servlet;

import com.komarmoss.bean.VehicleService;
import com.komarmoss.config.CustomObjectMapper;
import com.komarmoss.model.vo.VehicleVO;
import com.komarmoss.model.vo.WebResponseVO;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "vehicleServlet", urlPatterns = {"/vehicles"})
public class VehicleServlet extends HttpServlet {
    private final CustomObjectMapper mapper = CustomObjectMapper.getInstance();
    @EJB(beanName = "vehicleService")
    private VehicleService vehicleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter out = resp.getWriter();
        final String id = req.getParameter("id");
        final WebResponseVO webResponseVO = new WebResponseVO(id != null ? vehicleService.findVehicle(Integer.valueOf(id)) : vehicleService.findVehicles());
        out.print(mapper.writeValueAsString(webResponseVO));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter out = resp.getWriter();
        final VehicleVO vehicleVO = mapper.readValue(req.getReader(), VehicleVO.class);
        final WebResponseVO webResponseVO = new WebResponseVO(vehicleService.saveOrUpdateVehicle(vehicleVO));
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
            final WebResponseVO webResponseVO = new WebResponseVO(vehicleService.removeVehicle(Integer.valueOf(id)));
            out.print(mapper.writeValueAsString(webResponseVO));
        }
    }
}
