package com.hackthon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PlanDTO {

    @Schema(description = "planName",
            example = "planName", required = false)
    private String planName;

    @Schema(description = "planType",
            example = "planType", required = false)
    private String planType;

}
