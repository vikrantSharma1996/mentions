package com.example.vikrantsharma.knoxmentions;

import java.util.Arrays;
import java.util.List;


public class SlahsCommands {

    private int id;
    private String command;
    private String msg;
    private String hint;

    public SlahsCommands(int id, String command,String msg,String hint ) {
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



    public static List<SlahsCommands> SLASH = Arrays.asList(
            new SlahsCommands(101, "Jira", "data from json","you selected jira"),
            new SlahsCommands(102, "Zeplin", "data from json","you selected zeplin"),
            new SlahsCommands(103, "Github", "data from json","you selected github")

    );
}
