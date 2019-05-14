package com.kalbob.app.config.service;

import com.kalbob.app.ApplicationITConfiguration;
import com.kalbob.app.config.UnitTestProfile;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ApplicationITConfiguration.class)
@UnitTestProfile
public @interface ServiceUnitTestProfile {

}
