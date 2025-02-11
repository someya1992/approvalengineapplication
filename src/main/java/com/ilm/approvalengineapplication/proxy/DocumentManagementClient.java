package com.ilm.approvalengineapplication.proxy;

import com.ilm.approvalengineapplication.response.DocumentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "document-management-service")
public
interface DocumentManagementClient {
    @GetMapping("/documents/{loanId}")
    DocumentResponse getDocuments(@PathVariable("loanId") Long loanId);
}