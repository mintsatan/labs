package com.company;

import commands.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ServerMaker {
    protected SocketAddress socketAddress;
    protected DatagramChannel server;

    public ServerMaker(int PORT) {
        this.socketAddress = new InetSocketAddress(PORT);
        try {
            this.server = DatagramChannel.open().bind(socketAddress);
            System.out.println("Ожидаю подключения");
        } catch (IOException e) {
            System.err.println("Клиент не подключен к серверу");
            System.exit(1);
        }
    }

    public byte[] receiving() {
        byte[] a = new byte[10000];
        ByteBuffer buffer;
        Charset charset = StandardCharsets.UTF_8;
        buffer = ByteBuffer.wrap(a);
        try {
            buffer.clear();
            this.socketAddress = this.server.receive(buffer);
//            System.out.println((charset.decode(buffer)));
            return a;
        } catch (IOException e) {
            System.err.println("Клиент не подключен к серверу для получения данных");
        }
        buffer.flip();
        buffer.clear();
//        System.out.println(a);
        return null;
    }

    public void sendData(byte[] bytes) {
        ByteBuffer buffer;
        buffer = ByteBuffer.wrap(bytes);
        try {
            this.server.send(buffer, socketAddress);
        } catch (IOException e) {
            System.err.println("Клиент не подключен к серверу для отправки данных");
        }
        buffer.flip();
        buffer.clear();
    }
}
