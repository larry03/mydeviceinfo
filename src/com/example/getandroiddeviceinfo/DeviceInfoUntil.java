package com.example.getandroiddeviceinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;


public class DeviceInfoUntil {
    String format = "yyyyMMdd.HHmmss.SSSZ";// �������ʱ����ʱ���ʽ
    String cDate = getDate(new Date(), format);

    // date.setText(cDate.substring(0, 22));

    private String getDate(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    // ȡ��device��IP address
    /*private String getIp() {
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();

        // ��ʽ��IP address�����磺��ʽ��ǰ��1828825280����ʽ����192.168.1.109
        String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
        return ip;

    }*/

    /**
     * ��ѯ�ֻ��ڷ�ϵͳӦ��
     */
    public static List<PackageInfo> getAllApps(Context context) {
        List<PackageInfo> apps = new ArrayList<PackageInfo>();
        PackageManager pManager = context.getPackageManager();
        // ��ȡ�ֻ�������Ӧ��
        List<PackageInfo> paklist = pManager.getInstalledPackages(0);
        for (int i = 0; i < paklist.size(); i++) {
            PackageInfo pak = (PackageInfo) paklist.get(i);
            // �ж��Ƿ�Ϊ��ϵͳԤװ��Ӧ�ó���
            if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {
                // customs applications
                apps.add(pak);
            }
        }
        return apps;
    }

    // ��ȡdevice��os version
    public static String getosVersion() {
        String version = android.os.Build.VERSION.RELEASE;
        return "Andrid " + version;
    }
    
    public static String getAppVersionName(Context context) {    
        String versionName = "";    
       try {    
            // ---get the package info---    
           PackageManager pm = context.getPackageManager();    
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);    
            versionName = pi.versionName;    
            //versioncode = pi.versionCode;  
            if (versionName == null || versionName.length() <= 0) {    
                return "";    
            }    
        } catch (Exception e) {    
            Log.e("VersionInfo", "Exception", e);    
        }    
        return versionName;    
    }  
    
    
    private static String[] getCpuInfo() {  
        String str1 = "/proc/cpuinfo";  
        String str2="";  
        String[] cpuInfo={"",""};  
        String[] arrayOfString;  
        ///proc/cpuinfo�ļ��е�һ����CPU���ͺţ��ڶ�����CPU��Ƶ�ʣ�����ͨ�����ļ�����ȡ��Щ���ݣ�
        try {  
            FileReader fr = new FileReader(str1);  
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);  
            str2 = localBufferedReader.readLine();  
            arrayOfString = str2.split("\\s+");  
            for (int i = 2; i < arrayOfString.length; i++) {  
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";  
            }  
            str2 = localBufferedReader.readLine();  
            arrayOfString = str2.split("\\s+");  
            cpuInfo[1] += arrayOfString[2];  
            localBufferedReader.close();  
        } catch (IOException e) { 
            e.printStackTrace();
        }  
        return cpuInfo;  
    }  
    
    public static String getCpuXingHao(){
        String[] cpuInfo={"",""};
        cpuInfo= getCpuInfo();
        return cpuInfo[0];
    }
    
    public static String getCpuPingLv(){
        String[] cpuInfo={"",""};
        cpuInfo= getCpuInfo();
        return cpuInfo[1];
    }
    
    
   /* public  void getTotalMemory() {  
        String str1 = "/proc/meminfo";  
        String str2="";  
        try {  
            FileReader fr = new FileReader(str1);  
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);  
            while ((str2 = localBufferedReader.readLine()) != null) {  
                Log.i("memory", "---" + str2);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();
        }  
    } */
    
    public long[] getRomMemroy() {  
        long[] romInfo = new long[2];  
        //Total rom memory  
        romInfo[0] = getTotalInternalMemorySize();  
  
        //Available rom memory  
        File path = Environment.getDataDirectory();  
        StatFs stat = new StatFs(path.getPath());  
        long blockSize = stat.getBlockSize();  
        long availableBlocks = stat.getAvailableBlocks();  
        romInfo[1] = blockSize * availableBlocks;  
        //getVersion();  
        return romInfo;  
    }  
  
    public long getTotalInternalMemorySize() {  
        File path = Environment.getDataDirectory();  
        StatFs stat = new StatFs(path.getPath());  
        long blockSize = stat.getBlockSize();  
        long totalBlocks = stat.getBlockCount();  
        return totalBlocks * blockSize;  
    }  
    
    
    public static String getTotalMemory(Context mContext) {  
        String str1 = "/proc/meminfo";// ϵͳ�ڴ���Ϣ�ļ�  
        String str2;  
        String[] arrayOfString;  
        long initial_memory = 0;  
  
        try {  
            FileReader localFileReader = new FileReader(str1);  
            BufferedReader localBufferedReader = new BufferedReader(  
                    localFileReader, 8192);  
            str2 = localBufferedReader.readLine();// ��ȡmeminfo��һ�У�ϵͳ���ڴ��С  
  
            arrayOfString = str2.split("//s+");  
            for (String num : arrayOfString) {  
                Log.i(str2, num + "/t");  
            }  
  
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// ���ϵͳ���ڴ棬��λ��KB������1024ת��ΪByte  
            localBufferedReader.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();
        }  
        return Formatter.formatFileSize(mContext, initial_memory);// Byteת��ΪKB����MB���ڴ��С���  
    }  
    
   /* private String getAvailMemory() {// ��ȡandroid��ǰ�����ڴ��С  
        
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);  
        MemoryInfo mi = new MemoryInfo();  
        am.getMemoryInfo(mi);  
        //mi.availMem; ��ǰϵͳ�Ŀ����ڴ�  
  
        return Formatter.formatFileSize(getBaseContext(), mi.availMem);// ����ȡ���ڴ��С���  
    }  */
    
  
}
