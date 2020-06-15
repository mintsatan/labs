package com.company;

import commands.Info;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Scanner;

public class ServerConnect {
    protected SocketAddress serverAddress;
    protected DatagramChannel client;
    protected byte[] last_command;

    public ServerConnect(InetAddress ADDR, int PORT) throws UnknownHostException, InterruptedException {
        this.serverAddress = new InetSocketAddress(InetAddress.getLocalHost(), PORT);
        Scanner scanner = new Scanner(System.in);
        int t = 0;
        while (true) {
            try {
                this.client = DatagramChannel.open();
                this.client.connect(this.serverAddress);
                byte[] b = {-1};
                sendData(b);
                byte[] a = new byte[10000];
                ByteBuffer buffer = ByteBuffer.wrap(a);
                buffer.clear();
                this.serverAddress = this.client.receive(buffer);
                break;
            } catch (IOException e) {
                Thread.sleep(1000);

                if (t == 60) {
                    System.out.println("Кажется сервер наелся и спит");
                    System.exit(1);
                }
                if (t == 0) {
                    System.out.println("Переподключение..");
                }
                t++;
            }
        }
    }

    public boolean isConnect() {
        return this.client.isConnected();
    }

    public void sendData(byte[] bytes) {
        this.last_command = bytes;
//        System.out.println(client.isConnected());
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        try {
//            System.out.println(Arrays.toString(bytes));
            this.client.send(buffer, serverAddress);
        } catch(IOException e) {
            System.err.println("Нет соединения с сервером для отправки данных");
            System.exit(1);
        }
        buffer.flip();
        buffer.clear();
    }

    public byte[] receiving() {
        byte[] a = new byte[10000];
        ByteBuffer buffer = ByteBuffer.wrap(a);
        buffer.clear();
        try {
            this.serverAddress = this.client.receive(buffer);
        } catch (IOException e) {
            System.err.println("Нет соединения с сервером для получения данных");
            int t = 0;
            while (true) {
                try {
                    this.client = DatagramChannel.open();
                    this.client.connect(this.serverAddress);
                    sendData(this.last_command);
                    this.serverAddress = this.client.receive(buffer);
                    return a;
                } catch (IOException ioe) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                    if (t == 60) {
                        System.out.println("Кажется сервер наелся и спит");
                        System.exit(1);
                    }
                    if (t == 0) {
                        System.out.println("Переподключение..");
                    }
                    t++;
                }
            }
        }
        buffer.flip();
        return a;
    }
}
