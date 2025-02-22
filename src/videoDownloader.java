import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class videoDownloader {

    static String COMMAND = "yt-dlp -o \"{{FILE_PATH}}\" -f \"bestvideo[height<=1080]+bestaudio/best[height<=1080]\" --merge-output-format mp4 --postprocessor-args \"-c:v copy -c:a aac\" {{VIDEO_LINK}}";
    static String SAVE_FILEPATH = System.getenv("file_save_path");
    static String FILEPATH_PLACEHOLDER = "{{FILE_PATH}}";
    static String VIDEO_LINK_PLACEHOLDER = "{{VIDEO_LINK}}";

    public void download(String videoURL){
        try {
            if (videoURL.isBlank()){
                JOptionPane.showMessageDialog(null, "Въведохте празен линк");
                throw new RuntimeException("Грешка");
            }

            if (!COMMAND.contains(FILEPATH_PLACEHOLDER) || !COMMAND.contains(VIDEO_LINK_PLACEHOLDER)) {
                JOptionPane.showMessageDialog(null, "Expected: " + FILEPATH_PLACEHOLDER + " or " + VIDEO_LINK_PLACEHOLDER + " placeholder! ");
                throw new RuntimeException("Expected: " + FILEPATH_PLACEHOLDER + " or " + VIDEO_LINK_PLACEHOLDER + " placeholder! ");
            }

            String executionReadyCommand = COMMAND;
            executionReadyCommand = executionReadyCommand.replace(FILEPATH_PLACEHOLDER, SAVE_FILEPATH);
            executionReadyCommand = executionReadyCommand.replace(VIDEO_LINK_PLACEHOLDER, videoURL);

            if (executionReadyCommand.contains(FILEPATH_PLACEHOLDER) || executionReadyCommand.contains(VIDEO_LINK_PLACEHOLDER)) {
                throw new RuntimeException("Did Not Expect: " + FILEPATH_PLACEHOLDER + " or " + VIDEO_LINK_PLACEHOLDER + " placeholder! ");
            }

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", executionReadyCommand);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print command output
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Program executed successfully!");
            }else {
                System.out.println("Program failed with exitcode : " + exitCode );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
