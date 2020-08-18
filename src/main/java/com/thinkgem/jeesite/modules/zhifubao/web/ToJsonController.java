package com.thinkgem.jeesite.modules.zhifubao.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.zhifubao.entity.Message;

import com.thinkgem.jeesite.modules.zhifubao.entity.NumberInfo;
import com.thinkgem.jeesite.modules.zhifubao.service.NumberInfoService;


import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
public class ToJsonController {
    @Autowired
    private NumberInfoService numberInfoService;
    /*
   获取数据库数据（list）转换成json格式
    */
    @RequestMapping(value = "TOJSON")
    @ResponseBody
    public String TOJSON() {
        Message message = new Message();
        HashMap<String, Message> map = new HashMap();

        try {
            List<NumberInfo> numberInfoLists = numberInfoService.getEntity();//获取List
            if (numberInfoLists != null) {
                JSONArray jsonArray = new JSONArray();
                for (NumberInfo numberInfoList : numberInfoLists) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", numberInfoList.getId());
                    jo.put("userId", numberInfoList.getUserId());
                    jo.put("phoneNumber",numberInfoList.getPhoneNumber());
                    jo.put("psptId", numberInfoList.getPsptId());
                    jo.put("orderTime", numberInfoList.getOrderTime());
                    jsonArray.add(jo);
                }

                message.setStatus("1");
                message.setMsgContent("Data obtained successfully");
                message.setData(jsonArray);
                map.put("data", message);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(message);
                System.out.println(jsonObject.toString());
                return jsonObject.toString();
            }else {
                message.setStatus("2");
                message.setMsgContent("Failed to get data, please contact administrator");
                message.setData(null);
                map.put("data", message);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(message);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            message.setStatus("2");
            message.setMsgContent("Failed to get data, please contact administrator");
            message.setData(null);
            map.put("data", message);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(message);
            e.printStackTrace();
            return jsonObject.toString();
        }
    }


    @RequestMapping(value = "tojsonByTime"  , method = RequestMethod.GET)
    @ResponseBody

    public String tojsonByTime( @RequestParam(value = "time") String time,  @RequestParam(value = "flag") String flag){
        Message message = new Message();
        HashMap<String, Message> map = new HashMap();
        HashMap<String,Object> maps = new HashMap<String, Object>();
        try {

            maps.put("orderTime",time);
            maps.put("flag",flag);
            List<NumberInfo> numberInfoLists = numberInfoService.getEntyByTime(maps);//获取List
            if (numberInfoLists != null && numberInfoLists.size()>0) {
                JSONArray jsonArray = new JSONArray();
                for (NumberInfo numberInfoList : numberInfoLists) {
                    JSONObject jo = new JSONObject();
                    jo.put("id", numberInfoList.getId());
                    jo.put("userId", numberInfoList.getUserId());
                    jo.put("phoneNumber",numberInfoList.getPhoneNumber());
                    jo.put("psptId", numberInfoList.getPsptId());
                    jo.put("orderTime", numberInfoList.getOrderTime());
                    jsonArray.add(jo);
                }

                message.setStatus("1");
                message.setMsgContent("Data obtained successfully");
                message.setData(jsonArray);
                map.put("data", message);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(message);
                System.out.println(jsonObject.toString());
                return jsonObject.toString();
            }else {
                message.setStatus("2");
                message.setMsgContent("Failed to get data, please contact administrator");
                message.setData(null);
                map.put("data", message);
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(message);
                return jsonObject.toString();
            }
        } catch (Exception e) {
            message.setStatus("2");
            message.setMsgContent("Failed to get data, please contact administrator");
            message.setData(null);
            map.put("data", message);
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(message);
            e.printStackTrace();
            return jsonObject.toString();
        }


    }


}
