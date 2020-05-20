package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class ServerConnect {
    protected SocketAddress serverAddress;
    protected DatagramChannel client;

    public ServerConnect(InetAddress ADDR, int PORT) {
        try {
            this.client = DatagramChannel.open().bind(null);
        } catch (IOException e) {
            System.err.println("Нет соединения с сервером");
            System.exit(1);
        }

        this.serverAddress = new InetSocketAddress(ADDR, PORT);

    }

    public void sendData(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        try {
            System.out.println(Arrays.toString(bytes));
            this.client.send(buffer, serverAddress);
        } catch(IOException e) {
            System.err.println("Нет соединения с сервером");
            System.exit(1);
        }
        buffer.clear();
    }

    public byte[] receiving() {
        byte[] a = new byte[10000];
        ByteBuffer buffer = ByteBuffer.wrap(a);
        buffer.clear();
        try {
            this.serverAddress = this.client.receive(buffer);
        } catch (IOException e) {
            System.err.println("Клиент не подключен к серверу");
            System.exit(1);
        }

        return a;
    }
}
