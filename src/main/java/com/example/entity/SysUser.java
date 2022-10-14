package com.example.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @TableName sys_user
 */
@Data
public class SysUser implements Serializable, UserDetails {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String nickName;

    /**
     * 性别(0男,1女,2未知)
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private String openId;

    /**
     * 
     */
    private boolean status;

    /**
     * 
     */
    private Integer admin;

    /**
     * 
     */
    private String phoneNumber;

    @TableField(exist = false)
    private List<SysRole> roles;
    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickName=").append(nickName);
        sb.append(", sex=").append(sex);
        sb.append(", avatar=").append(avatar);
        sb.append(", address=").append(address);
        sb.append(", openId=").append(openId);
        sb.append(", status=").append(status);
        sb.append(", admin=").append(admin);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list=new ArrayList<>();
        roles.forEach(item->list.add(new SimpleGrantedAuthority("ROLE"+item.getCode())));
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}