package com.github.simplesteph.protobuf;

import example.simple.Simple.SimpleMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SimpleMain {

    public static void main(String[] args) {

        System.out.println("Hello world!");

        // this will get a class form build folder for SimpleMessage which gets compiled
        SimpleMessage.Builder builder = SimpleMessage.newBuilder();

        // setting the fields for protobuf
        builder.setId(34)
         .setIsSimple(true)
         .setName("My simple message name");

        // repeated field
        builder.addSampleList(1)
            .addSampleList(2)
            .addSampleList(3)
            .addSampleList(4)
            .addAllSampleList(Arrays.asList(5,6,7));
        builder.setSampleList(3, 45);

        System.out.println(builder.toString());

        // extract out the simple message
        SimpleMessage message = builder.build();

        // write the protocol buffers binary to a file
        try {
            FileOutputStream outputStream = new FileOutputStream("simple_message.bin");
            message.writeTo(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // send as byte array
        // byte[] bytes = message.toByteArray();

        try {
            System.out.println("Reading from file... ");
            FileInputStream fileInputStream = new FileInputStream("simple_message.bin");
            SimpleMessage messageFromFile = SimpleMessage.parseFrom(fileInputStream);
            System.out.println(messageFromFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
