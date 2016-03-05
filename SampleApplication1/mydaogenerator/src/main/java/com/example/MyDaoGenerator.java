package com.example;

import javax.xml.stream.events.EndDocument;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "greendao");

        Entity mail = schema.addEntity("Mail");
        mail.addIdProperty();
        mail.addStringProperty("title");
        mail.addStringProperty("userid");
        mail.addStringProperty("password");

        Entity sns = schema.addEntity("SNS");
        sns.addIdProperty();
        sns.addStringProperty("title");
        sns.addStringProperty("userid");
        sns.addStringProperty("password");

        Entity web = schema.addEntity("Web");
        web.addIdProperty();
        web.addStringProperty("title");
        web.addStringProperty("userid");
        web.addStringProperty("password");

        Entity computer = schema.addEntity("Computer");
        computer.addIdProperty();
        computer.addStringProperty("title");
        computer.addStringProperty("userid");
        computer.addStringProperty("password");

        Entity shopping = schema.addEntity("Shopping");
        shopping.addIdProperty();
        shopping.addStringProperty("title");
        shopping.addStringProperty("userid");
        shopping.addStringProperty("password");

        Entity credit = schema.addEntity("Credit");
        credit.addIdProperty();
        credit.addStringProperty("title");
        credit.addStringProperty("userid");
        credit.addStringProperty("password");

        Entity bank = schema.addEntity("Bank");
        bank.addIdProperty();
        bank.addStringProperty("title");
        bank.addStringProperty("userid");
        bank.addStringProperty("password");

        Entity other = schema.addEntity("Other");
        other.addIdProperty();
        other.addStringProperty("title");
        other.addStringProperty("userid");
        other.addStringProperty("password");

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
