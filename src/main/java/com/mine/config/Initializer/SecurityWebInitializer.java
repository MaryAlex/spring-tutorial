package com.mine.config.Initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
// Registers the DelegatingFilterProxy to use the springSecurityFilterChain before any other registered Filter.
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
}
