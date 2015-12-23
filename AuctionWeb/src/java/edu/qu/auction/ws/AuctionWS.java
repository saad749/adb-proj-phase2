/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.qu.auction.ws;

import edu.qu.auction.dao.BidsDao;
import edu.qu.auction.dao.ItemsDao;
import edu.qu.auction.dao.UsersDao;
import edu.qu.auction.dao.redis.RedisDao;
import edu.qu.auction.domain.Bids;
import edu.qu.auction.domain.Items;
import edu.qu.auction.domain.Users;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.json.JSONObject;

@Path("/ws")
public class AuctionWS {

    @EJB
    private ItemsDao itemsFacade;
    @EJB
    UsersDao usersDao;
    @EJB
    BidsDao bidsDao;

    @EJB
    RedisDao redisDao;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String methodTest() {
        List<Items> list = itemsFacade.findAll();
        int x = list.size();
        return "Hello World " + x;
    }

    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Items> getITems() {
        long a = System.currentTimeMillis();
        List<Items> items = itemsFacade.findAll();
        long b = System.currentTimeMillis();
        System.out.println("Time taken to retrieve all items in MySQL:" + (b - a));
        return items;
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    public List<Users> getUsers() {
        return usersDao.findAll();
    }

    @GET
    @Path("/bids")
    @Produces(MediaType.APPLICATION_XML)
    public List<Bids> getBids() {
        return bidsDao.findAll();
    }

    @POST
    public Response postBid(
            @FormParam("userName") String userName,
            @FormParam("itemId") String itemId,
            @FormParam("value") String value
    ) {

        Users user = usersDao.findByUserName(userName);
        Items item = itemsFacade.find(new Integer(itemId));

        Bids bid = new Bids();
        bid.setItemId(item);
        bid.setUserId(user);
        bid.setBidValue(new Double(value));

        bidsDao.bid(bid);

        System.out.println("------->post" + userName);
        return Response.ok().build();
    }

    @GET
    @Path("/simulateBidsRedis")
    @Produces(MediaType.TEXT_PLAIN)
    public String simulateBidsRedis() {
        int num = 1000;
        long a = System.currentTimeMillis();
        Random rand = new Random();
        for (int x = 1; x <= num; x++) {

            String userName = "userName" + (rand.nextInt(10) + 1);
            String itemCode = "itemCode" + (rand.nextInt(10) + 1);
            String bidValue = "" + (rand.nextInt(1000) + 1);

            redisDao.bid(userName, itemCode, bidValue);
        }
        long b = System.currentTimeMillis();
        return "Time taken to inseted " + num + " items in Redis:" + (b - a);
    }

    @GET
    @Path("/simulateBidsMySQL")
    @Produces(MediaType.TEXT_PLAIN)
    public String simulateBidsMySQL() {
        int num = 1000;
        long a = System.currentTimeMillis();
        Random rand = new Random();
        for (int x = 1; x <= num; x++) {

            String userName = "userName" + (rand.nextInt(10) + 1);
            String itemCode = "itemCode" + (rand.nextInt(10) + 1);
            String bidValue = "" +  (rand.nextInt(1000) + 1);

            Users user = usersDao.findByUserName(userName);
            List<Items> items = itemsFacade.getItemsByCode(itemCode);
            Items item = items.get(0);
            
            Bids bid = new Bids();
            bid.setItemId(item);
            bid.setUserId(user);
            bid.setBidValue(new Double(bidValue));

            bidsDao.bid(bid);
        }
        long b = System.currentTimeMillis();
        return "Time taken to inseted " + num + " items in Redis:" + (b - a);
    }

    @POST
    @Path("/bidRedis")
    public Response postBidRedis(
            @FormParam("userName") String userName,
            @FormParam("itemCode") String itemCode,
            @FormParam("value") String value
    ) {

        redisDao.bid(userName, itemCode, value);

        System.out.println("------->post redis" + userName);
        return Response.ok().build();
    }

    @GET
    @Path("/createItemsRedis")
    @Produces(MediaType.TEXT_PLAIN)
    public String createItemsRedis() {
        int num = 1000;
        long a = System.currentTimeMillis();
        Random rand = new Random();
        for (int x = 1; x <= num; x++) {
            String code = "itemCode" + x;
            String desc = "desc" + x;
            String price = "" + (rand.nextInt(1000) + 1);

            redisDao.insertItemsHash(code, desc, price);
//            redisDao.insertItemsAsList(code, desc, price);
        }
        long b = System.currentTimeMillis();
        return "Time taken to inseted " + num + " items in Redis:" + (b - a);
    }

    @GET
    @Path("/createUsersRedis")
    @Produces(MediaType.TEXT_PLAIN)
    public String createUsersRedis() {
        int num = 1000;

        long a = System.currentTimeMillis();
        Random rand = new Random();
        for (int x = 1; x <= num; x++) {
            String userName = "userName" + x;
            String firstName = "firstName" + x;
            String lastName = "desc" + x;
            String email = userName + "@qu.edu.qa";
            String contactNumber = "66107777";

            redisDao.insertUsersHash(userName, firstName, lastName, email, contactNumber);
        }
        long b = System.currentTimeMillis();
        return "Time taken to insert " + num + " User in Redis:" + (b - a);
    }

    @GET
    @Path("/createUsersMySQL")
    @Produces(MediaType.TEXT_PLAIN)
    public String createUsersMySQL() {
        int num = 1000;

        long a = System.currentTimeMillis();
        Random rand = new Random();
        for (int x = 1; x <= num; x++) {
            String userName = "userName" + x;
            String firstName = "firstName" + x;
            String lastName = "desc" + x;
            String email = userName + "@qu.edu.qa";
            String contactNumber = "66107777";
            Users user = new Users();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setContactNumber(contactNumber);
            user.setUserName(userName);
            user.setId(null);
            usersDao.create(user);
        }
        long b = System.currentTimeMillis();
        return "Time taken to insert " + num + " User in MySQL:" + (b - a);
    }

    @GET
    @Path("/createItemsMySQL")
    @Produces(MediaType.TEXT_PLAIN)
    public String createItemsMySQL() {

        int num = 1000;
        long a = System.currentTimeMillis();
        Random rand = new Random();
        for (int x = 1; x <= num; x++) {
            Items item = new Items();
            String code = "itemCode" + x;
            String desc = "desc" + x;
            String price = "" + (rand.nextInt(1000) + 1);

            item.setItemCode(code);
            item.setPrice(price);
            item.setShortDesc(desc);
            itemsFacade.create(item);
        }
        long b = System.currentTimeMillis();
        return "Time taken to inseted " + num + " items in MySQL:" + (b - a);
    }

    @GET
    @Path("/getAllItemsRedis")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllItemRedis() {
        long a = System.currentTimeMillis();
        String itemsJson =  redisDao.getAllItemsHash();
//        String itemsJson = redisDao.getAllItemsAsList();
        long b = System.currentTimeMillis();
        System.out.println("Time taken to retrieve all items from Redis:" + (b - a));
        return itemsJson;
    }

    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchItemsRedis(@FormParam("pattern") String pattern) {
        Long a = System.currentTimeMillis();
        String itemsJson = redisDao.searchItems(pattern);
        Long b = System.currentTimeMillis();
        System.out.println("Time taken to Search in Redis:" + (b - a));
        return itemsJson;
    }

    @POST
    @Path("/searchMySQL")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Items> searchItemsMySQL(@FormParam("itemCode") String itemCode) {
        Long a = System.currentTimeMillis();
        List<Items> items = itemsFacade.getItemsByCode(itemCode);
        Long b = System.currentTimeMillis();
        System.out.println("Time taken to Search in MySQL:" + (b - a));
        return items;

    }

    @GET
    @Path("/getLBoardItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLBoardItems() {
        Long a = System.currentTimeMillis();
        String lboardItems = redisDao.getLBoardITems();
        Long b = System.currentTimeMillis();
        System.out.println("Time taken to Get LBoardItems in Redis:" + (b - a));
        return lboardItems;

    }

    @GET
    @Path("/getLBoardUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLBoardUsers() {
        Long a = System.currentTimeMillis();
        String lboardItems = redisDao.getLBoardUsers();
        Long b = System.currentTimeMillis();
        System.out.println("Time taken to Get getLBoardUsers in Redis:" + (b - a));
        return lboardItems;
    }

    @GET
    @Path("/clearRedis")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAllRedisKeys() {
        redisDao.deleteAllKeys();
        return "Done";
    }

}
