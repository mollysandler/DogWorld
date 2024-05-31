import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import processing.core.PApplet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class SQSMessenger {

    private static final Logger LOGGER = Logger.getLogger(SQSMessenger.class.getName());
    private static final String REGION = "us-west-1";
    private static final String QUEUE_URL = "https://sqs.us-west-1.amazonaws.com/450835264782/SpiderWorldQueue";
    private static final String CREDENTIALS_PATH = "D:\\CalPoly\\CSC309\\sprint3\\csc309project\\.aws\\credentials";
    private AmazonSQS sqs;
    private WorldData game;
    private boolean iInvoked;
    private PApplet screen;

    private static SQSMessenger sqsMessenger = null;

    private SQSMessenger() {
        BasicAWSCredentials awsCredentials = getAWSCredentials();
        sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(REGION)
                .build();
        this.game = WorldData.getWorldData();
        iInvoked = false;
    }

    public static SQSMessenger getInstance() {
        if (sqsMessenger != null) {
            return sqsMessenger;
        } else {
            return new SQSMessenger();
        }
    }

    public void sendScore(int paint_score, int coding_score) {
        SQSMessenger.getInstance().messageReceiver();
        String s = paint_score + " " + coding_score;
        SQSMessenger.getInstance().messageSender(s);
    }

    public void messageSender(String s) {
        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(QUEUE_URL)
                    .withMessageBody(s);
            sqs.sendMessage(sendMessageRequest);
            System.out.println("message sent: " + s);
        } catch (AmazonSQSException e) {
            LOGGER.log(Level.SEVERE, "Failed to send message to SQS", e);
        }
    }

    public void messageReceiver() {
        while (!iInvoked) {
            try {
                ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
                        .withQueueUrl(QUEUE_URL)
                        .withMaxNumberOfMessages(1);
                ReceiveMessageResult receiveMessageResult = sqs.receiveMessage(receiveMessageRequest);

                List<Message> messages = receiveMessageResult.getMessages();
                for (Message message : messages) {
                    LOGGER.info("Message received: " + message.getBody());

                    // Process the message and end the game if a player won
                    if (message.getBody().contains("Player won the game!")) {
                        System.out.println("Another player won the game. Notifying main game.");
                        game.setPlayerWon();
                    }
                    else {
                        String scores = message.getBody();
                        int paint_score = Integer.parseInt(scores.substring(0, scores.indexOf(" ")));
                        scores = scores.substring(scores.indexOf(" "));
                        scores = scores.trim();
                        int coding_score = Integer.parseInt(scores);

                        System.out.println(paint_score);
                        System.out.println(coding_score);
                    }

                    // Delete the received message from the queue
                    String messageReceiptHandle = message.getReceiptHandle();
                    sqs.deleteMessage(new DeleteMessageRequest(QUEUE_URL, messageReceiptHandle));
                }

                // Check for messages every 3 seconds
                System.out.println("Thread going to sleep");
                sleep(3000);

            } catch (AmazonSQSException | InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Failed to receive messages from SQS", e);
            }
        }
    }

    private static BasicAWSCredentials getAWSCredentials() {
        String accessKeyId = null;
        String secretAccessKey = null;

        try (BufferedReader br = new BufferedReader(new FileReader(CREDENTIALS_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("aws_access_key_id")) {
                    accessKeyId = line.split("=")[1].trim();
                } else if (line.startsWith("aws_secret_access_key")) {
                    secretAccessKey = line.split("=")[1].trim();
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to read AWS credentials from file", e);
        }

        if (accessKeyId != null && secretAccessKey != null) {
            return new BasicAWSCredentials(accessKeyId, secretAccessKey);
        } else {
            throw new RuntimeException("Failed to read AWS credentials from file.");
        }
    }

//    public void drawWonButton() {
//        screen.fill(255, 0, 0);
//        screen.rect(370, 650, 140, 80);
//    }
//    public void checkIfPressed(int mouseX, int mouseY) {
//        if (mouseX >= 370 && mouseX <= 510 && mouseY >= 650 && mouseY <= 730) {
//            this.messageSender();
//        };
//    }
}
