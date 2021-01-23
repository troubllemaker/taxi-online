package com.csw.servicepassengeruser.gray;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * TODO (输入本类描述)
 *
 * @author csw
 * @version 1.0
 * @date 2021/1/22 15:28
 */
public class IRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        Server server = null;
        ILoadBalancer loadBalancer = getLoadBalancer();
        while (server == null){
            return getLoadBalancer(loadBalancer);
        }
        return null;
    }

    private Server getLoadBalancer(ILoadBalancer loadBalancer) {
        Map<String, String> mapVersion = (Map<String, String>) ParameterContext.get();
        String version = "";
        if(mapVersion != null){
            version = mapVersion.get("version");
        }
        List<Server> reachableServers = loadBalancer.getReachableServers();
        for (Server server:reachableServers) {
            Map<String, String> metadata = ((DiscoveryEnabledServer) (server)).getInstanceInfo().getMetadata();
            if(!CollectionUtils.isEmpty(metadata)){
                String loadBalancerVersion = metadata.get("version");
                if(version.equalsIgnoreCase(loadBalancerVersion)){
                    return server;
                }
            }
        }
        return null;
    }
}
