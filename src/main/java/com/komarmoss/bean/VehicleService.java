package com.komarmoss.bean;

import com.komarmoss.model.vo.TypeOfVehicleVO;
import com.komarmoss.model.vo.VehicleVO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface VehicleService {

    List<VehicleVO> findVehicles();

    VehicleVO findVehicle(Integer id);

    VehicleVO saveOrUpdateVehicle(VehicleVO owner);

    boolean removeVehicle(Integer id);

    List<TypeOfVehicleVO> getTypesOfVehicles();

}
