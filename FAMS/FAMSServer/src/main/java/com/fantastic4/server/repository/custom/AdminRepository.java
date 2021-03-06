package com.fantastic4.server.repository.custom;

import com.fantastic4.common.dto.AdminDTO;
import com.fantastic4.server.repository.SuperRepository;

public interface AdminRepository extends SuperRepository<AdminDTO, String> {

    AdminDTO login(AdminDTO adminDTO) throws Exception;
}
