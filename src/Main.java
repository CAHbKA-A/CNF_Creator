import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileSourceName = "list.txt";
        String fileCommandsGit = "toGit.txt";
        StringBuilder textConfig = new StringBuilder();
        String textConf = new String("[req]\n" +
                "distinguished_name = dn\n" +
                "req_extensions = req_ext\n" +
                "prompt = no\n" +
                " \n" +
                "[dn]\n" +
                "C=RU\n" +
                "O=Sberbank of Russia\n" +
                "1.OU=Computers\n" +
                "2.OU=RADIO-EXT\n" +
                "3.OU=workstation\n" +
                "4.OU=sm\n" +
                "CN=000000\n" +
                " \n" +
                "[req_ext]\n" +
                "extendedKeyUsage = clientAuth\n" +
                "keyUsage = digitalSignature, keyEncipherment\n");
        String textConf2 = new String("blahmh");
        String textGit = new String("winpty openssl req -new -out 00000.csr -newkey rsa:2048 -passout pass:****** -keyout 00000.key -config 00000.cnf");

        File directory = new File("configs");
        StringBuilder gitString = new StringBuilder();
        if (!directory.exists()) {
            directory.mkdir();
        }

        BufferedReader reader = new BufferedReader(new FileReader(fileSourceName));
        String serialNumber;
        while ((serialNumber = reader.readLine()) != null) {
         //   System.out.println(serialNumber);

            createCnfFile(textConf, serialNumber);

            gitString.append(textGit.replaceAll("0000", serialNumber)+"\n");
        }
        FileOutputStream fosGit = new FileOutputStream(fileCommandsGit );
        fosGit.write(gitString.toString().getBytes());
        fosGit.flush();
        fosGit.close();
    }

    private static void createCnfFile(String textConf,  String serialNumber) throws IOException {
        StringBuilder sb = new StringBuilder(textConf.replaceAll("000000",serialNumber));




        FileOutputStream fos = new FileOutputStream("configs/" + serialNumber + ".cnf");
        fos.write(sb.toString().getBytes());
        fos.flush();
        fos.close();
    }

}
