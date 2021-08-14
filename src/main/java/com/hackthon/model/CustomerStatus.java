package com.hackthon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerStatus {

    @Schema(description = "status",
            example = "status", required = false)
    private String status;

    @Schema(description = "customerReferenceId",
            example = "Cust123444", required = false)
    private String customerReferenceId;

}
