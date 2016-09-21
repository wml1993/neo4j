
import java.sql.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Mysql_2 {

    static String[] ContainNoArray = new String[25610]; //String 类型的数组开创空间大小要定义长度,
    static Set<String> ContainNoSet = new HashSet<String>();

    public static void main(String[] args){

        ReadBaplie r1 = new ReadBaplie(new File("E:\\source\\研一下\\刘晋老师用来做信息图的数据 - 副本\\箱号\\baplie.txt"),ContainNoArray);
        ContainNoArray = r1.readContainNo(new File("E:\\source\\研一下\\刘晋老师用来做信息图的数据 - 副本\\箱号\\baplie.txt"),ContainNoArray);
//        System.out.print(ContainNoArray.length);
//        for(String str:ContainNoArray)
//        {System.out.println(str);}
        Mysql_2 my = new Mysql_2();
        //        System.out.print(ContainNoSet);
        my.QueryDataFromMysql(ContainNoArray);
        my.QueryBillingNoFromMysql(ContainNoArray);
    }
    void QueryBillingNoFromMysql(String[] BillingNoArray){


    }
    void QueryDataFromMysql(String[] ContainArray){
        int k=0;
            try {
                Connection con = null;
                Class.forName("com.mysql.jdbc.Driver").newInstance(); //利用反射在实例化对象
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/knowledgeshipping", "root", "123456");
                System.out.println("yes");
                //

                Statement st1 = con.createStatement();
                Statement st2 = con.createStatement();
                Statement st3 = con.createStatement();
                Statement st4 = con.createStatement();
                Statement st5 = con.createStatement();
                Statement st6 = con.createStatement();

                ResultSet BaplieResult = null;
                ResultSet CNC201Result = null;
                ResultSet COARRIResult = null;
                ResultSet COSTRPResult = null;
                ResultSet IFCTSTResult = null;
                ResultSet MT1101Result = null;

        /*          String tmp = ContainArray[0];
                    System.out.println( ContainArray[0]);*/

                while (k<ContainNoArray.length) {

                    String sql_baplie = "select 发送方,接收方,数据类型,时间,箱号 from baplie where 箱号 ='" + ContainArray[k] + "'";
                    String sql_CNC201 = "select 发送方,接收方,数据类型,时间,箱号 from CNC201 where 箱号 ='" + ContainArray[k] + "'";
                    String sql_COARRI = "select 发送方,接收方,数据类型,时间,箱号 from COARRI where 箱号 ='" + ContainArray[k] + "'";
                    String sql_COSTRP = "select 发送方,接收方,数据类型,时间,箱号 from COSTRP where 箱号 ='" + ContainArray[k] + "'";
                    String sql_IFCTST = "select 发送方,接收方,数据类型,时间,箱号 from IFCTST where 箱号 ='" + ContainArray[k] + "'";
                    String sql_MT1101 = "select 发送方,接收方,数据类型,时间,箱号 from MT1101 where 箱号 ='" + ContainArray[k] + "'";

                    BaplieResult = st1.executeQuery(sql_baplie);
                    CNC201Result = st2.executeQuery(sql_CNC201);
                    COARRIResult = st3.executeQuery(sql_COARRI);
                    COSTRPResult = st4.executeQuery(sql_COSTRP);
                    IFCTSTResult = st5.executeQuery(sql_IFCTST);
                    MT1101Result = st6.executeQuery(sql_MT1101);

                    if (BaplieResult != null) {
                        Mysql_2.GetValue(BaplieResult);
                    }
                    if (CNC201Result != null) {
                        Mysql_2.GetValue(CNC201Result);
                    }
                    if (COARRIResult != null) {
                        Mysql_2.GetValue(COARRIResult);
                    }
                    if (COSTRPResult != null) {
                        Mysql_2.GetValue(COSTRPResult);
                    }
                    if (IFCTSTResult != null) {
                        Mysql_2.GetValue(IFCTSTResult);
                    }
                    if (MT1101Result != null) {
                        Mysql_2.GetValue(MT1101Result);
                    }

                    BufferedWriter wq  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\sql_out\\17.txt",true)));
                    wq.write("..\r\n");
                    wq.close();
                    k++;
                }
                BaplieResult.close();
                CNC201Result.close();
                COARRIResult.close();
                COSTRPResult.close();
                IFCTSTResult.close();
                MT1101Result.close();

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


    static void GetValue(ResultSet s) throws SQLException, IOException {

        while(s.next()){

            String sender = s.getString("发送方");

            String receive = s.getString("接收方");
            String type = s.getString("数据类型");
            String ContainNO = s.getString("箱号");
            String Time = s.getString("时间");

//            BufferedWriter wt  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\sql_out\\17.txt",true)));
//            wt.write(""+sender+" "+receive+" "+type+" "+ContainNO+ " "+Time+"\r\n");
//            wt.close();
        }
    }


    //
    //    @Override
    //    public void run() {
    //
    //
    //    }
}

class ReadBaplie{

    String[] ContainNoArray;
    File file;
    ReadBaplie(File file, String[] string){
        this.file = file;
        this.ContainNoArray = string;
    }

    String[] readContainNo(File f1,String[] string){
        String str = null;
        String[] ss = null;
        try {
            FileInputStream st = new FileInputStream(f1);  //默认读入的是字节流stream
            InputStreamReader isr = new InputStreamReader(st);  //将字节流变成字符流
            BufferedReader reader = new BufferedReader(isr);
            int i =0;
            while((str = reader.readLine())!= null){
                Mysql.ContainNoSet.add(str);
                string[i] = str;
                i++;
            }
            Set<String> set = new HashSet<String>();
            set.addAll(Arrays.asList(string));

            ss= set.toArray(new String[0]);
            //            i = 0;
            //            while (i<string.length){
            //                System.out.println(string[i]);
            //                i++;
            //            }
            return set.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return ss;
    }
}