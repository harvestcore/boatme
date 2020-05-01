package com.agm.boatme.data;

import java.util.Random;

public class Msg {
    String operation;
    String[] messages;

    public Msg(String operation, String[] messages) {
        this.operation = operation;
        this.messages = messages;
    }

    public String random() {
        return messages[new Random().nextInt(messages.length)];
    }
}