package com.komarmoss.servlet;

import com.komarmoss.bean.VehicleService;
import com.komarmoss.config.CustomObjectMapper;
import com.komarmoss.model.vo.WebResponseVO;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "typeOfVehiclesServlet", urlPatterns = {"/typesOfVehicle"})
public class TypeOfVehiclesServlet extends HttpServlet {
    private final CustomObjectMapper mapper = CustomObjectMapper.getInstance();
    @EJB
    private VehicleService vehicleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter out = resp.getWriter();
        final String id = req.getParameter("id");
        final WebResponseVO webResponseVO = new WebResponseVO(vehicleService.getTypesOfVehicles());
        out.print(mapper.writeValueAsString(webResponseVO));
    }

}
