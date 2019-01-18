package com.a1000geeks.test.data;

import com.a1000geeks.test.data.network.ServiceNetwork;

import javax.inject.Inject;

public class RepositoryManagerImpl implements RepositoryManager {

    private ServiceNetwork serviceNetwork;


    @Inject
    RepositoryManagerImpl(ServiceNetwork serviceNetwork) {
        this.serviceNetwork = serviceNetwork;
    }

    @Override
    public ServiceNetwork getServiceNetwork() {
        return serviceNetwork;
    }
}
