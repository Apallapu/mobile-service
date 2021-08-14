package com.hackthon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {

    @Schema(description = "customerName",
            example = "customerName", required = false)
    private String customerName;

    @Schema(description = "customerReferenceId",
            example = "Cust123444", required = false)
    private String customerReferenceId;

    @Schema(description = "email",
            example = "test@gmail.com", required = false)
    private String email;

    @Schema(description = "status",
            example = "status", required = false)
    private String status;

    @Schema(description = "ContactDTO")
    private ContactDTO contactDTO;

    @Schema(description = "PlanDTO")
    private List<PlanDTO> plans;

    @Schema(description = "documentsDTO")
    private DocumentsDTO documentsDTO;

}
