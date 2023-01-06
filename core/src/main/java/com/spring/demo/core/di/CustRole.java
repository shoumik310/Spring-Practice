package com.spring.demo.core.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // If there are multiple beans this one takes precedence
public class CustRole implements RoleInterface {

}
