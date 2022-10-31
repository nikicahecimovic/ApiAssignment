package com.assignment.request;

import java.util.Set;

public class UpdateTagRequest {
    private Set<String> tags;

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
