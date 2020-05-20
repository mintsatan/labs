package com.company;

import commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class ServerMaker {
    protected SocketAddress socketAddress;
    protected DatagramChannel server;

    public ServerMaker(int PORT) {
        this.socketAddress = new InetSocketAddress(PORT);
        try {
            this.server = DatagramChannel.open().bind(socketAddress);
        } catch (IOException e) {
            System.err.println("Клиент не подключен к серверу");
            System.exit(1);
        }
    }

    public Command receiving() {
        byte[] a = new byte[10000];
        ByteBuffer buffer;
        buffer = ByteBuffer.wrap(a);
        try {
            this.socketAddress = this.server.receive(buffer);
            System.out.println(Arrays.toString(a));
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Command) objectInputStream.readObject();
        } catch (IOException e) {
            System.err.println("Клиент не подключен к серверу");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("Это не команда...");
        }
        buffer.clear();
        System.out.println(a);
        return null;
    }

    public void sendData(byte[] bytes) {
        ByteBuffer buffer;
        buffer = ByteBuffer.wrap(bytes);
        try {
            this.server.send(buffer, socketAddress);
        } catch (IOException e) {
            System.err.println("Клиент не подключен к серверу");
            System.exit(1);
        }
        buffer.clear();
    }
}
