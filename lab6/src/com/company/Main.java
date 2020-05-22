package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Main {

    public static void main(String[] args) throws UnknownHostException {
        ServerConnect serverConnect = new ServerConnect(InetAddress.getLocalHost(), 3562);
        ClientPart client = new ClientPart(serverConnect, System.in);
    }
}
