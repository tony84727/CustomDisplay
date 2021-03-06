package com.daxton.customdisplay.task.action.list;

import com.daxton.customdisplay.CustomDisplay;
import com.daxton.customdisplay.api.character.StringConversion;
import com.daxton.customdisplay.api.character.StringFind;
import org.bukkit.entity.Player;

import java.util.List;

public class Title {

    private CustomDisplay cd = CustomDisplay.getCustomDisplay();

    private Player player;

    private String title = "";

    private String subTitle = "";

    private int fadeIn = 1;

    private int stay = 1;

    private int fadeOut = 1;


    public Title(Player player, String firstString){
        this.player = player;
        List<String> stringList = new StringFind().getStringList(firstString);
        for(String strings : stringList){
            if(strings.contains("fadeIn=")){
                String[] stl = strings.split("=");
                setFadeIn(Integer.valueOf(stl[1]));
            }
            if(strings.contains("stay=")){
                String[] stl = strings.split("=");
                setStay(Integer.valueOf(stl[1]));
            }
            if(strings.contains("fadeOut=")){
                String[] stl = strings.split("=");
                setFadeOut(Integer.valueOf(stl[1]));
            }
        }

        List<String> stringList2 = new StringFind().getStringMessageList(firstString);
        for(String string : stringList2){
            if(string.toLowerCase().contains("title=")){
                String[] strings = string.split("=");
                if(strings.length == 2){
                    setTitle(strings[1]);
                }
            }

            if(string.contains("subtitle=") || string.contains("subt=")){
                String[] strings = string.split("=");
                if(strings.length == 2){
                    setSubTitle(strings[1]);
                }

            }

        }


    }



    public void sendTitle(){
        player.sendTitle(title,subTitle,fadeIn,stay,fadeOut);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }
}
