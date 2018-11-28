package cn.rq.es.makeTestData;

import cn.rq.es.EsOperation.EsQuery;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
@Slf4j
public class MakeData {
    public static void main(String[] args) throws Exception {
//        insertToCase();
//        insertToCaseDes();
        insertToCasepeople();
    }

    public static void insertToCase(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int counts = 500000;
                int rememberCount = 0;
                int storeCount = 0;
                int offset = 0;
                List<Case> cases = makeCase(counts,offset);
                StringBuilder insertData = new StringBuilder();
                for (int i = offset; i < counts ; i++){
                    String index = "{ \"index\": { \"_id\": "+i+" }}";
                    String data = JSON.toJSONString(cases.get(i));
                    insertData.append(index);
                    insertData.append("\n");
                    insertData.append(data);
                    insertData.append("\n");
                    rememberCount++;
                    if (rememberCount >= 100000 && rememberCount % 100000 == 0){
                        log.info(String.format("第%d-%d条Case数据准备入库", storeCount+1,rememberCount));
                        try {
                            CreateDocument(insertData.toString(),"casett","casett");
                            storeCount = rememberCount;
                            Thread.sleep(30000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                System.out.println(insertData.toString());
                    }
                }
            }
        }).start();
    }

    public static void insertToCaseDes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int counts = 500000;
                int rememberCount = 0;
                int storeCount = 0;
                int offset = 0;
                List<CaseInfo> cases = makeCaseInfo(counts,offset);
                StringBuilder insertData = new StringBuilder();
                for (int i = offset; i < counts ; i++){
                    String index = "{ \"index\": { \"_id\": "+i+" }}";
                    String data = JSON.toJSONString(cases.get(i));
                    insertData.append(index);
                    insertData.append("\n");
                    insertData.append(data);
                    insertData.append("\n");
                    rememberCount++;
                    if (rememberCount >= 100000 && rememberCount % 100000 == 0){
                        log.info(String.format("第%d-%d条CaseInfo数据准备入库", storeCount+1,rememberCount));
                        try {
                            CreateDocument(insertData.toString(),"casett_info","casett_info");
                            storeCount = rememberCount;
                            Thread.sleep(30000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                System.out.println(insertData.toString());
                    }
                }
            }
        }).start();
    }

    public static void insertToCasepeople(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int counts = 500000;
                int rememberCount = 0;
                int storeCount = 0;
                int offset = 0;
                List<CasePeopleInfo> cases = makeCasePeopleInfo(counts,offset);
                StringBuilder insertData = new StringBuilder();
                for (int i = offset; i < counts ; i++){
                    String index = "{ \"index\": { \"_id\": "+i+" }}";
                    String data = JSON.toJSONString(cases.get(i));
                    insertData.append(index);
                    insertData.append("\n");
                    insertData.append(data);
                    insertData.append("\n");
                    rememberCount++;
                    if (rememberCount >= 100000 && rememberCount % 100000 == 0){
                        log.info(String.format("第%d-%d条CaseInfo数据准备入库", storeCount+1,rememberCount));
                        try {
                            CreateDocument(insertData.toString(),"casett_people_info","casett_people_info");
                            storeCount = rememberCount;
                            Thread.sleep(30000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                System.out.println(insertData.toString());
                    }
                }
            }
        }).start();
    }

    public static List<Case> makeCase(long counts,int offset){
        List<Case> lists = new ArrayList<>();
        for (int i = offset; i < counts ; i++){
            lists.add(new Case(i+"","第"+i+"个案子",
                    "[Case]测试字段[1],第"+i+"条",
                    "[Case]测试字段[2],第"+i+"条",
                    "[Case]测试字段[3],第"+i+"条",
                    "[Case]测试字段[4],第"+i+"条",
                    "[Case]测试字段[5],第"+i+"条",
                    "[Case]测试字段[6],第"+i+"条",
                    "[Case]测试字段[7],第"+i+"条",
                    "[Case]测试字段[8],第"+i+"条",
                    "[Case]测试字段[9],第"+i+"条",
                    "[Case]测试字段[10],第"+i+"条"));
        }
        return lists;
    }
    public static List<CaseInfo> makeCaseInfo(long counts,int offset){
        List<CaseInfo> lists = new ArrayList<>();
        for (int i = offset; i < counts ; i++){
            lists.add(new CaseInfo(i+"","第"+i+"个案子的描述",
                    "[CaseInfo]测试字段[1],第"+i+"条",
                    "[CaseInfo]测试字段[2],第"+i+"条",
                    "[CaseInfo]测试字段[3],第"+i+"条",
                    "[CaseInfo]测试字段[4],第"+i+"条",
                    "[CaseInfo]测试字段[5],第"+i+"条",
                    "[CaseInfo]测试字段[6],第"+i+"条",
                    "[CaseInfo]测试字段[7],第"+i+"条",
                    "[CaseInfo]测试字段[8],第"+i+"条",
                    "[CaseInfo]测试字段[9],第"+i+"条",
                    "[CaseInfo]测试字段[10],第"+i+"条"));
        }
        return lists;
    }

    public static List<CasePeopleInfo> makeCasePeopleInfo(long counts,int offset){
        Random random = new Random();
        List<CasePeopleInfo> lists = new ArrayList<>();
        for (int i = offset; i < counts ; i++){
            if (i % 2 == 0 ) {
                lists.add(new CasePeopleInfo(i + "", "第"+i+"个案子","犯人_" + i + "_", random.nextInt(100),
                        "[CasePeopleInfo]测试字段[1],第"+i+"条",
                        "[CasePeopleInfo]测试字段[2],第"+i+"条",
                        "[CasePeopleInfo]测试字段[3],第"+i+"条",
                        "[CasePeopleInfo]测试字段[4],第"+i+"条",
                        "[CasePeopleInfo]测试字段[5],第"+i+"条",
                        "[CasePeopleInfo]测试字段[6],第"+i+"条",
                        "[CasePeopleInfo]测试字段[7],第"+i+"条",
                        "[CasePeopleInfo]测试字段[8],第"+i+"条",
                        "[CasePeopleInfo]测试字段[9],第"+i+"条",
                        "[CasePeopleInfo]测试字段[10],第"+i+"条"));
            }
            else {
                lists.add(new CasePeopleInfo(i + "", "故意不匹配名:"+i,"犯人_" + i + "_", random.nextInt(100),
                        "[CasePeopleInfo]测试字段[1],第"+i+"条",
                        "[CasePeopleInfo]测试字段[2],第"+i+"条",
                        "[CasePeopleInfo]测试字段[3],第"+i+"条",
                        "[CasePeopleInfo]测试字段[4],第"+i+"条",
                        "[CasePeopleInfo]测试字段[5],第"+i+"条",
                        "[CasePeopleInfo]测试字段[6],第"+i+"条",
                        "[CasePeopleInfo]测试字段[7],第"+i+"条",
                        "[CasePeopleInfo]测试字段[8],第"+i+"条",
                        "[CasePeopleInfo]测试字段[9],第"+i+"条",
                        "[CasePeopleInfo]测试字段[10],第"+i+"条"));
            }
        }
        return lists;
    }


    /**
     * 创建文档
     * @throws Exception
     */
    public static void CreateDocument(String insertData,String index,String type)throws Exception {

        RestClient restClient = RestClient.builder(new HttpHost("10.0.0.8",9200,"http")).build();
        String method = "PUT";
        String endpoint = "/"+index+"/"+type+"/_bulk";
        HttpEntity entity = new NStringEntity(insertData
                , ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
//        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
