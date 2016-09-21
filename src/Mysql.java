/**
 * Created by wml on 2016/7/5.
 */
import java.sql.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.concurrent.Semaphore;


class Mytest extends Semaphore{

    Mytest(int permits){
        super(permits);
    }
}

public class Mysql implements Runnable {

    static Set<String> ContainNoSet = new HashSet<String>();
//    Mysql(Set<String> ContainNoSet ){
//        this.ContainNoSet = ContainNoSet;
//        我是注释
//    }

    @Override
    public synchronized void run() {

        Iterator<String> ContainNoIter = ContainNoSet.iterator();

        while (ContainNoIter.hasNext()) {
            System.out.println(Thread.currentThread().getName()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            try {

                Connection con = null;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/knowledgeshipping", "root", "123456");
                System.out.println("yes");
                //
                Statement st1 = con.createStatement();
                Statement st2 = con.createStatement();
                Statement st3 = con.createStatement();
                Statement st4 = con.createStatement();
                Statement st5 = con.createStatement();
                Statement st6 = con.createStatement();

    /*          String tmp = ContainArray[0];
                System.out.println( ContainArray[0]);*/

                    String sql_baplie = "select 发送方,接收方,数据类型,时间,箱号 from baplie where 箱号 ='" + ContainNoIter.next() + "'";
                    String sql_CNC201 = "select 发送方,接收方,数据类型,时间,箱号 from CNC201 where 箱号 ='" + ContainNoIter.next() + "'";
                    String sql_COARRI = "select 发送方,接收方,数据类型,时间,箱号 from COARRI where 箱号 ='" + ContainNoIter.next() + "'";
                    String sql_COSTRP = "select 发送方,接收方,数据类型,时间,箱号 from COSTRP where 箱号 ='" + ContainNoIter.next() + "'";
                    String sql_IFCTST = "select 发送方,接收方,数据类型,时间,箱号 from IFCTST where 箱号 ='" + ContainNoIter.next() + "'";
                    String sql_MT1101 = "select 发送方,接收方,数据类型,时间,箱号 from MT1101 where 箱号 ='" + ContainNoIter.next() + "'";

                    ContainNoIter.remove();

                    ResultSet CNC201Result = st2.executeQuery(sql_CNC201);
                    ResultSet COARRIResult = st3.executeQuery(sql_COARRI);
                    ResultSet COSTRPResult = st4.executeQuery(sql_COSTRP);
                    ResultSet IFCTSTResult = st5.executeQuery(sql_IFCTST);
                    ResultSet MT1101Result = st6.executeQuery(sql_MT1101);
                    ResultSet BaplieResult = st1.executeQuery(sql_baplie);

                    if(BaplieResult!=null){
                        Mysql.GetValue(BaplieResult);
                    }if(CNC201Result!=null){
                        Mysql.GetValue(CNC201Result);
                    }if(COARRIResult!=null){
                        Mysql.GetValue(COARRIResult);
                    }if(COSTRPResult!=null){
                        Mysql.GetValue(COSTRPResult);
                    }if(IFCTSTResult!=null){
                        Mysql.GetValue(IFCTSTResult);
                    }if(MT1101Result!=null){
                        Mysql.GetValue(MT1101Result);
                    }

                    CNC201Result.close();
                    COARRIResult.close();
                    COSTRPResult.close();
                    IFCTSTResult.close();
                    MT1101Result.close();
                    BaplieResult.close();

                    st1.close();
                    st2.close();
                    st3.close();
                    st4.close();
                    st5.close();
                    st6.close();
                    con.close();

            } catch (Exception e) {
                System.out.print("MYSQL ERROR:" + e.getMessage());
            }
        }
    }

    static synchronized void GetValue(ResultSet s) throws SQLException, IOException {
        while (s.next()) {
            String sender = s.getString("发送方");
            String receive = s.getString("接收方");
            String type = s.getString("数据类型");
            String ContainNO = s.getString("箱号");
            String Time = s.getString("时间");
            BufferedWriter wt = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\sql_out\\8.txt", true)));
            wt.write("" + sender + " " + receive + " " + type + " " + ContainNO + " " + Time + "\r\n");
            wt.close();
        }
    }
}


//class ReadBaplie{
//
//    File file;
//    private ReadBaplie(File file) {
//        this.file = file;
//    }
//
//    static String[] ContainNoArray = new String[25610]; //String 类型的数组开创空间大小要定义长度,
//    static Set<String> ContainNoHashSet = new HashSet<String>();
//
//    public static void main(String[] args) {
//
//        ReadBaplie r1 = new ReadBaplie(new File("E:\\source\\研一下\\刘晋老师用来做信息图的数据 - 副本\\箱号\\baplie.txt"));
//        r1.readContainNo(new File("E:\\source\\研一下\\刘晋老师用来做信息图的数据 - 副本\\箱号\\baplie.txt"));
//        Mysql thread1 = new Mysql();
//        Mysql thread2 = new Mysql();
//        Mysql thread3 = new Mysql();
//        Mysql thread4 = new Mysql();
//
//        new Thread(thread1).start();
//        new Thread(thread2).start();
//        new Thread(thread3).start();
//        new Thread(thread4).start();
//
//
////        System.out.print(ContainNoSet);
//
//    }
//
//
//
//   Void readContainNo(File f1){
//        String str = null;
//        try {
//            FileInputStream st = new FileInputStream(f1);  //默认读入的是字节流stream
//            InputStreamReader isr = new InputStreamReader(st);  //将字节流变成字符流
//            BufferedReader reader = new BufferedReader(isr);
//            int i =0;
//
//            while((str = reader.readLine())!= null){
////               System.out.print(str);
//                Mysql.ContainNoSet.add(str);
//                //System.out.println(str);
//                i++;
//            }
//
////            i = 0;
////            while (i<string.length){
////                System.out.println(string[i]);
////                i++;
////            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//       return null;
//    }
//}