package com.fantastic4.desktop.proxy;

import com.fantastic4.common.services.ServicesFactory;
import com.fantastic4.common.services.SuperService;
import com.fantastic4.common.services.custom.AdminService;
import com.fantastic4.common.services.custom.SensorService;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class ProxyHandler implements ServicesFactory{

    private static ProxyHandler proxyHandler;
    private SensorService sensorService;
    private AdminService adminService;
    String host = "localhost";

    private ProxyHandler() {
        try {
            System.setProperty("java.security.policy", "file:./client.policy");
            if(System.getSecurityManager() == null ){
                System.setSecurityManager( new RMISecurityManager() );
            }
            ServicesFactory servicesFactory = (ServicesFactory) Naming.lookup("//localhost:5050/fams");
            sensorService = (SensorService) servicesFactory.getService(ServicesType.SENSOR);
            adminService = (AdminService) servicesFactory.getService(ServicesType.ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ProxyHandler getInstance(){
        if (proxyHandler == null)
            proxyHandler = new ProxyHandler();
        return proxyHandler;
    }

    @Override
    public SuperService getService(ServicesType servicesType) throws Exception {
        switch (servicesType) {
            case SENSOR: return sensorService;
            case ADMIN: return adminService;
            default: return null;
        }
    }
}
