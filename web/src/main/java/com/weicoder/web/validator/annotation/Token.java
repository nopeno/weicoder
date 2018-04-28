package com.weicoder.web.validator.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.weicoder.common.constants.StringConstants;

/**
 * 使用TokenEngine验证Token
 * @author WD
 */
@Target({ METHOD, TYPE })
@Retention(RUNTIME)
public @interface Token {
	/**
	 * 验证token的参数名 默认token
	 * @return
	 */
	String value() default "token";

	/**
	 * 无效的Token错误码
	 * @return 错误码
	 */
	int valid() default 101;

	/**
	 * 过期的Token错误码
	 * @return
	 */
	int expire() default 102;

	/**
	 * 验证id是否相同 验证用户token与用户id是否相同 不为0验证
	 * @return
	 */
	String id() default StringConstants.EMPTY;
}
