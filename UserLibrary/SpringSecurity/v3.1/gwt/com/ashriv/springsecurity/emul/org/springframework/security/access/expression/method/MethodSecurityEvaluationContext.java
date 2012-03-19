package org.springframework.security.access.expression.method;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;

/**
 * Internal security-specific EvaluationContext implementation which lazily adds the
 * method parameter values as variables (with the corresponding parameter names) if
 * and when they are required.
 *
 * @author Luke Taylor
 * @since 3.0
 */
class MethodSecurityEvaluationContext extends StandardEvaluationContext {
    private static final Log logger = LogFactory.getLog(MethodSecurityEvaluationContext.class);

    private ParameterNameDiscoverer parameterNameDiscoverer;
    private final MethodInvocation mi;
    private boolean argumentsAdded;

    /**
     * Intended for testing. Don't use in practice as it creates a new parameter resolver
     * for each instance. Use the constructor which takes the resolver, as an argument thus
     * allowing for caching.
     */
    public MethodSecurityEvaluationContext(Authentication user, MethodInvocation mi) {
        this(user, mi, new LocalVariableTableParameterNameDiscoverer());
    }

    public MethodSecurityEvaluationContext(Authentication user, MethodInvocation mi,
                    ParameterNameDiscoverer parameterNameDiscoverer) {
        this.mi = mi;
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    @Override
    public Object lookupVariable(String name) {
        Object variable = super.lookupVariable(name);

        if (variable != null) {
            return variable;
        }

        if (!argumentsAdded) {
            addArgumentsAsVariables();
            argumentsAdded = true;
        }

        variable = super.lookupVariable(name);

        if (variable != null) {
            return variable;
        }

        return null;
    }

    public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    private void addArgumentsAsVariables() {
        Object[] args = mi.getArguments();

        if (args.length == 0) {
            return;
        }

        Object targetObject = mi.getThis();
        // SEC-1454
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(targetObject);

        if (targetClass == null) {
            // TODO: Spring should do this, but there's a bug in ultimateTargetClass() which returns null
            targetClass = targetObject.getClass();
        }

        Method method = AopUtils.getMostSpecificMethod(mi.getMethod(), targetClass);
        String[] paramNames = parameterNameDiscoverer.getParameterNames(method);

        if (paramNames == null) {
            logger.warn("Unable to resolve method parameter names for method: " + method
                    + ". Debug symbol information is required if you are using parameter names in expressions.");
            return;
        }

        for(int i=0; i < args.length; i++) {
            super.setVariable(paramNames[i], args[i]);
        }
    }

}
