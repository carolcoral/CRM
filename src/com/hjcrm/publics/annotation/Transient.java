package com.hjcrm.publics.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *    1.@Target,修饰的对象范围
　　　　2.@Retention,保留的时间长短
　　　　3.@Documented,标记注解 公共API 没有成员
　　　　4.@Inherited  标记注解 @Inherited阐述了某个被标注的类型是被继承的


 * @Target说明了Annotation所修饰的对象范围
 * 	  1.CONSTRUCTOR:用于描述构造器
　　　　2.FIELD:用于描述域
　　　　3.LOCAL_VARIABLE:用于描述局部变量
　　　　4.METHOD:用于描述方法
　　　　5.PACKAGE:用于描述包
　　　　6.PARAMETER:用于描述参数
　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明

 * @Retention定义了该Annotation被保留的时间长短
 * 	取值（RetentionPoicy）有：
　　　　1.SOURCE:在源文件中有效（即源文件保留）
　　　　2.CLASS:在class文件中有效（即class保留）
　　　　3.RUNTIME:在运行时有效（即运行时保留）
 * @author likang
 * @date   2017-5-18 下午5:52:33
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transient {

}
