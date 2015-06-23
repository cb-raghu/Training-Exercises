import java.io.File;
import java.util.HashMap;
import java.util.Map;

class MoveFiles {

    Map<String, Integer> scanMap = new HashMap<String, Integer>();
    String outputFolder = "";
    public static void main(String[] args) {
        args = new String[2];
        args[0] = "/Users/cb-raghu/work/Training-Exercises/class/week2/SampleFolder";
        args[1] = "/Users/cb-raghu/work/Training-Exercises/class/week2/Dest/";
        if (args.length == 2) {
            MoveFiles scan = new MoveFiles();
            scan.outputFolder = args[1];
            scan.scanFiles((args[0]));
            System.out.println("Files moved successfully");
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
                    String[] fileDetails = extractFileDetails(file.getName());
                    updateScanMap(fileDetails,file);
                }
            }
        } else if (dirFile.isFile() && !dirFile.isHidden()) {
            String[] fileDetails = extractFileDetails(dirFile.getName());
            updateScanMap(fileDetails,dirFile);
        }
    }

    public String[] extractFileDetails(String fileName) {
        int index = -1;
        String extension,_fileName;
        String[] fileDetails = null;
        index = fileName.lastIndexOf(".");
        if (index != -1) {
            fileDetails = new String[2];
            _fileName = fileName.substring(0,index);
            extension = fileName.substring(index + 1);
            fileDetails[0] = _fileName;
            fileDetails[1] = extension;
            return fileDetails;
        }
        return fileDetails;
    }
    
    /*public void moveFile(File file)
    {
        String[] fileDetails = extractFileDetails(file.getName());
        updateScanMap(fileDetails[0]);
    }*/

    public void printExtensions() {
        for (Map.Entry<String, Integer> entry : scanMap.entrySet()) {
            System.out.println(entry.getKey() + " ---> " + entry.getValue());
        }
    }
    
    public void moveFile(File file,String[] fileDetails,int count)
    {
        if(count == 0)
            file.renameTo(new File(outputFolder + fileDetails[0] + "." + fileDetails[1]));
        else
            file.renameTo(new File(outputFolder + fileDetails[0] + "_"  + count +  "." + fileDetails[1]));
    }

    public void updateScanMap(String[] fileDetails,File file) {
        
        Integer count = scanMap.get(fileDetails[0]);
        if (count == null) {
            count = 0;
            moveFile(file,fileDetails, count++);
            scanMap.put(fileDetails[0], count);
        } else {
            moveFile(file,fileDetails, count);
            scanMap.put(fileDetails[0], count + 1);
        }

    }

}
