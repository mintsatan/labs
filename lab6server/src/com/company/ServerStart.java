package com.company;

public class ServerStart {
    public static void main(String[] args) {
        ServerPart server = new ServerPart(3562, System.getenv("envvar"));
        server.init();
    }
}
