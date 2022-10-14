package com.example.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName sys_menu
 */
@Data
public class SysMenu implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String path;

    /**
     * 
     */
    private String icon;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String compoent;

    /**
     * 
     */
    private Long parentId;

    private List<SysMenu> children;

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
        SysMenu other = (SysMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getCompoent() == null ? other.getCompoent() == null : this.getCompoent().equals(other.getCompoent()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getCompoent() == null) ? 0 : getCompoent().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", path=").append(path);
        sb.append(", icon=").append(icon);
        sb.append(", title=").append(title);
        sb.append(", compoent=").append(compoent);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}