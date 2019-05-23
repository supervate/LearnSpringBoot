package com.learn;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class decompiler_unLimit {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        ClassPool cp = ClassPool.getDefault();
        cp.importPackage("java.io");
        cp.importPackage("com.raqsoft.util");
        cp.importPackage("com.raqsoft.dm");
        cp.importPackage("com.raqsoft.common");
        cp.importPackage("com.raqsoft.cellset");
        cp.importPackage("com.raqsoft.expression");
        cp.importPackage("com.raqsoft.resources");
//        cp.importPackage("com.dm.licence");
//        cp.importPackage("com.raqsoft.licence");
        try {
            CtClass cc = cp.get("com.raqsoft.dm.Sequence");
//            CtClass licenClass = cp.get("com.raqsoft.dm.LicenceValidate");
//
//            CtMethod checkMethod = licenClass.getDeclaredMethod("checkLicence");
//            CtMethod encode = licenClass.getDeclaredMethod("encode");
//            checkMethod = CtNewMethod.copy(checkMethod, cc, null);
//            encode = CtNewMethod.copy(encode, cc, null);
//            cp.getClassLoader().loadClass("");
//            cc.addMethod(encode);
//            cc.addMethod(checkMethod);
            CtClass p2 = cp.makeInterface("java.io.InputStream");
            CtClass[] params = new CtClass[]{CtClass.byteType,p2};
            CtMethod readLold = cc.getDeclaredMethod("readLicense",params);
            readLold.setBody("{\n" +
                    "        InputStreamReader isr = new InputStreamReader($2, \"UTF-8\");\n" +
                            "        BufferedReader br = new BufferedReader(isr);\n" +
                            "        StringBuilder sb = new StringBuilder(1024);\n" +
                            "\n" +
                            "        while(true) {\n" +
                            "            String line = br.readLine();\n" +
                            "            if (line == null) {\n" +
                            "                line = sb.toString();\n" +
                            "                Object o = XMLUtil.parseXml(line, \"Config/License\");\n" +
                            "                Record r = null;\n" +
                            "                if (o instanceof Record) {\n" +
                            "                    r = (Record)o;\n" +
                            "                    byte var8 = ((Number)r.getFieldValue(\"product\")).byteValue();\n" +
                            "                    if (var8 != $1) {\n" +
                            "                        MessageManager mm = EngineMessage.get();\n" +
                            "                        throw new RuntimeException(mm.getMessage(\"license.invalidFile\"));\n" +
                            "                    } else {\n" +
                            "                        com.raqsoft.dm.Sequence lic = new Sequence(18);\n" +
                            "                        ByteArrayOutputStream buf = new ByteArrayOutputStream(1024);\n" +
                            "                        writeByte(buf, $1);\n" +
                            "                        String productName = (String)r.getFieldValue(\"productName\");\n" +
                            "                        lic.set(1, productName);\n" +
                            "                        _$1((OutputStream)buf, (String)productName);\n" +
                            "                        long serialNo = ((Number)r.getFieldValue(\"serialNo\")).longValue();\n" +
                            "                        lic.set(2, new Long(serialNo));\n" +
                            "                        _$1(buf, serialNo);\n" +
                            "                        byte type = ((Number)r.getFieldValue(\"type\")).byteValue();\n" +
                            "                        lic.set(3, new Byte(type));\n" +
                            "                        writeByte(buf, type);\n" +
                            "                        long expiration = ((Number)r.getFieldValue(\"expiration\")).longValue();\n" +
                            "                        lic.set(4, new Long(expiration));\n" +
                            "                        _$1(buf, expiration);\n" +
                            "                        long serviceExpiration = ((Number)r.getFieldValue(\"serviceExpiration\")).longValue();\n" +
                            "                        lic.set(5, new Long(serviceExpiration));\n" +
                            "                        _$1(buf, serviceExpiration);\n" +
                            "                        String stamp = (String)r.getFieldValue(\"stamp\");\n" +
                            "                        lic.set(6, stamp);\n" +
                            "                        _$1((OutputStream)buf, (String)stamp);\n" +
                            "                        short fps = ((Number)r.getFieldValue(\"fps\")).shortValue();\n" +
                            "                        lic.set(7, new Short(fps));\n" +
                            "                        _$1(buf, fps);\n" +
                            "                        short cpu = ((Number)r.getFieldValue(\"cpu\")).shortValue();\n" +
                            "                        lic.set(8, new Short(cpu));\n" +
                            "                        _$1(buf, cpu);\n" +
                            "                        String IPRange = (String)r.getFieldValue(\"IPRange\");\n" +
                            "                        lic.set(9, IPRange);\n" +
                            "                        _$1((OutputStream)buf, (String)IPRange);\n" +
                            "                        int index = r.dataStruct().getFieldIndex(\"host\");\n" +
                            "                        String host = null;\n" +
                            "                        if (index != -1) {\n" +
                            "                            host = (String)r.getFieldValue(index);\n" +
                            "                            _$1((OutputStream)buf, (String)host);\n" +
                            "                        }\n" +
                            "\n" +
                            "                        lic.set(10, host);\n" +
                            "                        String vendor = (String)r.getFieldValue(\"vendor\");\n" +
                            "                        lic.set(11, vendor);\n" +
                            "                        _$1((OutputStream)buf, (String)vendor);\n" +
                            "                        String vendorURL = (String)r.getFieldValue(\"vendorURL\");\n" +
                            "                        lic.set(12, vendorURL);\n" +
                            "                        _$1((OutputStream)buf, (String)vendorURL);\n" +
                            "                        String vendorTel = (String)r.getFieldValue(\"vendorTel\");\n" +
                            "                        lic.set(13, vendorTel);\n" +
                            "                        _$1((OutputStream)buf, (String)vendorTel);\n" +
                            "                        String vendorLogo = (String)r.getFieldValue(\"vendorLogo\");\n" +
                            "                        lic.set(14, vendorLogo);\n" +
                            "                        _$1((OutputStream)buf, (String)vendorLogo);\n" +
                            "                        String copyright = (String)r.getFieldValue(\"copyright\");\n" +
                            "                        lic.set(15, copyright);\n" +
                            "                        _$1((OutputStream)buf, (String)copyright);\n" +
                            "                        String ISV = (String)r.getFieldValue(\"ISV\");\n" +
                            "                        lic.set(16, ISV);\n" +
                            "                        _$1((OutputStream)buf, (String)ISV);\n" +
                            "                        String user = (String)r.getFieldValue(\"user\");\n" +
                            "                        lic.set(17, user);\n" +
                            "                        _$1((OutputStream)buf, (String)user);\n" +
                            "                        String projectName = (String)r.getFieldValue(\"projectName\");\n" +
                            "                        lic.set(18, projectName);\n" +
                            "                        _$1((OutputStream)buf, (String)projectName);\n" +
                            "                        String signature = (String)r.getFieldValue(\"signature\");\n" +
                            "                        byte p = $1;\n" +
                            "                        if ($1 == 127) {\n" +
                            "                            p = 4;\n" +
                            "                        }\n" +
                            "                        _$20[p - 1] = lic;\n" +
                            "                        sb = new StringBuilder(24);\n" +
                            "                        if ($1 == 1) {\n" +
                            "                            sb.append(\"Esproc\");\n" +
                            "                        } else if ($1 == 2) {\n" +
                            "                            sb.append(\"Escalc\");\n" +
                            "                        } else if ($1 == 3) {\n" +
                            "                            sb.append(\"Report\");\n" +
                            "                        } else if ($1 == 127) {\n" +
                            "                            sb.append(\"DataMining\");\n" +
                            "                        } else {\n" +
                            "                            sb.append(\"Unknown product\");\n" +
                            "                        }\n" +
                            "                        sb.append(\" Functions(H-L) = \").append(r.getFieldValue(\"functions\"));\n" +
                            "                        return;\n" +
                            "                    }\n" +
                            "                } else {\n" +
                            "                    throw new Exception(\"invalid license file\");\n" +
                            "                }\n" +
                            "            }\n" +
                            "            sb.append(line).append('\\n');\n" +
                            "" +
                            "        }\n" +
                            "    }");
            cc.writeFile("D://Sequence/");
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
