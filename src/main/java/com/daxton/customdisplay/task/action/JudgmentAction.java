package com.daxton.customdisplay.task.action;

import com.daxton.customdisplay.CustomDisplay;
import com.daxton.customdisplay.api.character.StringFind;
import com.daxton.customdisplay.task.action.list.*;
import com.daxton.customdisplay.manager.ActionManager;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class JudgmentAction {

    private CustomDisplay cd = CustomDisplay.getCustomDisplay();

    private BukkitRunnable bukkitRunnable;

    int delay = 0;
    private LivingEntity target;
    private double damageNumber = 0;

    public JudgmentAction(){

    }


    public void execute(Player player, LivingEntity target, String firstString, double damageNumber,String taskID){
        this.target = target;
        this.damageNumber = damageNumber;
        bukkitRun(player,firstString,taskID);
    }

    /**只有玩家**/
    public void execute(Player player, String firstString,String taskID){
        bukkitRun(player,firstString,taskID);
    }

    public void executeOneTwo(Player player,LivingEntity target, String firstString,String taskID){
        this.target = target;
        bukkitRun(player,firstString,taskID);
    }

    public void bukkitRun(Player player,String firstString,String taskID){
        if (firstString.toLowerCase().contains("delay ")) {
            String[] slt = firstString.split(" ");
            if (slt.length == 2) {
                delay = delay + Integer.valueOf(slt[1]);
            }
        }
        bukkitRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                /**動作第一個關鍵字**/
                String judgMent = new StringFind().getAction(firstString);

                /**Action的相關判斷**/
                if (judgMent.toLowerCase().contains("action")) {
                    if(ActionManager.getJudgment_Action_Map().get(taskID) == null){
                        ActionManager.getJudgment_Action_Map().put(taskID,new Action());
                    }
                    if(ActionManager.getJudgment_Action_Map().get(taskID) != null){
                        if(target == null){
                            ActionManager.getJudgment_Action_Map().get(taskID).setAction(player,firstString,taskID);
                        }else if(damageNumber == 0){
                            ActionManager.getJudgment_Action_Map().get(taskID).setAction(player,target,firstString,taskID);
                        }else {
                            ActionManager.getJudgment_Action_Map().get(taskID).setAction(player,target,firstString,damageNumber,taskID);
                        }
                    }

                }

                /**HolographicDisplays的相關判斷**/
                if(judgMent.toLowerCase().contains("createhd") || judgMent.toLowerCase().contains("addlinehd") || judgMent.toLowerCase().contains("removelinehd") || judgMent.toLowerCase().contains("teleporthd") || judgMent.toLowerCase().contains("deletehd")){
                    if(ActionManager.getJudgment_Holographic_Map().get(taskID) == null){
                        ActionManager.getJudgment_Holographic_Map().put(taskID,new Holographic());
                    }
                    if(ActionManager.getJudgment_Holographic_Map().get(taskID) != null){
                        ActionManager.getJudgment_Holographic_Map().get(taskID).setHD(player,target,firstString,damageNumber,taskID);
                    }
                }
                /**Loop的相關判斷**/
                if(judgMent.toLowerCase().contains("loop")){
                        if(ActionManager.getJudgment_Loop_Map().get(taskID) == null){
                            ActionManager.getJudgment_Loop_Map().put(taskID,new Loop());
                        }
                        if(ActionManager.getJudgment_Loop_Map().get(taskID) != null){
                            if(target == null){
                                ActionManager.getJudgment_Loop_Map().get(taskID).onLoop(player,firstString,taskID);
                            }else {
                                ActionManager.getJudgment_Loop_Map().get(taskID).onLoop(player,target,firstString,damageNumber,taskID);
                            }
                        }
                }

                /**Title的相關判斷**/
                if(judgMent.toLowerCase().contains("title")){
                    new Title(player,firstString).sendTitle();
                }
                /**Sound的相關判斷**/
                if (judgMent.toLowerCase().contains("sound")) {
                    new Sound(player, firstString).playSound();
                }
                /**ActionBar的相關判斷**/
                if(judgMent.toLowerCase().contains("actionbar")){
                    new ActionBar(player,firstString).sendActionBar();
                }

                /**BossBar的相關判斷**/
                if(judgMent.toLowerCase().contains("boosbar")){
                    if(ActionManager.getJudgment_BossBar_Map().get(taskID) == null){
                        ActionManager.getJudgment_BossBar_Map().put(taskID,new SendBossBar());
                    }
                    if(ActionManager.getJudgment_BossBar_Map().get(taskID) != null){
                        ActionManager.getJudgment_BossBar_Map().get(taskID).set(player,target,firstString,taskID);
                    }
                }

                /**Particle的相關判斷**/
                if(judgMent.toLowerCase().contains("particle")){
                    if(ActionManager.getParticles_Map().get(taskID) == null){
                        ActionManager.getParticles_Map().put(taskID,new SendParticles());
                    }
                    if(ActionManager.getParticles_Map().get(taskID) != null){
                        if(target == null){
                            ActionManager.getParticles_Map().get(taskID).setParticles(player,firstString,taskID);
                        }else {
                            ActionManager.getParticles_Map().get(taskID).setParticles(player,target,firstString,taskID);
                        }
                    }
                }

                /**Message的相關判斷**/
                if(judgMent.toLowerCase().contains("message")){
                    if(ActionManager.getJudgment_Message_Map().get(taskID) == null){
                        ActionManager.getJudgment_Message_Map().put(taskID,new Message());
                    }
                    if(ActionManager.getJudgment_Message_Map().get(taskID) != null){
                        ActionManager.getJudgment_Message_Map().get(taskID).setMessage(player,firstString);
                        ActionManager.getJudgment_Message_Map().get(taskID).sendMessage();
                    }


                }

            }
        };
        bukkitRunnable.runTaskLater(cd, delay);
    }

    public BukkitRunnable getBukkitRunnable() {
        return bukkitRunnable;
    }
}
