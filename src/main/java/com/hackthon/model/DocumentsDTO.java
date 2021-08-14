package com.hackthon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DocumentsDTO {

    @Schema(description = "documentId",
            example = "documentId", required = false)
    private String documentId;

    @Schema(description = "documentName",
            example = "documentName", required = false)
    private String documentName;
    @Schema(description = "documentType",
            example = "documentType", required = false)
    private String documentType;

}
