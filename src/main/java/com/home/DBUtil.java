package com.home;
import java.util.Iterator;
import org.bson.Document;
import org.junit.Ignore;
import org.junit.Test;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

/**
 * http://itmyhome.com
 * 作者： itmyhome
 */
public class DBUtil {

    // 连接到 mongodb 服务
    MongoClient mongoClient = null;
    // 连接到数据库
    MongoDatabase mongoDatabase = null;

    /**
     * 构造方法实例化
     */
    public DBUtil() {
        mongoClient = new MongoClient("localhost", 27017);
        mongoDatabase = mongoClient.getDatabase("mydb");
        System.out.println("Connect to database successfully: " + mongoDatabase);

    }

    /**
     * 创建集合
     */
    @Ignore
    public void createCollection(String collectionName) {
        mongoDatabase.createCollection(collectionName);
        System.out.println("集合: " + collectionName + " 创建成功");
    }

    /**
     * 获取所有集合
     */
    @Ignore
    public void getCollection() {
        MongoIterable<String> result = mongoDatabase.listCollectionNames();

        Iterator ite = result.iterator();
        while (ite.hasNext()) {
            System.out.println("集合名字：" + ite.next());
        }
    }

    /**
     * 删除集合
     */
    @Ignore
    public void dropCollection(String collectionName) {
        mongoDatabase.getCollection(collectionName).drop();
        System.out.println("集合：" + collectionName + " 删除成功");
    }

    /**
     * 插入文档
     */
    @Test
    public void insert() {

        // 获取所插入集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("person");
        Document document = new Document("title", "MongoDB")
                .append("description", "database")
                .append("by","itmyhome");
        collection.insertOne(document);
    }

    /**
     * 检索所有文件
     */
    @Ignore
    public void queryAll() {
        MongoCollection<Document> collection = mongoDatabase.getCollection("person");

        FindIterable<Document> document = collection.find();
        Iterator ite = document.iterator();

        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
    }

    /**
     * 更新文件
     */
    @Test
    public void update(){
        MongoCollection<Document> collection = mongoDatabase.getCollection("person");
        collection.updateOne(Filters.eq("title", "MongoDB"),
                new Document("$set", new Document("mobile", "11011")));
    }

    /**
     * 删除文档
     */
    @Ignore
    public void deleteAllDocument() {
        MongoCollection<Document> collection = mongoDatabase.getCollection("person");
        collection.deleteMany(Filters.all("title", "MongoDB"));
        System.out.println("删除成功");
    }

    /**
     * 条件查询
     */
    @Test
    public void find() {
        MongoCollection<Document> collection = mongoDatabase.getCollection("person");
        //查询likes为100的数据
        //FindIterable<Document> document = collection.find(Filters.lt("likes",100));
        FindIterable<Document> document = collection.find();
        Iterator ite = document.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
    }
}