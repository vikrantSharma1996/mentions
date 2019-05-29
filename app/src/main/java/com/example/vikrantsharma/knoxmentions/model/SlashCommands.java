package com.example.vikrantsharma.knoxmentions.model;

import java.util.Arrays;
import java.util.List;


public class SlashCommands {

    private int id;
    private String command;
    private String msg;
    private String hint;

    public SlashCommands(int id, String command, String msg, String hint ) {
        this.id = id;
        this.command = command;
        this.msg=msg;
        this.hint=hint;
    }



    public int getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public String getMsg() {
        return msg;
    }

    public String getHint() {
        return hint;
    }



    public static List<SlashCommands> SLASH = Arrays.asList(
            new SlashCommands(101, "Jira", "data from json","you selected jira"),
            new SlashCommands(102, "Zeplin", "data from json","you selected zeplin"),
            new SlashCommands(103, "Github", "data from json","you selected github")

    );
}
