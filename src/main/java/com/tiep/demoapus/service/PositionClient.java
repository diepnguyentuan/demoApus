package com.tiep.demoapus.service;

import com.tiep.demoapus.dto.response.PositionResponseDTO;
import com.tiep.demoapus.service.ServiceImp.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "positionClient", url = "${external.apis.position}", configuration = FeignClientConfiguration.class)
public interface PositionClient {
    @GetMapping
    PositionResponseDTO getPositionsById(@RequestParam("positionId") Long id);
}

