package com.shenzhijie.loguserinfo.web.base.result;

import com.shenzhijie.loguserinfo.web.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.info4z.club
 * <p>title:com.shenzhijie.loguserinfo.web.base.result</p>
 * <p>ClassName:ResultWrapper</p>
 * <p>Description:TODO(请用一句话描述这个类的作用)</p>
 * <p>Compony:Info4z</p>
 * author:zhijieShen
 * date:2021/6/29
 * version:1.0
 * 注意：本内容仅限于公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
@Builder
public class ResultWrapper<T> implements Serializable {
    private static final long serialVersionUID = 5519803148492275832L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;

    private T data;

    /**
     * @author user
     * 返回成功的包装
     */
    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder() {
        return ResultWrapper.builder().code(StateCodeEnum.SUCCESS.getCode()).msg(StateCodeEnum.SUCCESS.getMsg());
    }

    /**
     * @author user
     * 返回失败的包装
     */
    public static ResultWrapper.ResultWrapperBuilder getErrorBuilder() {
        return ResultWrapper.builder().code(StateCodeEnum.ERROR.getCode()).msg(StateCodeEnum.ERROR.getMsg());
    }

}
