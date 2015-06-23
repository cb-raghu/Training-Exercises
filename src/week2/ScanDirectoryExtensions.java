import java.io.File;
import java.util.HashMap;
import java.util.Map;

class ScanDirectoryExtensions {

    Map<String, Integer> scanMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        //args = new String[1];
        //args[0] = "/Users/cb-raghu/work/client-libs/node";
        if (args.length == 1) {
            ScanDirectoryExtensions scan = new ScanDirectoryExtensions();
            scan.scanFiles((args[0]));
            scan.printExtensions();
        } else {
            System.out.println("Invalid arguments");
        }
    }

    public void scanFiles(String folderPath) {
        String filePathString;

        File dirFile = new File(folderPath);
        File[] fileList;
        String path, extension;
        if (dirFile.isDirectory()) {
            fileList = dirFile.listFiles();
            for (File file : fileList) {
                if (file.isDirectory()) {
                    scanFiles((file.getPath()));
                } else if (file.isFile() && !file.isHidden()) {
                    path = file.getName();
                    extractExtension(path);
                }
            }
        } else if (dirFile.isFile() && !dirFile.isHidden()) {
            path = dirFile.getName();
            extractExtension(path);
        }
    }

    public void extractExtension(String fileName) {
        int index = -1;
        String extension;
        index = fileName.lastIndexOf(".");
        if (index != -1) {
            extension = fileName.substring(index + 1);
            updateScanMap(extension);
        }
    }

    public void printExtensions() {
        for (Map.Entry<String, Integer> entry : scanMap.entrySet()) {
            System.out.println(entry.getKey() + " ---> " + entry.getValue());
        }
    }

    public void updateScanMap(String extension) {
        Integer count = scanMap.get(extension);
        if (count == null) {
            scanMap.put(extension, 1);
        } else {
            scanMap.put(extension, count + 1);
        }

    }

}
