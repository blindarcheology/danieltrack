package org.track.core.common.strategy;


import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StrategyConfig {

    private final Map<String, ProviderStrategy> strategyMap = new ConcurrentHashMap<>();

    /**
     * k : beanName
     * v : ProviderStrategy implements bean
     *
     * @param strategyMap
     */
    @Autowired
    public void put(Map<String, ProviderStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    /**
     * get(beanName)
     *
     * @param os
     * @return
     */
    public ProviderStrategy get(ProviderStrategyImplBeanName os) {
        Preconditions.checkArgument(!StringUtils.isEmpty(os), "不允许输入空字符串");
        return strategyMap.get(os.getBeanName());
    }


}
