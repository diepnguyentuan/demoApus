package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.response.ApiResponse;
import com.tiep.demoapus.dto.response.DepartmentListResponse;
import com.tiep.demoapus.dto.response.DepartmentResponseDTO;
import com.tiep.demoapus.service.ServiceImp.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "departmentClient",
        url = "https://resources-service.dev.apusplatform.com/api/v1/department",
        configuration = FeignClientConfiguration.class)
public interface DepartmentClient {
    @GetMapping
    ApiResponse<DepartmentResponseDTO> getDepartmentById(@RequestParam("departmentId") Long id);

    @GetMapping("/list")
    ApiResponse<DepartmentListResponse> getDepartmentsByIds(@RequestParam("ids") List<Long> ids);
}
