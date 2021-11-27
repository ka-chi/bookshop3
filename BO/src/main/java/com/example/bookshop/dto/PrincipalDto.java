package com.example.bookshop.dto;/**
 * @Auther: shu'ai
 * @Date: 2021/10/18 17:24
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName:principalDto
 * @Auther: shu'ai
 * @Description: 登陆后存在session 的类
 * @Date: 2021/10/18 17:24
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrincipalDto implements UserDetails {

    private String name;

    private String sex;

    private String username;

    private String password;

    private String tel;


    /**
     * 钱包余额
     */
    private float money;

    private String registerTime;
    /**
     * 权限
     */
    private Integer authentic;

    private Integer id;


    private String shopName;
    private String shopAddress;
    private String type;
    private float registerMoney;

    private String log;//标志
    /**
     * 审核状态
     */
    private Integer checkStatus;

    private  String address;

    private List<String> roleList;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
        //return this.roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 这个地方是来操作是否开启用户账号锁定的
     * 默认是false就是开启锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
