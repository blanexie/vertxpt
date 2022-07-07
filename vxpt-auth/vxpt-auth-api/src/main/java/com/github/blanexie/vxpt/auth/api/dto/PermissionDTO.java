package com.github.blanexie.vxpt.auth.api.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class PermissionDTO {

    private String code;

    private String name;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionDTO that = (PermissionDTO) o;
        return code.equals(that.code) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}
