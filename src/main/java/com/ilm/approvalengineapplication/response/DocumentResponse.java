package com.ilm.approvalengineapplication.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class DocumentResponse {
    private Long loanId;
    private boolean documentsUploaded;
    public boolean isDocumentsUploaded() { return documentsUploaded; }
    // Getters and Setters
}