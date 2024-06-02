import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

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

    private static SQSMessenger sqsMessenger = null;

    private SQSMessenger() {
        BasicAWSCredentials awsCredentials = getAWSCredentials();
        sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(REGION)
                .build();
        game = WorldData.getWorldData();
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
        // String response = SQSMessenger.getInstance().messageReceiver(0);
//        if (!response.isEmpty()) {
//            String scores = response;
//            System.out.println(scores);
//            int received_paint_score = Integer.parseInt(scores.substring(0, scores.indexOf(" ")));
//            scores = scores.substring(scores.indexOf(" "));
//            scores = scores.trim();
//            int received_coding_score = Integer.parseInt(scores);
//
//            if ((received_coding_score + received_paint_score) > (coding_score + paint_score)) {
//                System.out.println("YOU LOST");
//            } else if ((received_coding_score + received_paint_score) < (coding_score + paint_score)) {
//                System.out.println("YOU WIN");
//            } else {
//                System.out.println("TIE");
//            }
//        }
        iInvoked = true;
        String s = paint_score + " " + coding_score;
        SQSMessenger.getInstance().messageSender(s);
    }

    public void messageSender(String s) {
        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(QUEUE_URL)
                    .withMessageBody(s);
            sqs.sendMessage(sendMessageRequest);
        } catch (AmazonSQSException e) {
            LOGGER.log(Level.SEVERE, "Failed to send message to SQS", e);
        }
    }

    public String messageReceiver(int secs) {
        try {
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
                    .withQueueUrl(QUEUE_URL)
                    .withMaxNumberOfMessages(1);
            ReceiveMessageResult receiveMessageResult = sqs.receiveMessage(receiveMessageRequest);

            List<Message> messages = receiveMessageResult.getMessages();
            for (Message message : messages) {
                LOGGER.info("Message received: " + message.getBody());

                // Delete the received message from the queue
                String messageReceiptHandle = message.getReceiptHandle();
                sqs.deleteMessage(new DeleteMessageRequest(QUEUE_URL, messageReceiptHandle));

                return String.valueOf(message.getBody());
            }

            // Check for messages every 3 seconds
            System.out.println("Thread going to sleep");
            sleep(secs);

        } catch (AmazonSQSException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Failed to receive messages from SQS", e);
        }
        return "";
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

    public boolean getiInvoked() {
        return SQSMessenger.getInstance().iInvoked;
    }
}
