import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TaskTimeHelper {
    public static int getRandomExecuteTime(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}

class BackgroundThreadDemo {

    private static int NUM_THREADS = 3;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        for(int i = 1; i<=4; i++){
            final int sourceId  = i;
            executor.submit(()-> {
                int executionTime = TaskTimeHelper.getRandomExecuteTime();
                System.out.println("Start load " + sourceId + " Data");
                try {
                    Thread.sleep(executionTime * 1000);
                    if(executionTime > 4) {
                        System.out.println("Reques Timeout");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}


class UiThreadDemo {
    public static void main(String[] args) {
        int jumlahDataSumber = 4;
        int jumlahDataBerhasil = 0;
        int jumlahDataGagal = 0;
        int WaktuTotal = 0;

        System.out.println("Start Load " + jumlahDataSumber + " data" );

        while(jumlahDataBerhasil + jumlahDataGagal < jumlahDataSumber){
            try {
                Thread.sleep(1000);
                WaktuTotal++;
            } catch (Exception e) {
                e.printStackTrace();    
            }

            int WaktuEksekusi = TaskTimeHelper.getRandomExecuteTime();
        
            if (WaktuEksekusi > 4){
                jumlahDataGagal++;
            } else {
                jumlahDataBerhasil++;
            }
    
            System.out.println("Loading...(" + WaktuEksekusi + " s)" );
        }

        System.out.println("Take Finish \t");
        System.out.println("Time Execution " + WaktuTotal + "s. " + jumlahDataBerhasil + " Data Succesfully Loaded & " + jumlahDataGagal + " Data failed to load");
    }
}