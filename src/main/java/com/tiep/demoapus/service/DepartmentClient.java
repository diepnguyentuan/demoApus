package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "departmentService", url = "${external.apis.department}")
public interface DepartmentClient {
    @GetMapping("/department/list")
    List<DepartmentResponseDTO> getDepartmentsByIds(@RequestParam("ids") List<Long> ids);
}
