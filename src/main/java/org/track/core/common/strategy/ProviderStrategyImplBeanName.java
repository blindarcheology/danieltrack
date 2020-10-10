package org.track.core.common.strategy;

public enum ProviderStrategyImplBeanName {

    ONE_PROVIDER_STRATEGY("oneProviderStrategy");

    private String beanName;

    ProviderStrategyImplBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
