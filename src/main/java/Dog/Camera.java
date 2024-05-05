package Dog;

import com.fazecast.jSerialComm.*;
/**
 * @author Ivan Martinez
 */
public class Camera {
    private SerialPort camera;

    private final int NUM_RINGS = 6;
    private int[][] Pegs = new int[3][NUM_RINGS];

    private final int RED = 1;
    private final int GREEN = 2;
    private final int BLUE = 3;
    private final int YELLOW = 4;
    private final int PURPLE = 5;
    private final int ORANGE = 6;

    public Camera(SerialPort robotPort){

        camera = robotPort;
        Pegs[1] = new int[]{ORANGE, PURPLE, YELLOW, BLUE, GREEN, RED};
    }

    public void checkColors(){



    }

//    public void readCamera(){
//        InputStream readData = camera.getInputStream();
//        long duration = 5000L;
//        long startTime = System.currentTimeMillis();
//
//        while(System.currentTimeMillis() - startTime < duration){
//            System.out.println("readData from inputStream: " + readData);
//
//        }
//            camera.addDataListener(new SerialPortDataListener() {
//            @Override
//            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
//            @Override
//            public void serialEvent(SerialPortEvent event)
//            {
//                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
//                    return;
//                byte[] newData = new byte[camera.bytesAvailable()];
//                int numRead = camera.readBytes(newData, newData.length);
//                System.out.println("Read " + numRead + " bytes.");
//            }
//        });
//    }
}

