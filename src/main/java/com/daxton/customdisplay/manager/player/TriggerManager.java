package com.daxton.customdisplay.manager.player;

import com.daxton.customdisplay.task.action.JudgmentAction;
import com.daxton.customdisplay.task.action.list.Holographic;
import com.daxton.customdisplay.task.action.list.HolographicNew;
import com.daxton.customdisplay.task.action.list.Loop;
import com.daxton.customdisplay.task.player.OnAttack;
import com.daxton.customdisplay.task.player.OnTimer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TriggerManager {

    private static Map<String, OnTimer> onTimerMap = new HashMap<>();

    private static Map<UUID, List<String>> onTimerNameMap = new HashMap<>();


    /**AttackListener->onAttack**/
    private static Map<String, OnAttack> AttackListenerTaskMap = new HashMap<>();
    /**onAttack->JudgmentAction**/
    private static Map<String, JudgmentAction> onAttackTaskMap = new HashMap<>();
    /**JudgmentAction->HolographicNew**/
    private static Map<String, HolographicNew> holographicTaskMap = new HashMap<>();
    /**JudgmentAction->Loop**/
    private static Map<String, Loop> judgmentActionTaskMap = new HashMap<>();
    /**Loop->JudgmentAction**/
    private static Map<String, JudgmentAction> loopTaskMap = new HashMap<>();




    public static Map<String, OnTimer> getOnTimerMap() {
        return onTimerMap;
    }

    public static Map<UUID, List<String>> getOnTimerNameMap() {
        return onTimerNameMap;
    }

    /**AttackListener->onAttack**/
    public static Map<String, OnAttack> getAttackListenerTaskMap() {
        return AttackListenerTaskMap;
    }
    /**onAttack->JudgmentAction**/
    public static Map<String, JudgmentAction> getOnAttackTaskMap() {
        return onAttackTaskMap;
    }
    /**JudgmentAction->HolographicNew**/
    public static Map<String, HolographicNew> getHolographicTaskMap() {
        return holographicTaskMap;
    }
    /**Loop->JudgmentAction**/
    public static Map<String, JudgmentAction> getLoopTaskMap() {
        return loopTaskMap;
    }
    /**JudgmentAction->Loop**/
    public static Map<String, Loop> getJudgmentActionTaskMap() {
        return judgmentActionTaskMap;
    }
}
