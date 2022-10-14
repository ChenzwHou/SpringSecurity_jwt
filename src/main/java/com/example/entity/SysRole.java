package com.example.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName sys_role
 */
@Data
public class SysRole implements Serializable {

    private int id;
    /**
     * 角色描述
     */
    private String label;

    /**
     * 
     */
    private String code;


    private List<SysMenu> menus;

    private List<SysPermission> permissions;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRole other = (SysRole) that;
        return (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", label=").append(label);
        sb.append(", code=").append(code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}