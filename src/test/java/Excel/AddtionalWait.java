package Excel;
public class AddtionalWait {
    public static void main(String[] args) {
        // Set the duration of the timer in milliseconds
        long timerDuration = 5000; // 5 seconds
        
        // Set the start time of the loop
        long startTime = System.currentTimeMillis();
        
        // Set the end time of the loop
        long endTime = startTime + timerDuration;
        
        // Execute the loop until the current time exceeds the end time
        while (System.currentTimeMillis() < endTime) {
            // Your loop logic goes here
            
            // Print a message every second
            if (1==1) {
                System.out.println("Executing loop...");
            }
            else {
            	System.out.println("Else block");
            }
            
            // Sleep for 1 second to avoid high CPU usage
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Print a message when the loop ends
        System.out.println("Loop execution complete.");
    }
}