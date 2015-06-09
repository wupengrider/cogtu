package com.cogtu.realtime.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lenovo on 2015/6/6.
 */
public class JsonTest {

    public static void main(String[] args) {
        testJson();
    }

    public static void testJson() {
        String jsonMessage = "{\"语文\":\"88\",\"数学\":\"78\",\"计算机\":\"99\"}";
        String value1 = null;
        try {
            //将字符串转换成jsonObject对象
            // JSONObject myJsonObject = new JSONObject(jsonMessage);
            JSONObject myJsonObject = new JSONObject(jsonMessage);
            //获取对应的值
            value1 = myJsonObject.getString("数学");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("value1=" + value1);

//JSONArray

        jsonMessage = "[{'num':'成绩', '外语':88, '历史':65, '地理':99, 'object':{'aaa':'1111','bbb':'2222','cccc':'3333'}}," +
                "{'num':'兴趣', '外语':28, '历史':45, '地理':19, 'object':{'aaa':'11a11','bbb':'2222','cccc':'3333'}}," +
                "{'num':'爱好', '外语':48, '历史':62, '地理':39, 'object':{'aaa':'11c11','bbb':'2222','cccc':'3333'}}]";
        JSONArray myJsonArray;
        try {
            myJsonArray = new JSONArray(jsonMessage);

            for (int i = 0; i < myJsonArray.length(); i++) {
                //获取每一个JsonObject对象
                JSONObject myjObject = myJsonArray.getJSONObject(i);

                //获取每一个对象中的值
                String numString = myjObject.getString("num");
                int englishScore = myjObject.getInt("外语");
                int historyScore = myjObject.getInt("历史");
                int geographyScore = myjObject.getInt("地理");
                //获取数组中对象的对象
                JSONObject myjObject2 = myjObject.getJSONObject("object");
                String aaaString = myjObject2.getString("aaa");
                System.out.println("aaaString=" + aaaString);

                System.out.println("numString=" + numString);
                System.out.println("englishScore=" + englishScore);
                System.out.println("historyScore=" + historyScore);
                System.out.println("geographyScore=" + geographyScore);
            }
        } catch (JSONException e) {
        }
    }

}
