package com.tiep.demoapus.feignClient;

import com.tiep.demoapus.dto.response.PositionListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "departmentClient",
        url = "https://resources-service.dev.apusplatform.com/api/v1/position",
        configuration = FeignClientConfiguration.class)
public interface PositionClient {
    @GetMapping("/list")
    ApiResponse<PositionListResponse> getPositionsByIds(@RequestParam("ids") List<Long> ids);
}

