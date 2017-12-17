package com.komarmoss.bean;

import com.komarmoss.model.vo.OwnerVO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OwnerService {

    List<OwnerVO> findOwners();

    OwnerVO findOwner(Integer id);

    OwnerVO saveOrUpdateOwner(OwnerVO owner);

    boolean removeOwner(Integer id);
}
