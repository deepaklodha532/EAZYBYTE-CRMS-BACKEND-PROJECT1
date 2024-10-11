package com.crms.CustomerRelationalManagement.helper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    String message ;
    Boolean success ;
}
