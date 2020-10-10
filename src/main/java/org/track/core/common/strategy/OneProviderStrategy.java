package org.track.core.common.strategy;

import org.springframework.stereotype.Component;

@Component
public class OneProviderStrategy implements ProviderStrategy {
    @Override
    public void println() {
        System.out.println("ONE");
    }
}
