package com.daxia.core.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.annotation.JSONField;
import com.daxia.core.common.ManagerType;

/**
 * 商家管理员
 */
@Entity
@Table(name = "manager")
public class Manager extends BaseModel implements UserDetails  {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    private Long id;

	/**
	 * 商家登陆的用户名
	 */
    private String username;
    /**
     * 商家名称
     */
    private String storeName;
    private String password;

    /**
     * 联系方式
     */
    private String telephone;
    private Integer type;
    private String email;
    
    @Transient
    private boolean accountNonExpired = true;
    
    @Transient
    private boolean accountNonLocked = true;
    
    @Transient
    private boolean credentialsNonExpired = true;
    
    @Transient
    private boolean  enabled = true;
    
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 一个用户有多个角色，每个角色又包括了不同的权限。
     * 所以，一个用户的权限的集合，就是这个用户的 所有的角色的 所包括的权限 的集合
     */
    @ManyToMany(targetEntity = Role.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "userrole", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles;
    
    
    
   /* @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "user_id")
	private Set<OnlineUser> onlineUsers = new HashSet<OnlineUser>();
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "user_id")
	private Set<Contract> contracts = new HashSet<Contract>();

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "send_user_id")
    private Set<Chat> chats = new HashSet<Chat>();
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "receive_user_id")
    private Set<Chat> chats2 = new HashSet<Chat>();
*/    
    /*@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "user_id")
    private Set<Order> orders = new HashSet<Order>();*/
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @JSONField(serialize = false)
    public String getPassword() {
        return password;
    }
    @JSONField(serialize = false)
    public void setPassword(String password) {
        this.password = password;
    }
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    @JSONField(serialize = false)
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    @JSONField(serialize = false)
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    @JSONField(serialize = false)
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return enabled;
    }
    @JSONField(serialize = false)
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @JSONField(serialize = false)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @JSONField(serialize = false)
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
	/**
     * @return the roles
     */
    @JSONField(serialize = false)
    public Set<Role> getRoles() {
    	return roles;
    }
	/**
     * @param roles the roles to set
     */
    @JSONField(serialize = false)
    public void setRoles(Set<Role> roles) {
    	this.roles = roles;
    }

	public Integer getType() {
    	return type;
    }
	public void setType(Integer type) {
    	this.type = type;
    }
	public static long getSerialversionuid() {
    	return serialVersionUID;
    }
	public String getStoreName() {
    	return storeName;
    }
	public void setStoreName(String storeName) {
    	this.storeName = storeName;
    }
	public String getTelephone() {
    	return telephone;
    }
	public void setTelephone(String telephone) {
    	this.telephone = telephone;
    }
	public String getEmail() {
    	return email;
    }
	public void setEmail(String email) {
    	this.email = email;
    }

	public boolean isHead() {
		return this.getType() == ManagerType.Head.getValue();
	}
	
}
