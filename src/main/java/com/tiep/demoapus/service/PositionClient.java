package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.response.PositionResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "positionService", url = "${external.apis.position}")
public interface PositionClient {
    @GetMapping("/position/list")
    List<PositionResponseDTO> getPositionsByIds(@RequestParam("ids") List<Long> ids);
}
