package Threads;

import Controller.LoginController;
import Controller.SkypeChatsController;
import Emotions.IndicoApi;
import Emotions.MSFaceApi;
import Model.SkypeChatsModel;
import Skype.Skype;
import View.SkypeChatsView;
import Wideo.JPanelOpenCV;
import org.opencv.core.*;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Kamil on 03.11.2017.
 */
public class CameraThread extends Thread {

    private VideoCapture camera;
    public static boolean exit;
    private Mat frame;
    private JPanelOpenCV panel;
    private int size = 0;
    private int rowSize = 0;
    private boolean isEmotions;

    public CameraThread(JPanelOpenCV panel) {
        super();
        camera = new VideoCapture(0);
        exit = false;
        frame = new Mat();
        this.panel = panel;
        this.size = panel.getLength();
        isEmotions = false;
    }

    public static BufferedImage MatToBufferedImage(Mat frame) {
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    public void setEmotions(boolean emotions) {
        isEmotions = emotions;
    }

    @Override
    public void run(){
        CascadeClassifier Detector = new CascadeClassifier();
        Detector.load("src\\main\\jars\\haarcascade_eye_tree_eyeglasses.xml");

        camera.read(frame);
        boolean blink = false;
        int i=0;
        int row = 0,cols = 0;
        boolean check = false;
        boolean multi = false;
        boolean start = false;
        while(true){
            MatOfRect faceDetections = new MatOfRect();
            if(!camera.isOpened()) break;
            if(!camera.read(frame)) break;
            if(isEmotions){

                BufferedImage image = MatToBufferedImage(frame);
                try {
                    File myFile = new File("./test.jpg");
                    myFile.createNewFile();
                    ImageIO.write(image, "jpg", new File("./test.jpg"));
                    //MSFaceApi faceApi = new MSFaceApi();
                    IndicoApi faceApi = new IndicoApi();
                    faceApi.detect();
                    isEmotions = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Detector.detectMultiScale(frame, faceDetections);
            i++;



            if(!multi) {
                if (faceDetections.toArray().length > 0) {
                    blink = false;
                    start = true;
                } else if (start) {
                    if (!blink) {
                        blink = true;

                        if (!check) {
                            check = true;
                            row = (row > 0 ? row - 1 : (size - 1));
                            panel.clearColor(row);
                            rowSize = panel.getRowSize(row);
                            if (!panel.isMulti()) {
                                panel.accept(row);
                                multi = true;
                            } else {
                                panel.acceptMulti(row);
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            panel.clearColor(row);
                        } else {
                            check = false;
                            cols = (cols>0? cols-1:rowSize-1);
                            panel.accept(row,cols);
                            panel.clearColor(row);
                            if(row==(size-1)) multi = true;
                            row = 0;
                            cols = 0;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            if(!multi) {
                if (i % 10 == 0) {

                    if(!check) {
                        panel.colorRows(row % size);
                        row = (row == (size - 1) ? 0 : row + 1);
                    } else {
                        panel.colorColumns(cols%rowSize,row);
                        cols = (cols==(rowSize-1)?0:cols+1);

                        //panel.clearColor(row);

                    }

                }

                for (Rect rect : faceDetections.toArray()) {
                    Core.circle(frame, new Point(rect.x + rect.width / 2, rect.y + rect.height / 2), 10, new Scalar(0, 0, 255));
                }

                BufferedImage image = MatToBufferedImage(frame);
                panel.change(image);
            }
            if(exit) {
                break;
            }
        }
        camera.release();

    }
}
