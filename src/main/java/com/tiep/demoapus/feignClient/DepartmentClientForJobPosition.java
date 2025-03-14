package com.tiep.demoapus.feignClient;

import com.tiep.demoapus.dto.response.DepartmentListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "departmentClient",contextId = "departmentClientForJobPosition",
        url = "https://resources-service.dev.apusplatform.com/api/v1/department",
        configuration = FeignClientConfiguration.class)
public interface DepartmentClientForJobPosition {

    @GetMapping("/list")
    ApiResponse<DepartmentListResponse> getDepartmentsByIds(@RequestParam("ids") List<Long> ids);
}
