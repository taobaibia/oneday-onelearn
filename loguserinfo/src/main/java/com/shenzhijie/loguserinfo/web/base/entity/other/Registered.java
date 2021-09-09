package com.shenzhijie.loguserinfo.web.base.entity.other;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loginopddd.entity</p>
 * <p>ClassName:LoginInfo</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/6/25
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@TableName(value = "login")
@Data
public class Registered extends Model<Registered> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotEmpty(message = "用户名不为空")
    private String name;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @Email
    private String email;
    @Size(min = 1, max = 25, message = "地址长度应该在1~30之间")
    private String address;

    @Override
    public String toString() {
        return "Registered{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Registered() {
    }

    public Registered(Integer id, String name, String password, String email, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}
