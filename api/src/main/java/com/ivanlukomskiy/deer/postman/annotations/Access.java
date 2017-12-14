package com.ivanlukomskiy.deer.postman.annotations;

import com.ivanlukomskiy.deer.postman.model.User;

import java.lang.annotation.*;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 14.12.2017.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
@Inherited
@Documented
public @interface Access {

    /*
     * Array of role names, which should have user - owner of the token
     */
    User.Role[] value() default User.Role.USER;
}
