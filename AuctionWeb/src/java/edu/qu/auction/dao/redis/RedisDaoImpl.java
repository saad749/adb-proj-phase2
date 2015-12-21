/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao.redis;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

/**
 *
 * @author hiy2001
 */
@Stateless
public class RedisDaoImpl implements RedisDao {

    private Jedis getJedisConnection() {
        Jedis jedis = new Jedis("localhost");
        return jedis;
    }

    @Override
    public void bid(String userName, String itemCode, String bidValue) {
        Jedis jedis = getJedisConnection();
        
        //Insert Bid
        //sadd BidsList
        Long BidId = jedis.incr("BidsSeq");
        JsonObjectBuilder itemBuilder = Json.createObjectBuilder()
                .add("id", BidId)
                .add("UserId", userName)
                .add("ItemId", itemCode)
                .add("BidValue", bidValue + "");
        JsonObject obj = itemBuilder.build();

        jedis.sadd("BidsList", obj.toString());

        //Insert bid
        //HSET key field value
        String key = "CurrentItemBid";
        jedis.hset(key, itemCode, BidId + "");

        // Leaderboard Items
        //ZINCRBY key increment member
        String itemLeaderBoardKey = "LBoradItems";
        jedis.zincrby(itemLeaderBoardKey, 1, itemCode);

        // Leaderboard User
        //ZINCRBY key increment member
        String userLeaderBoardKey = "LBoardUsers";
        jedis.zincrby(userLeaderBoardKey, 1, userName);

    }

    public void insertItem(String itemCode, String itenDesc, String price) {
        Jedis jedis = getJedisConnection();

        Long id = jedis.incr("ItemsSeq");
        String key = "item:" + id;

        Map<String, String> map = new HashMap<String, String>();
        map.put("code", itemCode);
        map.put("desc", itenDesc);
        map.put("price", price);
        jedis.hmset(key, map);

    }

    public void insertItemsAsList(String itemCode, String itenDesc, String price) {
        Jedis jedis = getJedisConnection();
        Long id = jedis.incr("ItemsSeq");

        JsonObjectBuilder itemBuilder = Json.createObjectBuilder()
                .add("id", id)
                .add("code", itemCode)
                .add("desc", itenDesc)
                .add("price", price);
        JsonObject obj = itemBuilder.build();

        jedis.sadd("ItemsList", obj.toString());
    }

    public void clearItems() {
        Jedis jedis = getJedisConnection();
        Set<String> set = jedis.keys("item:");
        for (String key : set) {
            jedis.del(key);
        }
        jedis.set("ItemsSeq", "0");
    }

    public void deleteAllKeys() {
        Jedis jedis = getJedisConnection();
        Set<String> set = jedis.keys("**");
        for (String key : set) {
            jedis.del(key);
        }
    }

    public String getAllItemsHash() {
        Jedis jedis = getJedisConnection();

        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        long a = System.currentTimeMillis();
        Set<String> set = jedis.keys("*item:*");
        System.out.println("keys fetch:" + (System.currentTimeMillis() - a));
        for (String key : set) {
            JsonObjectBuilder itemBuilder = Json.createObjectBuilder();
            itemBuilder.add("id", key.split(":")[1]);
            long aa = System.currentTimeMillis();
            Map<String, String> map = jedis.hgetAll(key);
//            System.out.println("hash fetch:" + (System.currentTimeMillis()-aa));
            for (Map.Entry<String, String> en : map.entrySet()) {
                itemBuilder.add(en.getKey(), en.getValue());
            }
            itemsBuilder.add(itemBuilder);
        }
        long b = System.currentTimeMillis();
        System.out.println("Inner fetch:" + (b - a));
        JsonArray obj = itemsBuilder.build();
        StringWriter strw = new StringWriter();
        JsonWriter writer = Json.createWriter(strw);
        writer.writeArray(obj);
        String jsonStr = new String();
        strw.write(jsonStr);
        return strw.toString();
    }

    public String getAllItemsAsList() {
        Jedis jedis = getJedisConnection();
        Set<String> items = jedis.smembers("ItemsList");
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (String str : items) {
            builder.append(str).append(",");
        }
        builder.append("]");
        String res = builder.toString();

        return res;
    }

    public String searchItems(String match) {
        Jedis jedis = getJedisConnection();
        StringBuilder builder = new StringBuilder();
        ScanParams params = new ScanParams();
        params.match(match);
        ScanResult<String> results = jedis.sscan("ItemsList", "0", params);
        String cur = results.getStringCursor();
        while (!cur.equals("0")) {
            results = jedis.sscan("ItemsList", cur, params);
            cur = results.getStringCursor();
            for (String str : results.getResult()) {
                builder.append(str).append(",");
            }
        }
        return "[" + builder.toString() + "]";

    }
}
