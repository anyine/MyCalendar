package com.jin.calendar.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jin.calendar.bo.RoomEvent;
import com.jin.calendar.common.CommonConstant;
import com.jin.calendar.model.RoomSchedule;

public class RoomScheduleController extends Controller {

    public void getDurationEvent() {
        List<RoomSchedule> list = RoomSchedule.dao.getDurationEventsByRoomId(
                getParaToInt("roomId"), getParaToLong("start"),
                getParaToLong("end"), getParaToInt("userid"));

        List<RoomSchedule> list1 = RoomSchedule.dao.getDurationEventsByRoomIdS(
                getParaToInt("roomId"), getParaToLong("start"),
                getParaToLong("end"));

        //list 合并去重
        list.removeAll(list1);
        list.addAll(list1);

        List<RoomEvent> events = new ArrayList<RoomEvent>();
        for (RoomSchedule roomSchedule : list) {
            RoomEvent event = new RoomEvent(roomSchedule.getInt("id"),
                    roomSchedule.getStr("subject"),
                    roomSchedule.getTimestamp("start"),
                    roomSchedule.getTimestamp("end"),
                    roomSchedule.getStr("username"),
                    roomSchedule.getStr("roomname"),
                    roomSchedule.getStr("email"),
                    roomSchedule.getStr("room_sh_status"),
                    false,
                    roomSchedule.getStr("room_idea"));
            //根据事件的日期和审批状态给事件添加颜色
            if (roomSchedule.getTimestamp("start").before(new Date())) {
                event.setColor(CommonConstant.COLOR_FOR_PAST_EVENT);
            } else if (roomSchedule.getInt("userid").equals(getSessionAttr("userId"))) {
                event.setColor(CommonConstant.COLOR_FOR_OWN_FUTURE_EVENT);
                if ("1".equals(roomSchedule.getStr("room_sh_status"))) {
                    event.setColor(CommonConstant.COLOR_FOR_FUTURE_EVENT_ALLOW);
                } else if ("2".equals(roomSchedule.getStr("room_sh_status"))) {
                    event.setColor(CommonConstant.COLOR_FOR_FUTURE_EVENT_NOT_ALLOW);
                }
                event.setEditable(true);
            } else {
                //event.setColor(CommonConstant.COLOR_FOR_FUTURE_EVENT);
                if ("1".equals(roomSchedule.getStr("room_sh_status"))) {
                    event.setColor(CommonConstant.COLOR_FOR_FUTURE_EVENT_ALLOW);
                } else if ("2".equals(roomSchedule.getStr("room_sh_status"))) {
                    event.setColor(CommonConstant.COLOR_FOR_FUTURE_EVENT_NOT_ALLOW);
                }
            }
            events.add(event);
        }

        renderJson(events);
    }

    /**
     * 更新事件的开始结束时间并更改状态
     */
    public void updateRoomEvent() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("isSuccess", true);
        Date start = new Date(getParaToLong("start"));
        Date end = new Date(getParaToLong("end"));
        if (start.before(new Date())) {
            returnMap.put("isSuccess", false);
            returnMap.put("msg", "调整后的开始时间已过期!");
        } else if (!RoomSchedule.dao.isLegalEventPast(getParaToLong("start") / 1000, getParaToLong("end") / 1000, getParaToInt("roomId"), getParaToInt("id"), getParaToInt("userid"))) {
            returnMap.put("isSuccess", false);
            returnMap.put("msg", "与其他会议时间有冲突！");
        } else {
            RoomSchedule roomSchedule = RoomSchedule.dao.findById(getParaToInt("id"));
            roomSchedule.set("start", start);
            roomSchedule.set("end", end);
            roomSchedule.set("room_sh_status", 0);
            roomSchedule.set("room_idea", "");
            roomSchedule.set("check_userid", "");
            roomSchedule.update();
        }
        renderJson(returnMap);
    }

    public void addRoomEvent() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("isSuccess", true);
        Date start = new Date(getParaToLong("start"));
        Date end = new Date(getParaToLong("end"));
        String title = getPara("title");
        int roomId = getParaToInt("roomId");
        if (start.before(new Date())) {
            returnMap.put("isSuccess", false);
            returnMap.put("msg", "开始时间已过期!");
        } else if (!RoomSchedule.dao.isLegalEventPast(getParaToLong("start") / 1000, getParaToLong("end") / 1000, roomId, -100, getParaToInt("userid"))) {
            returnMap.put("isSuccess", false);
            returnMap.put("msg", "与其他会议时间有冲突！");
        } else {
            new RoomSchedule().set("start", start)
                    .set("end", end)
                    .set("subject", title)
                    .set("userid", getSessionAttr("userId"))
                    .set("roomid", roomId)
                    .set("room_sh_status", 0)
                    .set("create_date", new Date()).save();
        }
        renderJson(returnMap);
    }

}
