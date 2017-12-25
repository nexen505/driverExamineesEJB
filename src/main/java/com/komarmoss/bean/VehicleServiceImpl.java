package com.komarmoss.bean;

import com.komarmoss.model.dao.VehicleDAO;
import com.komarmoss.model.entity.VehicleEntity;
import com.komarmoss.model.vo.VehicleVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class VehicleServiceImpl implements VehicleService {

    @EJB(beanName = "vehicleDAO")
    private VehicleDAO vehicleDAO;

    @Override
    public List<VehicleVO> findVehicles() {
        return vehicleDAO.getAllItems()
                .parallelStream()
                .map(VehicleVO::new)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleVO findVehicle(Integer id) {
        return new VehicleVO(vehicleDAO.getItemById(id));
    }

    @Override
    public VehicleVO saveOrUpdateVehicle(VehicleVO vehicle) {
        VehicleEntity vehicleEntity = vehicle.createEntity();
        vehicleDAO.saveOrUpdateItem(vehicleEntity);
        return findVehicle(vehicleEntity.getId());
    }

    @Override
    public boolean removeVehicle(Integer id) {
        if (id != null) {
            vehicleDAO.removeItemById(id);
            return true;
        }
        return false;
    }

}
