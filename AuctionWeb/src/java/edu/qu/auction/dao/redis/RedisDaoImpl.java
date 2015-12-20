/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.dao.redis;

import javax.ejb.Stateless;
import redis.clients.jedis.Jedis;

/**
 *
 * @author hiy2001
 */
@Stateless
public class RedisDaoImpl implements RedisDao {
    
    @Override
    public void bid(String userName, String itemCode, double bidValue){
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        
        //Insert bid
        //HSET key field value
        String key = "itembid:"+itemCode;
        jedis.hset(key, userName, bidValue+"");
        
        // Leaderboard Items
        //ZINCRBY key increment member
        String itemLeaderBoardKey = "ldbItems";
        jedis.zincrby(itemLeaderBoardKey,1,itemCode);
        
        // Leaderboard User
        //ZINCRBY key increment member
        String userLeaderBoardKey = "ldbUser";
        jedis.zincrby(userLeaderBoardKey,1,userName);
    }
}
