package com.company;

public class ServerStart {
    public static void main(String[] args) {
        ServerPart server = new ServerPart(8000, System.getenv("envvar"));
        server.init();
    }
}
