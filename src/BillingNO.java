import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by wml on 2016/7/13.
 */
public class BillingNO {

    Connection con = null;
    static ArrayList<String> BillingArray = new ArrayList<String>(); //String 类型的数组开创空间大小要定义长度,
    public static void main(String args []){

        try {
            BillingArray= Util.getBillingNo();
            BillingNO n = new BillingNO();
            n.QueryBillingNoFromMysql(BillingArray);

//            System.out.println(Util.BillingNo);
//            no.QueryBillingNoFromMysql(Util.BillingNo);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    static void GetValues(ResultSet s) throws SQLException, IOException {
        while(s.next()){

            String sender = s.getString("发送方");
            String receive = s.getString("接收方");
            String type = s.getString("数据类型");
            String BillNO = s.getString("提单号");
            String Time = s.getString("时间");
           BufferedWriter wt  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\sql_out\\20.txt",true)));
//            System.out.println(""+sender+" "+receive+" "+type+" "+BillNO+ " "+Time+"\r\n");
            wt.write(""+sender+" "+receive+" "+type+" "+BillNO+ " "+Time+"\r\n");
            wt.close();
        }
    }

    void  QueryBillingNoFromMysql(ArrayList<String> BillingNo){
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/knowledgeshipping", "root", "123456");
            System.out.println("yes");
            String[] mysqlName = new String[7];

            mysqlName[0] = "cnc201";
            mysqlName[1] = "cnc101";
            mysqlName[2] = "cncp01";
            mysqlName[3] = "coarri";
            mysqlName[4] = "ifctst";
            mysqlName[5] = "costrp";
            mysqlName[6] = "mt1101";

            Statement st1 = con.createStatement();
            Statement st2 = con.createStatement();
            Statement st3 = con.createStatement();
            Statement st4 = con.createStatement();
            Statement st5 = con.createStatement();
            Statement st6 = con.createStatement();
            Statement st7 = con.createStatement();

            ResultSet Resqlcnc201 = null;
            ResultSet Resqlcnc101 = null;
            ResultSet Resqlcncp01 = null;
            ResultSet Resqlcoarri = null;
            ResultSet Resqlifctst = null;
            ResultSet Resqlcostrp = null;
            ResultSet Resqlmt1101 = null;

            for (int i=0;i<BillingNo.size();i++){

                String sqlcnc201 = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName[0]+ " where 提单号 ='" + Util.BillingNo.get(i)  + "'";
                String sqlcnc101 = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName[1]+ " where 提单号 ='" + Util.BillingNo.get(i)  + "'";
                String sqlcncp01 = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName[2]+ " where 提单号 ='" + Util.BillingNo.get(i)  + "'";
                String sqlcoarri = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName[3]+ " where 提单号 ='" + Util.BillingNo.get(i)  + "'";
                String sqlifctst = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName[4]+ " where 提单号 ='" + Util.BillingNo.get(i)  + "'";
                String sqlcostrp = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName[5]+ " where 提单号 ='" + Util.BillingNo.get(i)  + "'";
                String sqlmt1101 = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName[6]+ " where 提单号 ='" + Util.BillingNo.get(i)  + "'";

                Resqlcnc201 =  st1.executeQuery(sqlcnc201);
                Resqlcnc101 =  st2.executeQuery(sqlcnc101);
                Resqlcncp01 =  st3.executeQuery(sqlcncp01);
                Resqlcoarri =  st4.executeQuery(sqlcoarri);
                Resqlifctst =  st5.executeQuery(sqlifctst);
                Resqlcostrp =  st6.executeQuery(sqlcostrp);
                Resqlmt1101 =  st7.executeQuery(sqlmt1101);

                if(Resqlcnc201 !=null) {
                    BillingNO.GetValues(Resqlcnc201);
                }if(Resqlcnc101 !=null){
                    BillingNO.GetValues(Resqlcnc101);
                }if(Resqlcncp01 !=null){
                    BillingNO.GetValues(Resqlcncp01);
                }if(Resqlcoarri !=null){
                    BillingNO.GetValues(Resqlcoarri);
                }if(Resqlifctst !=null){
                    BillingNO.GetValues(Resqlifctst);
                }if(Resqlcostrp !=null){
                    BillingNO.GetValues(Resqlcostrp);
                }if(Resqlmt1101 !=null){
                    BillingNO.GetValues(Resqlmt1101);
                }

                BufferedWriter wq  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\sql_out\\20.txt",true)));
                wq.write("..\r\n");
                wq.close();
//                System.out.println(Thread.currentThread().getName()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                new Thread(new BillingHandled(mysqlName[0],st1,i)).start();
//                new Thread(new BillingHandled(mysqlName[1],st2,i)).start();
//                new Thread(new BillingHandled(mysqlName[2],st3,i)).start();
//                new Thread(new BillingHandled(mysqlName[3],st4,i)).start();
//                new Thread(new BillingHandled(mysqlName[4],st5,i)).start();
//                new Thread(new BillingHandled(mysqlName[5],st6,i)).start();
//                new Thread(new BillingHandled(mysqlName[6],st7,i)).start();
            }

            Resqlcnc201.close();
            Resqlcnc101.close();
            Resqlcncp01.close();
            Resqlcoarri.close();
            Resqlifctst.close();
            Resqlcostrp.close();
            Resqlmt1101.close();
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

class Util {

    static ArrayList<String> BillingNo = new ArrayList<String>();

    public static ArrayList<String>  getBillingNo() throws FileNotFoundException,IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\sql_out\\BillingNoAll.txt")));
        while (br.readLine()!=null){
            BillingNo.add(br.readLine());
        }
//        for(String tmp:BillingNo){
//            System.out.println(tmp);
//        }
//        System.out.println(BillingNo.size());
        HashSet<String> set = new HashSet<>();
        set.addAll(BillingNo);
        BillingNo = new ArrayList<>(set);
        return BillingNo;
//        System.out.println(BillingNo.size());
    }
}

//class BillingHandled implements Runnable{
//
//    Statement st;
//    String mysqlName;
//    int index;
//    BillingHandled(String mysqlName,Statement st,int index){
//        this.mysqlName = mysqlName;
//        this.st = st;
//        this.index = index;
//    }
//    @Override
//    public void run() {
//
//
//        String sql = "select 发送方,接收方,数据类型,时间,提单号 from " + mysqlName+ " where 提单号 ='" + Util.BillingNo.get(index)  + "'";
////        System.out.print(sql);
//        ResultSet tmp = null;
//        try {
//           tmp =  st.executeQuery(sql);
////            System.out.print(tmp);
//            if (tmp!=null){
////                System.out.println(Thread.currentThread().getName()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+index);
//                BillingNO.GetValues(tmp);
//            }
//        }catch (Exception e){
//
//        }
//    }
//}