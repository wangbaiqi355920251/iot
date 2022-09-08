package io.iti.modules.iot.socket.test;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MockUdpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            // 1.定义服务器的地址、端口号、数据
            InetAddress address = InetAddress.getByName("localhost");
            int port = 2049;
            // 生成模拟数据
            String data_s = "+ECD,460040314201865,";
            data_s += String.valueOf(System.currentTimeMillis() / 1000) + ",";
            data_s += String.format("%.2f", Math.random() * 30) + ",";
            data_s += String.format("%.2f", Math.random() * 100) + ",";
            data_s += String.format("%.2f", Math.random() * 6000);
            byte[] data = data_s.getBytes();
            // 2.创建数据报，包含发送的数据信息
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            // 3.创建DatagramSocket对象
            DatagramSocket socket = new DatagramSocket();
            // 4.向服务器端发送数据报
            socket.send(packet);
            System.out.println(data_s);
            socket.close();
            Thread.sleep(1000 * 1 * 1);
        }
    }
}
